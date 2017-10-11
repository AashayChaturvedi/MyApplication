package com.example.gur48256.myapplication.database;

public class UserTable {
    public static final String TABLE_ITEMS = "users";
    public static final String COLUMN_NAME = "userName";
    public static final String COLUMN_COMMENTS = "comments";
    public static final String COLUMN_PHONE = "ph_no";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_GENDER = "gender";

    public static final String[] ALL_COLUMNS = {COLUMN_NAME, COLUMN_COMMENTS, COLUMN_PHONE, COLUMN_ADDRESS, COLUMN_GENDER};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_COMMENTS + " TEXT," +
                    COLUMN_PHONE + " INTEGER," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_GENDER + " TEXT" + ");";
    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}

