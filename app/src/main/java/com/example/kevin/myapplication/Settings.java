package com.example.kevin.myapplication;

/**
 * Created by Kevin on 11/11/2014.
 */
public class Settings {

    private String key;
    private String value;

    public Settings() {} // End DVC

    public Settings(String key, String value) {
        this.setKey(key);
        this.setValue(value);
    } // End EVC

    public String getKey() {
        return key;
    } // End getKey

    public void setKey(String key) {
        this.key = key;
    } // End setKey

    public String getValue() {
        return value;
    } // End getValue

    public void setValue(String value) {
        this.value = value;
    } // End setValue
}
