package com.example.kevin.myapplication;

/**
 * Created by Kevin on 11/14/2014.
 */
public class NoteTags {

    private int note_id;
    private String tag;

    public NoteTags() {} // End DVC

    public NoteTags(int note_id, String tag) {

        this.setNote_id(note_id);
        this.setTag(tag);
    } // End EVC

    public int getNote_id() {
        return note_id;
    } // End getNote_id

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    } // End setNote_id

    public String getTag() {
        return tag;
    } // End getTag

    public void setTag(String tag) {
        this.tag = tag;
    } // End setTag

} // End NoteTags
