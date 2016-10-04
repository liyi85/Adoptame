package com.example.andrearodriguez.adoptame.fundacioneslist.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.entities.Fundacion;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundationListAdapter extends RecyclerView.Adapter<FundationListAdapter.ViewHolder> {


    private List<Fundacion> fundationList;
    private OnItemClickListenerF clickListener;

    public FundationListAdapter(List<Fundacion> fundationList, OnItemClickListenerF clickListener) {
        this.fundationList = fundationList;
        this.clickListener = clickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fundation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fundacion currenFundacion = fundationList.get(position);
        holder.setOnItemClickListener(currenFundacion, clickListener);

        holder.txtNombre.setText(currenFundacion.getNombreFundacion());
        holder.txtCorreo.setText(currenFundacion.getEmail());
        holder.txtTelefono.setText(currenFundacion.getTelefono());
        holder.txtDireccion.setText(currenFundacion.getDireccion());

    }

    public void addFundation(Fundacion fundacion) {
        fundationList.add(0, fundacion);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return fundationList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtNombre)
        TextView txtNombre;
        @Bind(R.id.txtDireccion)
        TextView txtDireccion;
        @Bind(R.id.txtCorreo)
        TextView txtCorreo;
        @Bind(R.id.txtTelefono)
        TextView txtTelefono;
        @Bind(R.id.imgTelefono)
        ImageButton imgTelefono;
        @Bind(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        } public void setOnItemClickListener(final Fundacion fundacion, final OnItemClickListenerF clickListener) {
            imgTelefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onFundationClick(fundacion);
                }
            });

        }
    }
}