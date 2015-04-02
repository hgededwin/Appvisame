package com.systemboy.appvisame.appvisame;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Daniel on 02/04/2015.
 */
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
        contactViewHolder.vName.setText(ci.getName());
        contactViewHolder.vSurname.setText(ci.getSurname());
        contactViewHolder.vEmail.setText(ci.getEmail());
        contactViewHolder.vTitle.setText(ci.getTitulo());
        contactViewHolder.bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "" + ci.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        contactViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"tarjetas:"+ci.getName()+ci.getEmail(),Toast.LENGTH_SHORT).show();
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
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        protected Button bton;
        protected CardView card;

        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
            bton=(Button) v.findViewById(R.id.boton);
            card= (CardView) v.findViewById(R.id.card_view);
        }
    }
}