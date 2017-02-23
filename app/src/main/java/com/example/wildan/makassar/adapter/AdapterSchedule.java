package com.example.wildan.makassar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wildan.makassar.Model.Schedule;
import com.example.wildan.makassar.R;

import java.util.ArrayList;

/**
 * Created by wildan on 17/02/17.
 */

public class AdapterSchedule extends BaseAdapter {
    ArrayList<Schedule> daftarschedule;
    Context context;

    public AdapterSchedule(ArrayList<Schedule> daftarschedule, Context context) {
        this.daftarschedule = daftarschedule;
        this.context = context;
    }

    @Override
    public int getCount() {
        return daftarschedule.size();
    }

    @Override
    public Object getItem(int position) {
        return daftarschedule.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_row_schedule, parent, false);

        ImageView foto = (ImageView) v.findViewById(R.id.imageViewFotoSchedule);
        TextView nama = (TextView) v.findViewById(R.id.textViewNamaBandS);
        TextView stage = (TextView) v.findViewById(R.id.textViewStage);
        TextView jadwal = (TextView) v.findViewById(R.id.textViewJadwal);

        Schedule s = daftarschedule.get(position);
        foto.setImageResource(R.drawable.masha);
        nama.setText(s.getBand().getName());
        stage.setText(s.getStage());
        jadwal.setText(s.getStatus());

        return v;
    }
}
