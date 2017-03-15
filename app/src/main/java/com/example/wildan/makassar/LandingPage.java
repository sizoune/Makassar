package com.example.wildan.makassar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    TextView judul;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        judul = (TextView) toolbar.findViewById(R.id.toolbar_title);
        judul.setText("SCHEDULE");


        fragment = new ScheduleFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe, fragment);
        ft.commit();

        SocketConnect app = new SocketConnect();
        mSocket = app.getmSocket();
        mSocket.on("schedule-channel", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                LandingPage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        try {
                            JSONObject isi = data.getJSONObject("schedule");
                            String nama = (String) isi.get("name");
                            String status = (String) isi.get("status");
                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(LandingPage.this);
                            mBuilder.setSmallIcon(R.drawable.notificon);
                            mBuilder.setContentTitle("Schedule Changed !");
                            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("Band : " + nama + "\n"
                                    + "Status : " + status + "\n"
                                    + "Click for details !"));
                            mBuilder.setSound(soundUri);
                            Intent resultIntent = new Intent(LandingPage.this, LandingPage.class);
                            PendingIntent resultPendingIntent = PendingIntent.getActivity(LandingPage.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                            mBuilder.setContentIntent(resultPendingIntent);
                            mBuilder.setAutoCancel(true);
                            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            mNotificationManager.notify(0, mBuilder.build());
                        } catch (Exception e) {
                            Toast.makeText(LandingPage.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mSocket.connect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id == R.id.nav_lineup) {
                    fragment = new ScheduleFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe, fragment);
                    judul.setText("SCHEDULE");
                    ft.commit();
                } else if (id == R.id.nav_artist) {
                    fragment = new BandListFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe, fragment);
                    judul.setText("ARTIST");
                    ft.commit();
                } else if (id == R.id.nav_map) {
                    fragment = new MapFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe, fragment);
                    judul.setText("MAP");
                    ft.commit();
                }
            }
        }, 200);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
