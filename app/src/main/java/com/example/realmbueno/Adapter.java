package com.example.realmbueno;

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
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvDni = (TextView) convertView.findViewById(R.id.listadni);
            viewHolder.tvEdat = (TextView) convertView.findViewById(R.id.edatList);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Persona item = adapterData.get(position);
        viewHolder.tvDni.setText(item.dni);
        viewHolder.tvEdat.setText(String.valueOf(item.edat));
        return convertView;
    }
}