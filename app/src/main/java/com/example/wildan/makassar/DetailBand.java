package com.example.wildan.makassar;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailBand extends AppCompatActivity {

    ImageView foto;
    TextView judul, desc, source, writer;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_band);

        Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        foto = (ImageView) findViewById(R.id.imageViewDetFoto);
//        judul = (TextView) findViewById(R.id.textViewContactUs);
//        desc = (TextView) findViewById(R.id.textViewDetDesc);

        webView = (WebView) findViewById(R.id.webviewcuy);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle("Detail");
//        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.putih));
//        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.putih));

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String s = "";
        String url = "";
        if (b != null) {
            collapsingToolbar.setTitle((String) b.get("namaband"));
            s = (String) b.get("descband");
            url = (String) b.get("imgurl");
            Picasso.with(this.getApplicationContext()).load(url).into(foto);
        }

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(s, "text/html", "UTF-8");
    }
}
