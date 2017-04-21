package com.example.wildan.makassar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.wildan.makassar.Model.Sponsor;
import com.example.wildan.makassar.Model.Tenant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.drakeet.materialdialog.MaterialDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class TenantandSponsor extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    MaterialDialog mMaterialDialog;
    private ArrayList<Tenant> daftarTenant;
    private ArrayList<Sponsor> daftarSponsor;
    private SliderLayout mDemoSlider, mDemoSlider1;

    public TenantandSponsor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tenantand_sponsor, container, false);

        daftarTenant = new ArrayList<>();
        daftarSponsor = new ArrayList<>();
        getAllSchedule();
        getAllsponsor();
        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider1);
        mDemoSlider1 = (SliderLayout) v.findViewById(R.id.slider);

//        HashMap<String, String> url_maps = new HashMap<String, String>();
//        url_maps.put("Apply Now !", "http://www.endeavourpto.org/wp-content/uploads/2016/08/become-a-sponsor-1.png");
//        for (String name : url_maps.keySet()) {
//            TextSliderView textSliderView = new TextSliderView(TenantandSponsor.this.getContext());
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(url_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);
//
//            mDemoSlider1.addSlider(textSliderView);
//        }
//        mDemoSlider1.stopAutoCycle();
//        mDemoSlider1.setPagerTransformer(false, new BaseTransformer() {
//            @Override
//            protected void onTransform(View view, float v) {
//            }
//        });
//        mDemoSlider1.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        mDemoSlider1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider1.setCustomAnimation(new DescriptionAnimation());
//        mDemoSlider1.setDuration(4000);

        try {
            if (getArguments().getString("nama") != null) {
                mMaterialDialog = new MaterialDialog(TenantandSponsor.this.getContext())
                        .setTitle("Tenant Changed !")
                        .setMessage("Tenant name : " + getArguments().getString("nama") + "\nStatus : " + getArguments().getString("status"))
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            }
        } catch (Exception e) {

        }


        return v;
    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    void getAllsponsor() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://makassar90s.com/api/sponsors?key=jongceleb3s80700",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray listdata = data.getJSONArray("data");
                            for (int i = 0; i < listdata.length(); i++) {
                                JSONObject object = listdata.getJSONObject(i);
                                Sponsor t = new Sponsor(object.optString("name"), object.optString("desc"), object.optString("img_url"));
                                //Toast.makeText(TenantAndSponsor.this, t.getStatus(), Toast.LENGTH_SHORT).show();
                                daftarSponsor.add(t);
                            }
                            for (Sponsor t : daftarSponsor) {
                                TextSliderView textSliderView = new TextSliderView(TenantandSponsor.this.getContext());
                                textSliderView
                                        .description(t.getName())
                                        .image(t.getGambar())
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(TenantandSponsor.this);
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", t.getDesc());

                                mDemoSlider1.addSlider(textSliderView);
                            }

                            mDemoSlider1.setPresetTransformer(SliderLayout.Transformer.Accordion);
                            mDemoSlider1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mDemoSlider1.setCustomAnimation(new DescriptionAnimation());
                            mDemoSlider1.setDuration(4000);
                            mDemoSlider1.addOnPageChangeListener(TenantandSponsor.this);
                        } catch (Exception e) {
                            Toast.makeText(TenantandSponsor.this.getContext(), "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
//tempat response di dapatkan
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(TenantandSponsor.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(TenantandSponsor.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(TenantandSponsor.this.getContext());
        requestQueue.add(stringRequest);
    }

    void getAllSchedule() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://makassar90s.com/api/tenants?key=jongceleb3s80700",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray listdata = data.getJSONArray("data");
                            for (int i = 0; i < listdata.length(); i++) {
                                JSONObject object = listdata.getJSONObject(i);
                                Tenant t = new Tenant(object.optString("name"), object.optString("desc"), object.optString("status"), object.optString("img_url"));
                                //Toast.makeText(TenantAndSponsor.this, t.getStatus(), Toast.LENGTH_SHORT).show();
                                daftarTenant.add(t);
                            }
                            for (Tenant t : daftarTenant) {
                                TextSliderView textSliderView = new TextSliderView(TenantandSponsor.this.getContext());
                                textSliderView
                                        .description(t.getNama())
                                        .image(t.getUrlgambar())
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(TenantandSponsor.this);
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", t.getStatus());

                                mDemoSlider.addSlider(textSliderView);
                            }

                            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                            mDemoSlider.setDuration(4000);
                            mDemoSlider.addOnPageChangeListener(TenantandSponsor.this);
                        } catch (Exception e) {
                            Toast.makeText(TenantandSponsor.this.getContext(), "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
//tempat response di dapatkan
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(TenantandSponsor.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(TenantandSponsor.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(TenantandSponsor.this.getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(TenantandSponsor.this.getContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
