package com.adoptame.andrearodriguez.adoptame.perdidoslist.adapter;

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
 * Created by andrearodriguez on 3/24/17.
 */

public class PerdidosListAdapter extends RecyclerView.Adapter<PerdidosListAdapter.ViewHolder> {


    private List<Bebe> perdidosList;
    private ImageLoader imageLoader;
    private OnItemClickPerdidos clickListener;

    public PerdidosListAdapter(List<Bebe> perdidosList, ImageLoader imageLoader, OnItemClickPerdidos clickListener) {
        this.perdidosList = perdidosList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_perdidos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bebe currenPerdido = perdidosList.get(position);
        imageLoader.load(holder.imgPerdidos, currenPerdido.getUrl());

        holder.txtNombre.setText(currenPerdido.getNombre());
        holder.txtSexo.setText(currenPerdido.getSexo());
        holder.txtEdad.setText(currenPerdido.getEdad());

        holder.setOnItemClickListener(currenPerdido, clickListener);
        if (currenPerdido.isPublishedByMe()) {
            holder.imgDelete.setVisibility(View.VISIBLE);
        } else {
            holder.imgDelete.setVisibility(View.GONE);
        }
    }

    public void addPerdido(Bebe bebe) {
        perdidosList.add(0, bebe);
        notifyDataSetChanged();
    }

    public void removePerdido(Bebe bebe) {
        perdidosList.remove(bebe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return perdidosList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgPerdidos)
        ImageView imgPerdidos;
        @Bind(R.id.txtNombre)
        TextView txtNombre;
        @Bind(R.id.txtEspecie)
        TextView txtEspecie;
        @Bind(R.id.txtSexo)
        TextView txtSexo;
        @Bind(R.id.txtEdad)
        TextView txtEdad;
        @Bind(R.id.imgShare)
        ImageButton imgShare;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;
        @Bind(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Bebe currenPerdido, final OnItemClickPerdidos clickListener) {
            imgPerdidos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onPerdidoslick(currenPerdido);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(currenPerdido, imgPerdidos);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteClick(currenPerdido);
                }
            });
        }
    }
}
