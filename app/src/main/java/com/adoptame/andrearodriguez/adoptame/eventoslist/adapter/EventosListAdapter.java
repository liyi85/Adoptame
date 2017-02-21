package com.adoptame.andrearodriguez.adoptame.eventoslist.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Eventos;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;
import com.adoptame.andrearodriguez.adoptame.utils.SharedPreference;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public class EventosListAdapter extends RecyclerView.Adapter<EventosListAdapter.ViewHolder> {


    private Context context;
    SharedPreference sharedPreference;

    private List<Eventos> eventosList;
    private ImageLoader imageLoader;
    private OnItemClickListenerEventos clickListener;

    public EventosListAdapter(List<Eventos> eventosList, ImageLoader imageLoader, OnItemClickListenerEventos clickListener, Context context) {
        this.eventosList = eventosList;
        this.imageLoader = imageLoader;
        this.context = context;
        this.clickListener = clickListener;
        sharedPreference = new SharedPreference();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_evento, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Eventos currenEvento = eventosList.get(position);
        imageLoader.load(holder.imgEvento, currenEvento.getUrl());

        holder.txtNombreEvento.setText(currenEvento.getNombre().toUpperCase());
        holder.txtFecha.setText(currenEvento.getFecha());
        holder.txtHora.setText(currenEvento.getHora());
        holder.txtLugar.setText(currenEvento.getLugar());
        holder.txtTipo.setText(currenEvento.getTipoevento());
    }

    public void addEvento(Eventos eventos) {
        eventosList.add(0, eventos);
        notifyDataSetChanged();
    }

    public void removeEvento(Eventos eventos) {
        eventosList.remove(eventos);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return eventosList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgEvento)
        ImageView imgEvento;
        @Bind(R.id.txtNombreEvento)
        TextView txtNombreEvento;
        @Bind(R.id.txtLugar)
        TextView txtLugar;
        @Bind(R.id.txtFecha)
        TextView txtFecha;
        @Bind(R.id.txtHora)
        TextView txtHora;
        @Bind(R.id.txtTipo)
        TextView txtTipo;
        @Bind(R.id.imgShare)
        ImageButton imgShare;
        @Bind(R.id.imgFavorito)
        ImageButton imgFavorito;
        @Bind(R.id.cv)
        CardView cv;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Eventos currenEvento, final OnItemClickListenerEventos clickListener) {
            imgEvento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onEventoClick(currenEvento);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(currenEvento, imgEvento);
                }
            });

        }
    }
}
