package com.example.aungsithumoe.rssreader;

import java.util.List;

/**
 * Created by aungsithumoe on 12/12/17.
 */

public class RSSFeed {
    String _title;String _description;
    String _link;String _rss_link;
    String _language;
    List<RSSItem> _items;

    public RSSFeed() {
    }

    public RSSFeed(String _title, String _description, String _link, String _rss_link, String _language, List<RSSItem> _items) {
        this._title = _title;
        this._description = _description;
        this._link = _link;
        this._rss_link = _rss_link;
        this._language = _language;
        this._items = _items;
    }

    public RSSFeed(String _title, String _description, String _link, String _rss_link, String _language) {
        this._title = _title;
        this._description = _description;
        this._link = _link;
        this._rss_link = _rss_link;
        this._language = _language;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public String get_rss_link() {
        return _rss_link;
    }

    public void set_rss_link(String _rss_link) {
        this._rss_link = _rss_link;
    }

    public String get_language() {
        return _language;
    }

    public void set_language(String _language) {
        this._language = _language;
    }

    public List<RSSItem> get_items() {
        return _items;
    }

    public void set_items(List<RSSItem> _items) {
        this._items = _items;
    }
}
