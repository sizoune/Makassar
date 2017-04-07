package com.example.wildan.makassar;

import android.util.Log;
import android.widget.Toast;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by wildan on 15/03/17.
 */

public class SocketConnect {
    private Socket mSocket;

    public SocketConnect() {
        try {
            mSocket = IO.socket("http://188.166.187.3:3000");
        } catch (URISyntaxException e) {
            Log.d("socket error",e.getMessage());
        }
    }

    public Socket getmSocket() {
        return mSocket;
    }
}
