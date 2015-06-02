package com.systemboy.appvisame.appvisame;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ContactViewHolder> {

    private List<Usuario> contactList;

    public UsuarioAdapter(List<Usuario> contactList) {
        this.contactList = contactList;

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        final Usuario ci = contactList.get(i);
        contactViewHolder.vTitulo.setText(ci.getTitulo());
        contactViewHolder.vEstado.setText(ci.getEstado());
        contactViewHolder.vFecha.setText(ci.getFecha());
        contactViewHolder.vUrgencia.setText(ci.getUrgencia());
        contactViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"tarjetas:"+ci.getTitulo()+ci.getEstado(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card, viewGroup, false);

        return new ContactViewHolder(itemView);
    }
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTitulo;
        protected TextView vEstado;
        protected TextView vFecha;
        protected TextView vUrgencia;
        Context context;
        protected CardView card;

        public ContactViewHolder(View v) {
            super(v);
            vTitulo =  (TextView) v.findViewById(R.id.txt_Titulo);
            vEstado = (TextView)  v.findViewById(R.id.txt_estado);
            vFecha = (TextView)  v.findViewById(R.id.txt_fecha);
            vUrgencia = (TextView) v.findViewById(R.id.txt_urgencia);
            card= (CardView) v.findViewById(R.id.cardInicio);
            context.getApplicationContext();
        }
    }
}