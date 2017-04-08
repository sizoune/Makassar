package com.example.wildan.makassar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.wildan.makassar.Model.Band;
import com.example.wildan.makassar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wildan on 16/02/17.
 */

public class AdapterBand extends BaseAdapter implements SectionIndexer {
    private static String sections = "abcdefghilmnopqrstuvz";
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


        v = inflater.inflate(R.layout.list_row_bandlist, parent, false);
        ImageView songko = (ImageView) v.findViewById(R.id.imageViewFotoBand);
        TextView som = (TextView) v.findViewById(R.id.textViewNamaBand);
        Picasso.with(v.getContext()).load(band.getImg_url()).into(songko);
        som.setTextColor(Color.parseColor("#FFFFFF"));
        som.setText(band.getName());


        return v;
    }



    @Override
    public Object[] getSections() {
        Log.d("ListView", "Get sections");
        String[] sectionsArr = new String[sections.length()];
        for (int i = 0; i < sections.length(); i++)
            sectionsArr[i] = "" + sections.charAt(i);

        return sectionsArr;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        Log.d("ListView", "Get position for section");
        for (int i = 0; i < this.getCount(); i++) {
            String item = this.daftarband.get(i).getName().toLowerCase();
            if (item.charAt(0) == sections.charAt(sectionIndex))
                return i;
        }
        return 0;

    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}
