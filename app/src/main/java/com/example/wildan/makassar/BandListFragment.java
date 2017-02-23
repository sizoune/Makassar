package com.example.wildan.makassar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wildan.makassar.Model.Band;
import com.example.wildan.makassar.adapter.AdapterBand;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BandListFragment extends Fragment {
    ArrayList<Band> daftarband;
    AdapterBand adapter;
    ListView lv;

    public BandListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        daftarband = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_band_list, container, false);
        adapter = new AdapterBand(daftarband, BandListFragment.this.getContext());
        lv = (ListView) v.findViewById(R.id.listband);
        lv.setAdapter(adapter);
        //getData();
        getAllArtist();
        adapter.notifyDataSetChanged();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Band b = daftarband.get(position);
                //Toast.makeText(BandListFragment.this.getContext(), b.getDesc(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(BandListFragment.this.getContext(), DetailBand.class);
                i.putExtra("namaband", b.getName());
                i.putExtra("descband", b.getDesc());
                i.putExtra("imgurl", b.getImg_url());
                startActivity(i);

            }
        });
        return v;
    }

    void getData() {
        Band b = new Band("Kangen", "alay", "");
        Band a = new Band("Hilmi", "alay bingits", "");
        Band c = new Band("Graham", "alay bet", "");
        Band v = new Band("Desmon", "alay max", "");

        daftarband.add(b);
        daftarband.add(a);
        daftarband.add(c);
        daftarband.add(v);
    }

    private void getAllArtist() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.URL_BAND,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray listdata = data.getJSONArray("data");

                            for (int i = 0; i < listdata.length(); i++) {
                                JSONObject object = listdata.getJSONObject(i);
                                Band b = new Band(object.optString("name", "woy kosong"), object.optString("desc", ""), object.optString("img_url", ""));
                                daftarband.add(b);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(BandListFragment.this.getContext(), "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
//tempat response di dapatkan
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(BandListFragment.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    //Adding parameters to request

                    //returning parameter
                    return params;
                } catch (Exception e) {
                    Toast.makeText(BandListFragment.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }

}
