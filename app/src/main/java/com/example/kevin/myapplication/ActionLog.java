package com.example.kevin.myapplication;

/**
 * Created by Kevin on 11/11/2014.
 */
public class ActionLog {

    private int rn;
    private int videold;
    private String actiontype;
    private String time;
    private String sent;

    public ActionLog() {} // End DVC

    public ActionLog(int rn, int videold, String actiontype, String time, String sent) {
        this.setRn(rn);
        this.setVideold(videold);
        this.setActiontype(actiontype);
        this.setTime(time);
        this.setSent(sent);
    } // End EVC

    public int getRn() {
        return rn;
    } // End getRn

    public void setRn(int rn) {
        this.rn = rn;
    } // End setRn

    public int getVideold() {
        return videold;
    } // End getVideold

    public void setVideold(int videold) {
        this.videold = videold;
    } // End setVideold

    public String getActiontype() {
        return actiontype;
    } // End getActiontype

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    } // End setActiontype

    public String getTime() {
        return time;
    } // End getTime

    public void setTime(String time) {
        this.time = time;
    } // End setTime

    public String getSent() {
        return sent;
    } // End getSent

    public void setSent(String sent) {
        this.sent = sent;
    } // End setSent

} // End ActionLog
