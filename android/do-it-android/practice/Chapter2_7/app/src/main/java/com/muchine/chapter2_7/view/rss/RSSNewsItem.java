package com.muchine.chapter2_7.view.rss;

import android.graphics.drawable.Drawable;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class RSSNewsItem {

    private static final String[] pubDateTagNames = new String[]{"pubDate", "dc:date"};

    private String title;

    private String link;

    private String description;

    private String publishedDate;

    private String author;

    private String category;

    private Drawable icon;

    public RSSNewsItem(Element entry) {
        title = parse(entry, "title");
        link = parse(entry, "link");
        description = parse(entry, "description");
        publishedDate = parse(entry, pubDateTagNames);
        author = parse(entry, "author");
        category = parse(entry, "category");
    }

    public static String[] getPubDateTagNames() {
        return pubDateTagNames;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    private String parse(Element entry, String[] tagNames) {
        for (String tagName : tagNames) {
            String parsed = parse(entry, tagName);
            if (parsed != null) return parsed;
        }

        return null;
    }

    private String parse(Element entry, String tagName) {
        Element element = getFirstChildElement(entry, tagName);
        if (element == null) return null;

        return getValue(element);
    }

    private Element getFirstChildElement(Element element, String tagName) {
        NodeList child = element.getElementsByTagName(tagName);
        return child == null ? null : (Element) child.item(0);
    }

    private String getValue(Element element) {
        Node child = element.getFirstChild();
        if (child == null) return null;

        return child.getNodeValue();
    }
}
