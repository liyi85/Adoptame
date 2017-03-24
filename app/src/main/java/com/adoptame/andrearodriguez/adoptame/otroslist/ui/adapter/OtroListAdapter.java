package com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter;

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
 * Created by andrearodriguez on 2/2/17.
 */

public class OtroListAdapter extends RecyclerView.Adapter<OtroListAdapter.ViewHolder> {


    private List<Bebe> otroList;
    private ImageLoader imageLoader;
    private OnItemClickListenerOtros clickListener;


    public OtroListAdapter(List<Bebe> otroList, ImageLoader imageLoader, OnItemClickListenerOtros clickListener) {
        this.otroList = otroList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_otros, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Bebe currenBebe = otroList.get(position);

        imageLoader.load(holder.imgOtro, currenBebe.getUrl());

        holder.txtNombre.setText(currenBebe.getNombre().toUpperCase());
        holder.txtEspecie.setText(currenBebe.getEspecie());
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


    public void addOtro(Bebe bebe) {
        otroList.add(0, bebe);
        notifyDataSetChanged();
    }

    public void removeOtro(Bebe bebe) {
        otroList.remove(bebe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return otroList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgPerdidos)
        ImageView imgOtro;
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
        @Bind(R.id.imgFavorito)
        ImageButton imgFavorito;
        @Bind(R.id.cv)
        CardView cv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Bebe currenBebe, final OnItemClickListenerOtros clickListener) {
            imgOtro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onOtroClick(currenBebe);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(currenBebe, imgOtro, txtSexo, txtEdad);
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
