package com.adoptame.andrearodriguez.adoptame.eventoslist;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseEventListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseEventosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Eventos;
import com.adoptame.andrearodriguez.adoptame.eventoslist.events.EventosListEvent;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.List;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public class EventosListRepositoryImp implements EventosListRepository{

    private EventBus eventBus;
    private FirebaseEventosAPI firebaseAPI;

    public EventosListRepositoryImp(EventBus eventBus, FirebaseEventosAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(EventosListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(EventosListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Eventos eventos = dataSnapshot.getValue(Eventos.class);
                String email = firebaseAPI.getAuthEmail();
                eventos.setId(dataSnapshot.getKey());
//
//                boolean publishedByMy = (bebe.getEmail()).equals(email);
//                bebe.setPublishedByMe(publishedByMy);
                post(EventosListEvent.READ_EVENT, eventos);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Eventos eventos = dataSnapshot.getValue(Eventos.class);
                eventos.setId(dataSnapshot.getKey());

                post(EventosListEvent.DELETE_EVENT, eventos);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(EventosListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }

    @Override
    public void removeEvento(final Eventos eventos) {
        firebaseAPI.remove(eventos, new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {
                post(EventosListEvent.DELETE_EVENT, eventos);
            }

            @Override
            public void onError(FirebaseError error) {
                post(EventosListEvent.DELETE_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void updateEvento(Eventos eventos) {
        eventos.update();
        post();
    }

    private void post(int type, Eventos eventos) {
        post(type, eventos, null);
    }
    private void post(int type, String error) {
        post(type, null, error);
    }

    private void post(int type, Eventos eventos, String error) {
        EventosListEvent event = new EventosListEvent();
        event.setType(type);
        event.setError(error);
        event.setEventos(eventos);
        eventBus.post(event);
    }

    private void post() {
        post(EventosListEvent.UPDATE_EVENT, null, null);

    }

    private void post(int type, List<Eventos> eventosList){
        EventosListEvent event = new EventosListEvent();
        event.setEventosList(eventosList);
        event.setType(type);
        eventBus.post(event);
    }
}
