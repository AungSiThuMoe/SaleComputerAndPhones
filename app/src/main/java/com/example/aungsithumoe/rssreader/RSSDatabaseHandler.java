package com.example.aungsithumoe.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class RSSDatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "rssReader.db";
    // Contacts table name
    private static final String TABLE_RSS = "websites";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_RSS_LINK = "rss_link";
    private static final String KEY_DESCRIPTION = "description";
    String DB_PATH = null;
    Context myContext;
    public RSSDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        DB_PATH = "/data/data/" + myContext.getPackageName() + "/" + "databases/rssReader.db";
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
        String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_RSS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_LINK
                + " TEXT," + KEY_RSS_LINK + " TEXT," + KEY_DESCRIPTION
                + " TEXT" + ")";
        db.execSQL(CREATE_RSS_TABLE);}
    }
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
// database does't exist yet.
            System.out.println("DB does't exist yet!");
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RSS);
// Create tables again
        onCreate(db);
    }

    /**
     *  * Adding a new website in websites table Function will check if a site
     *  * already existed in database. If existed will update the old one else
     *  * creates a new row
     *  *
     */
    public void addSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, site.get_title()); // site title
        values.put(KEY_LINK, site.get_link()); // site url
        values.put(KEY_RSS_LINK, site.get_rss_link()); // rss link url
        values.put(KEY_DESCRIPTION, site.get_description()); // site description
// Check if row already existed in database
        if (!isSiteExists(db, site.get_rss_link())) {
// site not existed, create a new row
            db.insert(TABLE_RSS, null, values);
            db.close();
        } else {
// site already existed update the row
            updateSite(site);
            db.close();
        }
    }

    /**
     *  * Reading all rows from database
     *  *
     */
    public List<WebSite> getAllSites() {
        List<WebSite> siteList = new ArrayList<WebSite>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RSS
                              + " ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WebSite site = new WebSite();
                site.set_id(cursor.getInt(0));
                site.set_title(cursor.getString(1));
                site.set_link(cursor.getString(2));
                site.set_rss_link(cursor.getString(3));
                site.set_description(cursor.getString(4));
// Adding contact to list
                siteList.add(site);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
// return contact list
        return siteList;
    }

    /**
     *  * Updating a single row row will be identified by rss link
     *  *
     */
    public int updateSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, site.get_title());
        values.put(KEY_LINK, site.get_link());
        values.put(KEY_RSS_LINK, site.get_rss_link());
        values.put(KEY_DESCRIPTION, site.get_description());
// updating row return
        int update = db.update(TABLE_RSS, values, KEY_RSS_LINK + " = ?",
                new String[]{String.valueOf(site.get_rss_link())});
        db.close();
        return update;
    }

    /**
     *  * Reading a row (website) row is identified by row id
     *  *
     */
    public WebSite getSite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RSS, new String[]{KEY_ID, KEY_TITLE,
                        KEY_LINK, KEY_RSS_LINK, KEY_DESCRIPTION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        WebSite site = new WebSite(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        site.set_id(Integer.parseInt(cursor.getString(0)));
        site.set_title(cursor.getString(1));
        site.set_link(cursor.getString(2));
        site.set_rss_link(cursor.getString(3));
        site.set_description(cursor.getString(4));
        cursor.close();
        db.close();
        return site;
    }

    /**
     *  * Deleting single row
     *  *
     */
    public void deleteSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RSS, KEY_ID + " = ?",
                new String[]{String.valueOf(site.get_id())});
        db.close();
    }

    /**
     *  * Checking whether a site is already existed check is done by matching rss
     *  * link
     *  *
     */
    public boolean isSiteExists(SQLiteDatabase db, String rss_link) {
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_RSS
                + " WHERE rss_link = '" + rss_link + "'", new String[]{});
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }
}