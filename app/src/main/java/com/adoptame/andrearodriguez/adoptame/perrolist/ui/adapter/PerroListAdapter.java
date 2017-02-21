package com.adoptame.andrearodriguez.adoptame.perrolist.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;
import com.adoptame.andrearodriguez.adoptame.utils.SharedPreference;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 8/22/16.
 */
public class PerroListAdapter extends RecyclerView.Adapter<PerroListAdapter.ViewHolder> {

    private Context context;
    SharedPreference sharedPreference;

    private List<Bebe> perroList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public PerroListAdapter(List<Bebe> perroList, ImageLoader imageLoader, OnItemClickListener clickListener, Context context) {
        //super(context, R.layout.row_perro, perroList);
        this.perroList = perroList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
        this.context = context;
        sharedPreference = new SharedPreference();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_perro, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Bebe currenBebe = perroList.get(position);

        imageLoader.load(holder.imgDog, currenBebe.getUrl());

        holder.txtNombre.setText(currenBebe.getNombre().toUpperCase());
        holder.txtTamano.setText(currenBebe.getTamaño());
        holder.txtSexo.setText(currenBebe.getSexo());
        holder.txtEdad.setText(currenBebe.getEdad());

        holder.imgFavorito.setTag(currenBebe.getFavorite());
        if (checkFavoriteBebe(currenBebe)){
            holder.imgFavorito.setImageResource(android.R.drawable.btn_star_big_on);
            holder.imgFavorito.setTag("red");

        }else{
            holder.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off);
            holder.imgFavorito.setTag("grey");
        }

        holder.setOnItemClickListener(currenBebe, clickListener);
    }

    private boolean checkFavoriteBebe(Bebe currenBebe) {
        boolean check = false;
        List<Bebe> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (Bebe bebe : favorites) {
                if (bebe.equals(currenBebe)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }


    public void addPerro(Bebe bebe) {
        perroList.add(0, bebe);
        notifyDataSetChanged();
    }

    public void removePerro(Bebe bebe) {
        perroList.remove(bebe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return perroList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgDog)
        ImageView imgDog;
        @Bind(R.id.txtNombre)
        TextView txtNombre;
        @Bind(R.id.txtSexo)
        TextView txtSexo;
        @Bind(R.id.txtEdad)
        TextView txtEdad;
        @Bind(R.id.txtTamano)
        TextView txtTamano;
        @Bind(R.id.imgShare)
        ImageButton imgShare;
        @Bind(R.id.imgFavorito)
        ImageButton imgFavorito;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Bebe currenBebe, final OnItemClickListener clickListener) {
            imgDog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onPerroClick(currenBebe);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(currenBebe, imgDog, txtSexo, txtEdad);
                }
            });

            imgFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onFavClick(currenBebe);
                }
            });
        }
    }
}
