package com.he.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class FirstProvider extends ContentProvider {
    public FirstProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        Log.i("FirstProvider", "=======delete()方法被调用=======");
        Log.i("FirstProvider", "Uri 参数为：" + uri +
                " ||selection 参数为：" + selection +
                " ||selectionArgs 参数为" + selectionArgs);
        //throw new UnsupportedOperationException("Delete Not yet implemented");
        return 1;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Log.i("FirstProvider", "=======insert()方法被调用=======");
        Log.i("FirstProvider", "Uri 参数为:" + uri + " ||values 参数为" + values);
        //throw new UnsupportedOperationException("Insert Not yet implemented");
        return uri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Log.i("FirstProvider", "=======onCreate()方法被调用=======");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Log.i("FirstProvider", "=======query()方法被调用=======");
        Log.i("FirstProvider", "Uri 参数为:" + uri +
                " ||selection 参数为：" + selection +
                " ||selectionArgs 参数为" + selectionArgs +
                " ||sortOrder 参数为：" + sortOrder);
        //throw new UnsupportedOperationException("Query Not yet implemented");
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        Log.i("FirstProvider", "=======update()方法被调用=======");
        Log.i("FirstProvider", "Uri 参数为:" + uri +
                " ||values 参数为" + values +
                " ||selection 参数为：" + selection +
                " ||selectionArgs 参数为" + selectionArgs);
        //throw new UnsupportedOperationException("Update Not yet implemented");
        return 1;
    }
}
