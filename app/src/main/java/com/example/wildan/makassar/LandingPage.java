package com.example.wildan.makassar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wildan.makassar.Model.DataNotif;

import org.json.JSONObject;

import java.io.Serializable;

import br.com.goncalves.pugnotification.notification.PugNotification;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import me.drakeet.materialdialog.MaterialDialog;


public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    TextView judul;
    NotificationCompat.Builder mBuilder;
    Uri soundUri;
    MaterialDialog mMaterialDialog;
    String tenantname, tenantstatus, schename, schestat;
    DataNotif note;
    private Socket mSocket;
    private PopupWindow pw;

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

        mBuilder = new NotificationCompat.Builder(LandingPage.this);
        mBuilder.setSmallIcon(R.drawable.notificon);
        soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(soundUri);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            DataNotif tenant = (DataNotif) getIntent().getSerializableExtra("data1");
            DataNotif baru = (DataNotif) getIntent().getSerializableExtra("data");
            if (baru != null) {
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle("Schedule Changed !")
                        .setMessage("Band : " + baru.getNama() + "\nStatus : " + baru.getStatus())
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            } else if (tenant != null) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
                fragment = new TenantandSponsor();
                Bundle bundle = new Bundle();
                bundle.putString("nama", tenant.getNama());
                bundle.putString("status", tenant.getStatus());
                fragment.setArguments(bundle);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainframe, fragment);
                judul.setText("SPONSOR");
                ft.commit();
            }
        }

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
                            note = new DataNotif((String) isi.get("name"), (String) isi.get("status"));
                            Bundle bb = new Bundle();
                            bb.putSerializable("data", note);
                            Intent resultIntent = new Intent(LandingPage.this, LandingPage.class);
                            resultIntent.putExtras(bb);
                            PendingIntent resultPendingIntent = PendingIntent.getActivity(LandingPage.this, 01, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
                            PugNotification.with(LandingPage.this)
                                    .load()
                                    .title("Schedule Changed !")
                                    .message("Click for details !")
                                    .click(resultPendingIntent)
                                    .smallIcon(R.drawable.notificon)
                                    .largeIcon(R.drawable.notificon)
                                    .flags(Notification.DEFAULT_ALL)
                                    .sound(soundUri)
                                    .autoCancel(true)
                                    .identifier(1)
                                    .simple()
                                    .build();
//
                        } catch (Exception e) {
                            Toast.makeText(LandingPage.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mSocket.on("tenant-channel", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                LandingPage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(LandingPage.this, "tes", Toast.LENGTH_LONG).show();
                        JSONObject data = (JSONObject) args[0];
                        try {
                            JSONObject isi = data.getJSONObject("tenant");
                            note = new DataNotif((String) isi.get("name"), (String) isi.get("status"));
                            Bundle bb = new Bundle();
                            bb.putSerializable("data1", note);
                            Intent resultIntent = new Intent(LandingPage.this, LandingPage.class);
                            resultIntent.putExtras(bb);
                            PendingIntent resultPendingIntent = PendingIntent.getActivity(LandingPage.this, 01, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
                            PugNotification.with(LandingPage.this)
                                    .load()
                                    .title("Tenant Changed !")
                                    .message("Click for details !")
                                    .click(resultPendingIntent)
                                    .smallIcon(R.drawable.notificon)
                                    .largeIcon(R.drawable.notificon)
                                    .flags(Notification.DEFAULT_ALL)
                                    .sound(soundUri)
                                    .identifier(2)
                                    .autoCancel(true)
                                    .simple()
                                    .build();
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
                } else if (id == R.id.nav_tenspon) {
                    fragment = new TenantandSponsor();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe, fragment);
                    judul.setText("SPONSOR");
                    ft.commit();
                    getWindow().setFlags(
                            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
                }
            }
        }, 200);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
