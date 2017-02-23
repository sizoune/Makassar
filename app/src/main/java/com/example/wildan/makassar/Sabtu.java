package com.example.wildan.makassar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.wildan.makassar.Model.Schedule;
import com.example.wildan.makassar.adapter.AdapterSchedule;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Sabtu extends Fragment {
    ArrayList<Schedule> daftarschedule;
    AdapterSchedule adapter;
    ListView list;


    public Sabtu() {
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
        View v = inflater.inflate(R.layout.fragment_sabtu, container, false);

        daftarschedule = new ArrayList<>();
        adapter = new AdapterSchedule(daftarschedule, Sabtu.this.getContext());
        list = (ListView) v.findViewById(R.id.listsabtu);
        list.setAdapter(adapter);
        getAllSchedule();
        adapter.notifyDataSetChanged();
        return v;
    }

    void getAllSchedule() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.URL_SABTU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray listdata = data.getJSONArray("data");
                            for (int i = 0; i < listdata.length(); i++) {
                                JSONObject object = listdata.getJSONObject(i);
                                JSONObject databand = object.getJSONObject("band");
                                Band b = new Band(databand.optString("name", ""), databand.optString("desc", ""), databand.optString("img_url", ""));
                                Schedule s = new Schedule(object.optString("stage", ""), object.optString("date", ""), object.optString("status", ""), b);
                                daftarschedule.add(s);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(Sabtu.this.getContext(), "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
//tempat response di dapatkan
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(Sabtu.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Sabtu.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }
}
