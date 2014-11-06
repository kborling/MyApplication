package com.example.kevin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by Kevin on 11/6/2014.
 * Database Utility to manage the updating and creation of a database.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "gxc.db";
    public static final String TABLE_NAME = "tableNAME";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATA = "data";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
    } // End EVC

    /**
     *  Create table if it doesn't already exist
     *  @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        "(id integer primary key, other data text)"
        );
    } // End onCreate

    /**
     *  Drops old database and creates new database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dataName");
        onCreate(db);
    } // End onUpgrade

    /**
     *  Given a primary key, return that data
     *  @param id the Primary Key of the data
     *  @return res the data at the specified Primary Key
     */
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME + " where " + COLUMN_ID + " =" + id + "", null );
        return res;
    } // End getData

    /**
     *  Return all of the data in the specified table name
     *  @return data_list the array of all data in the specified table name
     */
    public ArrayList getAllData()
    {
        ArrayList data_list = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            data_list.add(res.getString(res.getColumnIndex(COLUMN_DATA)));
            res.moveToNext();
        }
        return data_list;
    } // End getAllData

    /**
     *  Given a primary key and data, update the contents at the pre-existing Primary Key
     *  @param id the Primary Key of the data
     *  @param data the data used to update
     *  @return boolean signify a successful update
     */
    public boolean updateData (Integer id, String data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", data);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    } // End updateData

    /**
     *  Given a primary key, delete the specified data
     *  @param id the Primary Key of the data
     *  @return res the data at the specified Primary Key
     */
    public Integer deleteData (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    } // End deleteData

    /**
     *  Return the row count in the database
     *  @return numRows the number of rows in the specified table
     */
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    } // End numberOfRows

} // End DBHelper
