package com.example.kevin.myapplication;

/**
 * Created by Kevin on 11/11/2014.
 */
public class Notes {

    private int videold;
    private String time;
    private String note;
    private String sent;

    public Notes(){} // End DVC

    public Notes (int videold, String time, String note, String sent) {
        this.setVideold(videold);
        this.setTime(time);
        this.setNote(note);
        this.setSent(sent);
    } // End EVC

    public int getVideold() {
        return videold;
    } // End getVideold

    public void setVideold(int videold) {
        this.videold = videold;
    } // End setVideold

    public String getTime() {
        return time;
    } // End getTime

    public void setTime(String time) {
        this.time = time;
    } // End setTime

    public String getNote() {
        return note;
    } // End getNote

    public void setNote(String note) {
        this.note = note;
    } // End setNote

    public String getSent() {
        return sent;
    } // End getSent

    public void setSent(String sent) {
        this.sent = sent;
    } // End setSent

} // End Notes
