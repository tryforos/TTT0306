package com.example.ttt0306projectdigitalclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "Holler";
    TextView textViewHandler;
    private GestureDetectorCompat mDetector;


    ////////
    //HOLLER DATA
    //
    final String FORMAT_KEY = DataUtilities.getFORMAT_KEY();
    final String TIME_ZONE_KEY = DataUtilities.getTIME_ZONE_KEY();
    final String COLOR_KEY = DataUtilities.getCOLOR_KEY();
    final String IMAGE_KEY = DataUtilities.getIMAGE_KEY();

    // set w defaults
    boolean booFormat = true;
    String strTimeZone = "Pacific (PST)";
    String strColor = "Blue";
    int intImage = 0;

    final String SHARED_KEY = DataUtilities.getSHARED_KEY();
    SharedPreferences sharedPreferences;
    //
    //
    ////////

    ////////
    ////////
    ////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for gestures
        mDetector = new GestureDetectorCompat(this, new GestureListener());


        // NEEDS TO BE ASSIGNED, ELSE NULL
        // STATIC VARIABLE IN DataUtilities Class
        DataUtilities.mainActivity = this;

        ////////
        //HOLLER DATA
        //
        //set in onCreate
        sharedPreferences = getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        //
        //
        ////////

        ////////
        //HANDLER PERIOD TASK
        //
        final Handler handler=new Handler();

        Runnable runnableCode = new Runnable() {
            
            // get time
            Date currentTime; // = Calendar.getInstance().getTime();
            Calendar rightNow; // = Calendar.getInstance();
            int intHour;
            int intMinute; //= rightNow.get(Calendar.MINUTE);
            int intSecond; //= rightNow.get(Calendar.SECOND);

            boolean booFirst = true;

            String s;
            int i;
            boolean b = true;

            //declare
            int intValues[] = new int[6];
            int intMaxValues[] = new int[6];
            DigitViewXml dvxDigits[] = new DigitViewXml[6];

            SeparatorViewXml svxSeparator;
            TextView textMeridiem;
            TextView textDate;

            @Override
            public void run() {

                if (b) {
                    // run this code every other 500ms

                    // get stored values
                    booFormat = sharedPreferences.getBoolean(FORMAT_KEY,booFormat);
                    strTimeZone = sharedPreferences.getString(TIME_ZONE_KEY,strTimeZone);
                    strColor = sharedPreferences.getString(COLOR_KEY,strColor);
                    intImage = sharedPreferences.getInt(IMAGE_KEY,intImage);

                    if (booFirst) {
                    //if (true) {
                        // do the first time through only
                        booFirst = !booFirst;

                        //set Views
                        textMeridiem = findViewById(R.id.idSecondsTop);
                        textDate = findViewById(R.id.idDate);
                        svxSeparator = findViewById(R.id.idSeparator);

                        dvxDigits[0] = findViewById(R.id.idDigitSecondsOnes);
                        dvxDigits[1] = findViewById(R.id.idDigitSecondsTens);
                        dvxDigits[2] = findViewById(R.id.idDigitMinutesOnes);
                        dvxDigits[3] = findViewById(R.id.idDigitMinutesTens);
                        dvxDigits[4] = findViewById(R.id.idDigitHoursOnes);
                        dvxDigits[5] = findViewById(R.id.idDigitHoursTens);

                        b = true;
                        svxSeparator.setValue(b);
                        b = false;

                    } // END if (booFirst)

                    //get time, date
                    currentTime = Calendar.getInstance().getTime();
                    rightNow = Calendar.getInstance();
                    TimeZone tz = DataUtilities.getTimeZone(strTimeZone);
                    rightNow.setTimeZone(tz);
                    intMinute = rightNow.get(Calendar.MINUTE);
                    intSecond = rightNow.get(Calendar.SECOND);

                    // set time
                    intValues[0] = intSecond % 10;
                    intValues[1] = (intSecond - intSecond % 10) / 10;
                    intValues[2] = intMinute % 10;
                    intValues[3] = (intMinute - intMinute % 10) / 10;
                    if (booFormat) {
                        // military
                        intHour = rightNow.get(Calendar.HOUR_OF_DAY); // 24 hour format
                        intValues[4] = intHour % 10;
                        intValues[5] = (intHour - intHour % 10) / 10;
                    }
                    else {
                        //
                        intHour = rightNow.get(Calendar.HOUR); // 12 hour format
                        intValues[4] = intHour % 10;
                        intValues[5] = (intHour - intHour % 10) / 10;
                    }

                    //set values
                    setDigits(intValues, dvxDigits);

                    //set color
                    setColor(dvxDigits, textDate, textMeridiem, svxSeparator);

                    //set image
                    setImage(intImage);

                    //set date
                    s = rightNow.get(Calendar.MONTH)+1 +"/"+ rightNow.get(Calendar.DAY_OF_MONTH) +"/"+ rightNow.get(Calendar.YEAR);
                    textDate.setText(s);

                    //set meridiem
                    if(booFormat) {
                        textMeridiem.setText(null);
                    }
                    else if(rightNow.get(Calendar.HOUR_OF_DAY) < 12){
                        textMeridiem.setText("AM");
                    }
                    else {
                        textMeridiem.setText("PM");
                    }

                } // END if (b)

                //blink separator flashes every 500 ms
                svxSeparator.setValue(b);
                b = !b;

                //executes the same Runnable task after a delay of set miliseconds.
                handler.postDelayed(this, 500);
            }
        };

        //execute a Runnable task written inside run() method on the UIThread
        handler.post(runnableCode);
        //
        //
        ////////

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        //pass touch event to detectors
        mDetector.onTouchEvent(event);
        return true;
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            int SWIPE_MIN_DISTANCE = 120;
            int SWIPE_THRESHOLD_VELOCITY = 200;
            final String TAG = "holler";


            //Log.d(TAG, "onFling: " + event1.toString() + event2.toString());

            if(event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //left swipe
                if (intImage > 0){
                    intImage--;
                }
                DataUtilities.saveAllData(sharedPreferences, booFormat, strTimeZone, strColor, intImage);
                setImage(intImage);
                //Toast.makeText(MainActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
            }
            else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //right swipe
                if (intImage < 3){
                    intImage++;
                }
                DataUtilities.saveAllData(sharedPreferences, booFormat, strTimeZone, strColor, intImage);
                setImage(intImage);
                //Toast.makeText(MainActivity.this, "Right Swipe", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
    }

    private void setImage(int intImage){

        //set view
        //AppCompatImageView viewImage = (AppCompatImageView) findViewById(R.id.idImage);
        ImageView viewImage = findViewById(R.id.idImage);

        if (intImage == 0){
            viewImage.setImageResource(android.R.drawable.btn_star);
            //viewImage.set
        }
        else if (intImage == 1) {
            viewImage.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else if (intImage == 2) {
            viewImage.setImageResource(android.R.drawable.ic_popup_reminder);
        }
        else { //if (intImage == 3) {
            viewImage.setImageResource(android.R.drawable.ic_btn_speak_now);
        }
    }


    private void setDigits(int[] values, DigitViewXml[] digits) {
        int i =0;
        while (i < values.length) {
            //cycle through and set all values
            digits[i].setValue(values[i]);
            i++;
        }

    }

    private void setColor(DigitViewXml[] digits, TextView textDate, TextView textMeridiem, SeparatorViewXml svxSeparator) {

        //get color as int
        int intColor;
        intColor = DataUtilities.getColor(strColor);

        //apply to all digits
        int i =0;
        while (i < digits.length) {
            //cycle through and set all values
            digits[i].setColor(intColor);
            i++;
        }

        //apply to other views
        textMeridiem.setTextColor(intColor);
        textDate.setTextColor(intColor);
        svxSeparator.setColor(intColor);

    }


    //OPTIONS MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //navigation
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return true;
    }

}
