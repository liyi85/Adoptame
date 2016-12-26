package com.adoptame.andrearodriguez.adoptame.gatolist.ui.adapter;

import android.support.v7.widget.CardView;
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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 12/23/16.
 */

public class GatoListAdapter extends RecyclerView.Adapter<GatoListAdapter.ViewHolder> {


    private List<Bebe> gatoList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;


    public GatoListAdapter(List<Bebe> gatoList, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.gatoList = gatoList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Bebe currenBebe = gatoList.get(position);

        imageLoader.load(holder.imgCat, currenBebe.getUrl());

        holder.txtNombre.setText(currenBebe.getNombre().toUpperCase());
        holder.txtTamano.setText(currenBebe.getTamaño());
        holder.txtSexo.setText(currenBebe.getSexo());
        holder.txtEdad.setText(currenBebe.getEdad());


        holder.imgFavorito.setTag(currenBebe.getFavorite());
        if (currenBebe.getFavorite()) {
            holder.imgFavorito.setImageResource(android.R.drawable.btn_star_big_on);

        } else {
            holder.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off);

        }

        holder.setOnItemClickListener(currenBebe, clickListener);

    }


    public void addGato(Bebe bebe) {
        gatoList.add(0, bebe);
        notifyDataSetChanged();
    }

    public void removeGato(Bebe bebe) {
        gatoList.remove(bebe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return gatoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgCat)
        ImageView imgCat;
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
        @Bind(R.id.cv)
        CardView cv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Bebe currenBebe, final OnItemClickListener clickListener) {
            imgCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onGatoClick(currenBebe);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(currenBebe, imgCat, txtSexo, txtEdad);
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