package com.muchine.chapter2_7.thread;

import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class SocketConnector implements Runnable {

    private static final String TAG = "SocketConnector";

    private final String host;

    private Thread thread;

    public SocketConnector(String host) {
        this.host = host;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, 5001);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("Hell Sejoon from Android");
            out.flush();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String obj = (String) in.readObject();
            Log.d(TAG, "서버에서 받은 메시지: " + obj);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
