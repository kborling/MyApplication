package com.example.kevin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kevin on 11/6/2014.
 * Database Utility to manage the updating and creation of a database.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "gxc.db";
    private static final int DATABASE_VERSION = 1;
    // Table Declarations
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_SETTINGS = "settings";
    private static final String TABLE_ACTION_LOG = "action_log";
    // Notes Columns
    private static final String NOTES_COLUMN_VIDEOLD = "Videold";
    private static final String NOTES_COLUMN_TIME = "Time";
    private static final String NOTES_COLUMN_NOTE = "Note";
    private static final String NOTES_COLUMN_SENT = "Sent";
    // Settings Columns
    private static final String SETTINGS_COLUMN_KEY = "Key";
    private static final String SETTINGS_COLUMN_VALUE = "Value";
    // Action Log Columns
    private static final String ACTION_LOG_COLUMN_VIDEOLD = "Videold";
    private static final String ACTION_LOG_COLUMN_ACTIONTYPE = "ActionType";
    private static final String ACTION_LOG_COLUMN_TIME = "Time";
    private static final String ACTION_LOG_COLUMN_SENT = "Sent";

    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    } // End getHelper

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    } // End EVC

    /**
     *  Create table if it doesn't already exist
     *  @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Set Primary Key for each table
        try {
        db.execSQL(
                "CREATE TABLE " + TABLE_NOTES +
                        "(" + NOTES_COLUMN_VIDEOLD + " INTEGER," + NOTES_COLUMN_TIME + " INTEGER," + NOTES_COLUMN_NOTE + " TEXT," + NOTES_COLUMN_SENT + " TEXT)"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_SETTINGS +
                        "(" + SETTINGS_COLUMN_KEY + " TEXT," + SETTINGS_COLUMN_VALUE + " TEXT)"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_ACTION_LOG +
                        "(" + ACTION_LOG_COLUMN_VIDEOLD + " INTEGER," + ACTION_LOG_COLUMN_ACTIONTYPE + " INTEGER," + ACTION_LOG_COLUMN_TIME + " INTEGER," + ACTION_LOG_COLUMN_SENT + " TEXT)"
        );

        } catch (SQLException se) {
            Log.v("DatabaseHandler Oncreate SQLException",
            Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v("DatabaseHandler Oncreate Exception",
            Log.getStackTraceString(e));
        }
    } // End onCreate

    /**
     *  Drops older tables and call onCreate to create new tables
     *  @param db the database
     *  @param oldVersion
     *  @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTION_LOG);
        onCreate(db);

        } catch (SQLException se) {
            Log.v("DatabaseHandler onUpgrade SQLException",
            Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v("DatabaseHandler onUpgrade Exception",
            Log.getStackTraceString(e));
        }
    } // End onUpgrade


    /* Table CRUD Methods */

    public String insertNotesRecord(Notes notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(NOTES_COLUMN_VIDEOLD, notes.getVideold());
            values.put(NOTES_COLUMN_TIME, notes.getTime());
            values.put(NOTES_COLUMN_NOTE, notes.getNote());
            values.put(NOTES_COLUMN_SENT, notes.getSent());

            db.insert(TABLE_NOTES, null, values);
            db.close();

            return "Record inserted into Notes Table successfully..";
        } catch (SQLiteException se) {
            Log.v("DatabaseHandler insertNotesRecord Exception",
                    Log.getStackTraceString(se));
            return se.getMessage();
        } catch (Exception e) {
            Log.v("DatabaseHandler insertMotesRecord Exception",
                    Log.getStackTraceString(e));
            return e.getMessage();
        } finally {
            db.close();
        }
    } // End insertNotesRecord

    public ArrayList<Notes> getNotesRecord() {
        ArrayList<Notes> record = new ArrayList<Notes>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String selectQuery = "SELECT * FROM " + TABLE_NOTES;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {

                    Notes notes = new Notes();
                    notes.setVideold(cursor.getInt(0));
                    notes.setTime(cursor.getString(1));
                    notes.setNote(cursor.getString(2));
                    notes.setSent(cursor.getString(3));

                    record.add(notes);

                } while (cursor.moveToNext());
            }
            return record;
        } catch (SQLiteException se) {
            Log.v("DatabaseHandler getNotesRecord Exception",
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v("DatabaseHandler getNotesRecord Exception",
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }
        return record;
    } // End getNotesRecord

    //TODO: Verify primary key
    public void deleteNotes(int videold) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, NOTES_COLUMN_VIDEOLD + " = " + videold, null);
    } // End deleteNotes

    public String insertSettingsRecord(Settings settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(SETTINGS_COLUMN_KEY, settings.getKey());
            values.put(SETTINGS_COLUMN_VALUE, settings.getValue());

            db.insert(TABLE_SETTINGS, null, values);
            db.close();

            return "Record inserted into Settings Table successfully..";
        } catch (SQLiteException se) {
            Log.v("DatabaseHandler insertSettingsRecord Exception",
                    Log.getStackTraceString(se));
            return se.getMessage();
        } catch (Exception e) {
            Log.v("DatabaseHandler insertSettingsRecord Exception",
                    Log.getStackTraceString(e));
            return e.getMessage();
        } finally {
            db.close();
        }
    } // End insertSettingsRecord

    public ArrayList<Settings> getSettingsRecord() {
        ArrayList<Settings> record = new ArrayList<Settings>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String selectQuery = "SELECT * FROM " + TABLE_SETTINGS;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {

                    Settings settings = new Settings();
                    settings.setKey(cursor.getString(0));
                    settings.setValue(cursor.getString(1));

                    record.add(settings);

                } while (cursor.moveToNext());
            }
            return record;
        } catch (SQLiteException se) {
            Log.v("DatabaseHandler getSettingsRecord Exception",
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v("DatabaseHandler getSettingsRecord Exception",
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }
        return record;
    } // End getSettingsRecord

    //TODO: Verify primary key
    public void deleteSettings(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETTINGS, SETTINGS_COLUMN_KEY + " = " + key, null);
    } // End deleteSettings

    public String insertActionLogRecord(ActionLog actionlog) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(ACTION_LOG_COLUMN_VIDEOLD, actionlog.getVideold());
            values.put(ACTION_LOG_COLUMN_ACTIONTYPE, actionlog.getActiontype());
            values.put(ACTION_LOG_COLUMN_TIME, actionlog.getTime());
            values.put(ACTION_LOG_COLUMN_SENT, actionlog.getSent());

            db.insert(TABLE_ACTION_LOG, null, values);
            db.close();

            return "Record inserted into Action Log Table successfully..";
        } catch (SQLiteException se) {
            Log.v("DatabaseHandler insertActionLogRecord Exception",
                    Log.getStackTraceString(se));
            return se.getMessage();
        } catch (Exception e) {
            Log.v("DatabaseHandler insertActionLogsRecord Exception",
                    Log.getStackTraceString(e));
            return e.getMessage();
        } finally {
            db.close();
        }
    } // End insertActionLogRecord

    public ArrayList<ActionLog> getActionLogRecord() {
        ArrayList<ActionLog> record = new ArrayList<ActionLog>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String selectQuery = "SELECT * FROM " + TABLE_ACTION_LOG;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {

                    ActionLog actionlog = new ActionLog();
                    actionlog.setVideold(cursor.getInt(0));
                    actionlog.setActiontype(cursor.getString(1));
                    actionlog.setTime(cursor.getString(2));
                    actionlog.setSent(cursor.getString(3));

                    record.add(actionlog);

                } while (cursor.moveToNext());
            }
            return record;
        } catch (SQLiteException se) {
            Log.v("DatabaseHandler getActionLogRecord Exception",
                    Log.getStackTraceString(se));
        } catch (Exception e) {
            Log.v("DatabaseHandler getActionLogRecord Exception",
                    Log.getStackTraceString(e));
        } finally {
            db.close();
        }
        return record;
    } // End getActionLogRecord
    //TODO: Verify primary key
    public void deleteActionLog(int videold) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTION_LOG, ACTION_LOG_COLUMN_VIDEOLD + " = " + videold, null);
    } // End deleteActionLog



} // End DBHelper
