package com.example.aungsithumoe.rssreader;

/**
 * Created by aungsithumoe on 12/12/17.
 */

public class WebSite {
    Integer _id;
    String _title;
    String _link;
    String _rss_link;
    String _description;

    public WebSite(String _title, String _link, String _rss_link, String _description) {
        this._title = _title;
        this._link = _link;
        this._rss_link = _rss_link;
        this._description = _description;
    }

    public WebSite() {
    }

    public WebSite(Integer _id, String _title, String _link, String _rss_link) {
        this._id = _id;
        this._title = _title;
        this._link = _link;
        this._rss_link = _rss_link;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
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

    public String get_rss_link() {
        return _rss_link;
    }

    public void set_rss_link(String _rss_link) {
        this._rss_link = _rss_link;
    }
}
