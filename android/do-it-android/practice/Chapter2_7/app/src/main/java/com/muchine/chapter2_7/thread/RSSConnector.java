package com.muchine.chapter2_7.thread;

import android.os.Handler;
import android.os.Message;

import com.muchine.chapter2_7.view.rss.RSSNewsItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class RSSConnector implements Runnable {

    private final String host;

    private final Handler handler;

    public RSSConnector(String host, Handler handler) {
        this.host = host;
        this.handler = handler;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            InputStream response = getRSSInputStream();
            Document document = buildDocument(response);

            Message message = new Message();
            message.obj = parse(document);

            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InputStream getRSSInputStream() throws Exception {
        URL url = new URL(host);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        connection.connect();
        return connection.getInputStream();
    }

    private Document buildDocument(InputStream response) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        return builder.parse(response);
    }

    private List<RSSNewsItem> parse(Document document) {
        List<RSSNewsItem> items = new ArrayList<>();

        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("item");
        if (nodes == null) return items;

        int nodeSize = nodes.getLength();
        if (nodeSize == 0) return items;

        for (int i = 0; i < nodeSize; i++) {
            RSSNewsItem item = new RSSNewsItem((Element) nodes.item(i));
            items.add(item);
        }

        return items;
    }

}
