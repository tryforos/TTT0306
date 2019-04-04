package com.example.ttt0306projectdigitalclock;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.TimeZone;

public class DataUtilities {

    final static String FORMAT_KEY = "Format";
    final static String TIME_ZONE_KEY = "Time Zone";
    final static String COLOR_KEY = "Color";
    final static String IMAGE_KEY = "Image";

    private static final String SHARED_KEY = "TTT306";
    private SharedPreferences sharedPreferences;

    //ASSIGNED IN MainActivity AFTER onCreate
    public static AppCompatActivity mainActivity;


    ////////
    ////////
    ////////

    // getters & setters
    public static String getFORMAT_KEY() {
        return FORMAT_KEY;
    }
    public static String getTIME_ZONE_KEY() {
        return TIME_ZONE_KEY;
    }
    public static String getCOLOR_KEY() {
        return COLOR_KEY;
    }
    public static String getSHARED_KEY() {
        return SHARED_KEY;
    }
    public static String getIMAGE_KEY() {
        return IMAGE_KEY;
    }
    // END getters & setters

    public static void saveAllData(SharedPreferences sharedPreferences, boolean booFormat, String strTimeZone, String strColor, int intImage){

        //commit to data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FORMAT_KEY, booFormat);
        editor.putString(TIME_ZONE_KEY, strTimeZone);
        editor.putString(COLOR_KEY, strColor);
        editor.putInt(IMAGE_KEY, intImage);
        editor.commit();
    }

    public static int getColor(String str){

        int intColor;

        HashMap<String, Integer> hmpColors = new HashMap<String, Integer>();

        hmpColors.put("Blue", R.color.colorBlue);
        hmpColors.put("Light Blue", R.color.colorLightBlue);
        hmpColors.put("Orange", R.color.colorOrange);

        intColor = mainActivity.getResources().getColor(hmpColors.get(str));

/*
             //set color based on strColor
        if (str.equals("Blue")){
            intColor = mainActivity.getResources().getColor(R.color.colorBlue);
        }
        else if (str.equals("Light Blue")){
            intColor = mainActivity.getResources().getColor(R.color.colorLightBlue);
        }
        else {
            intColor = mainActivity.getResources().getColor(R.color.colorOrange);
        }
*/

        return intColor;
    }

    public static TimeZone getTimeZone(String str){

        TimeZone tz;

        HashMap<String, String> hmpTimeZones = new HashMap<String, String>();

        hmpTimeZones.put("Eastern (EST)", "America/New_York");
        hmpTimeZones.put("Central (CST)", "America/Chicago");
        hmpTimeZones.put("Mountain (MST)", "America/Denver");
        hmpTimeZones.put("Pacific (PST)", "America/Los_Angeles");

        tz = TimeZone.getTimeZone(hmpTimeZones.get(str));

/*
        //set color based on str
        if (str.equals("Eastern (EST)")){
            tz = TimeZone.getTimeZone("America/New_York");
        }
        else if (str.equals("Central (CST)")){
            tz = TimeZone.getTimeZone("America/Chicago");
        }
        else if (str.equals("Mountain (MST)")){
            tz = TimeZone.getTimeZone("America/Denver");
        }
        else { // "Pacific (PST)"
            tz = TimeZone.getTimeZone("America/Los_Angeles");
        }
*/

        return tz;
    }

}
