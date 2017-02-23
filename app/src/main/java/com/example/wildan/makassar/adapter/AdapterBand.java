package com.example.wildan.makassar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wildan.makassar.Model.Band;
import com.example.wildan.makassar.R;

import java.util.ArrayList;

/**
 * Created by wildan on 16/02/17.
 */

public class AdapterBand extends BaseAdapter {
    ArrayList<Band> daftarband;
    Context context;

    public AdapterBand(ArrayList<Band> daftarband, Context context) {
        this.daftarband = daftarband;
        this.context = context;
    }

    @Override
    public int getCount() {
        return daftarband.size();
    }

    @Override
    public Object getItem(int position) {
        return daftarband.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v;

        Band band = daftarband.get(position);
        int type = getItemViewType(position);

        if (type == 1) {
            v = inflater.inflate(R.layout.list_row_sponsor, parent, false);
        } else {
            v = inflater.inflate(R.layout.list_row_bandlist, parent, false);
            ImageView songko = (ImageView) v.findViewById(R.id.imageViewFotoBand);
            TextView som = (TextView) v.findViewById(R.id.textViewNamaBand);
            songko.setImageResource(R.drawable.masha);
            som.setText(band.getName());
        }

        return v;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return daftarband.get(position).getName().equals("spons") ? 1 : 0;
    }
}
