package com.example.realmbueno;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;


/**
 * Created by jordi on 24/01/17.
 */

public class Adapter extends RealmBaseAdapter<Persona> implements ListAdapter {

    public Adapter(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);

    }


    private static class ViewHolder {
        TextView tvEdat;
        TextView tvDni;
        TextView tvGenero;
        TextView tvApellido;
        TextView tvNombre;
    }


    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvDni = (TextView) convertView.findViewById(R.id.listadni);
            viewHolder.tvEdat = (TextView) convertView.findViewById(R.id.edatList);
            viewHolder.tvApellido = (TextView) convertView.findViewById(R.id.apellidoList);
            viewHolder.tvGenero = (TextView) convertView.findViewById(R.id.generotList);
            viewHolder.tvNombre = convertView.findViewById(R.id.nombreList);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Persona item = adapterData.get(position);
        viewHolder.tvDni.setText(item.dni);
        viewHolder.tvEdat.setText(String.valueOf(item.edat));
        viewHolder.tvApellido.setText(item.apellido);
        viewHolder.tvGenero.setText(item.genero);
        viewHolder.tvNombre.setText(item.nombre);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), ModificarActivity.class);
                intent.putExtra("ModificarNombre", item.nombre);
                intent.putExtra("ModificarApellido", item.apellido);
                intent.putExtra("ModificarEdat", String.valueOf(item.edat));
                intent.putExtra("ModificarId", item.dni);
                intent.putExtra("ModificarGenere", item.genero);


                parent.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}