package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Personal;

import java.util.ArrayList;
import java.util.List;


public class ListPersonalAdapter extends ArrayAdapter<Personal> {
    private List<Personal> myListPersonal;
    private String te;

    public ListPersonalAdapter(Context context, int resource, List<Personal> objects, String t) {
        super(context, resource, objects);
        myListPersonal = objects;
        this.te = t;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_personal, null);
        }
        Personal t = myListPersonal.get(position);
        TextView textViewTen = (TextView) view.findViewById(R.id.text_view_name);
        TextView textViewRelationShip = (TextView) view.findViewById(R.id.text_view_relationship);
        textViewTen.setText(t.getTen());
        textViewRelationShip.setText(t.getQuanHe());
        ImageView imgTicked = (ImageView) view.findViewById(R.id.imgTicked);
        if (this.te.equals(t.getTen())) {
            imgTicked.setVisibility(View.VISIBLE);
        } else
            imgTicked.setVisibility(View.GONE);
        return view;
    }

    public void ReloadList(ArrayList<Personal> personals) {
        this.myListPersonal = personals;
        notifyDataSetChanged();
    }
}
