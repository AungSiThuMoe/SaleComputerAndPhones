package com.example.aungsithumoe.rssreader;

/**
 * Created by aungsithumoe on 12/12/17.
 */

public class RSSItem {
    String _title;
    String _link;
    String _description;
    String _pubdate;
    String _guid;

    public RSSItem() {
    }

    public RSSItem(String _title, String _link, String _description, String _pubdate, String _guid) {
        this._title = _title;
        this._link = _link;
        this._description = _description;
        this._pubdate = _pubdate;
        this._guid = _guid;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_pubdate() {
        return _pubdate;
    }

    public void set_pubdate(String _pubdate) {
        this._pubdate = _pubdate;
    }

    public String get_guid() {
        return _guid;
    }

    public void set_guid(String _guid) {
        this._guid = _guid;
    }
}
