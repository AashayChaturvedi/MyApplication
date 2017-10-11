package com.example.gur48256.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void insertInTable(DataItem dataItem) {
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_COMMENTS, dataItem.getComm());

        String name = dataItem.getFirst() + " " + dataItem.getLast();

        values.put(UserTable.COLUMN_NAME, name);
        values.put(UserTable.COLUMN_PHONE, dataItem.getPhone());
        values.put(UserTable.COLUMN_ADDRESS, dataItem.getAddr());
        values.put(UserTable.COLUMN_GENDER, dataItem.getGender());

        mDatabase.insert(UserTable.TABLE_ITEMS, null, values);
    }

    public List<DataItem> getItems() {
        List<DataItem> dataItemList = new ArrayList<>();

        Cursor cursor = mDatabase.query(UserTable.TABLE_ITEMS, UserTable.ALL_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            DataItem dataItem = new DataItem();
            dataItem.setComm(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_COMMENTS)));
            dataItem.setFirst(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME)));
            dataItem.setPhone(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_PHONE)));
            dataItem.setAddr(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_ADDRESS)));
            dataItem.setGender(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_GENDER)));

            dataItemList.add(dataItem);
        }
        cursor.close();
        return dataItemList;
    }

//    public long dataCount() {
//        return DatabaseUtils.queryNumEntries(mDatabase, UserTable.TABLE_ITEMS);
//    }
}
