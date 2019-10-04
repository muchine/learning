package com.muchine.chapter2_7.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class HttpConnector implements Runnable {

    private static final String TAG = "HttpConnector";

    private final Handler handler;

    private final String requestUrl;

    public HttpConnector(String requestUrl, Handler handler) {
        this.requestUrl = "http://" + requestUrl;
        this.handler = handler;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        String out = request();

        Message message = new Message();
        message.obj = out;

        handler.sendMessage(message);
    }

    private String request() {
        try {
            HttpURLConnection connection = newConnection();
            if (connection == null) return "";

            int resCode = connection.getResponseCode();
            Log.d(TAG, "response code: " + resCode);
            if (resCode != HttpURLConnection.HTTP_OK) return "";

            String out = readResponse(connection);
            connection.disconnect();

            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private HttpURLConnection newConnection() throws Exception {
        URL url = new URL(requestUrl);

        Log.d(TAG, "Connecting... url: " + requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection == null) return null;

        connection.setConnectTimeout(10000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        return connection;
    }

    private String readResponse(HttpURLConnection connection) throws Exception {
        InputStreamReader in = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(in);

        StringBuilder out = new StringBuilder();
        while(true) {
            String line = reader.readLine();
            if (line == null) break;

            out.append(line + "\n");
        }

        reader.close();
        return out.toString();
    }

}
