package com.example.wildan.makassar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailBand extends AppCompatActivity {

    TextView nama, desc, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_band);

        nama = (TextView) findViewById(R.id.txtNamaBand);
        desc = (TextView) findViewById(R.id.txtDesc);
        url = (TextView) findViewById(R.id.txtUrl);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if (b != null) {
            nama.setText((String) b.get("namaband"));
            desc.setText((String) b.get("descband"));
            url.setText((String) b.get("imgurl"));
        }
    }
}
