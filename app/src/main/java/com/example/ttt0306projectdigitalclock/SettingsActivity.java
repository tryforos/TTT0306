package com.example.ttt0306projectdigitalclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private ListView listTimeZones;
    private List<String> listData;
    private ArrayAdapter adapter;

    RadioGroup ragpColors;
    Switch swchFormat;

    int i;
    int intSelectedPostion;

    ////////
    //HOLLER DATA
    //
    final String FORMAT_KEY = DataUtilities.getFORMAT_KEY();
    final String TIME_ZONE_KEY = DataUtilities.getTIME_ZONE_KEY();
    final String COLOR_KEY = DataUtilities.getCOLOR_KEY();
    final String IMAGE_KEY = DataUtilities.getIMAGE_KEY();

    // set w defaults
    boolean booFormat = true;
    String strTimeZone = "Eastern (EST)";
    String strColor = "Blue";
    int intImage = 0;

    final String SHARED_KEY = DataUtilities.getSHARED_KEY();
    SharedPreferences sharedPreferences;
    //
    //
    ////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // set views
        ragpColors = findViewById(R.id.ragpColors);
        swchFormat = findViewById(R.id.swchFormat);
        listTimeZones = findViewById(R.id.listTimeZones);

        ////////
        //HOLLER DATA
        //
        //set in onCreate
        sharedPreferences = getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);

        // get stored values
        booFormat = sharedPreferences.getBoolean(FORMAT_KEY,booFormat);
        strTimeZone = sharedPreferences.getString(TIME_ZONE_KEY,strTimeZone);
        strColor = sharedPreferences.getString(COLOR_KEY,strColor);
        intImage = sharedPreferences.getInt(IMAGE_KEY,intImage);
        //
        //
        ////////

        ////////
        //set list
        //
        listData = new ArrayList<>();
        listData.add("Eastern (EST)");
        listData.add("Central (CST)");
        listData.add("Mountain (MST)");
        listData.add("Pacific (PST)");


        ////////
        //HOLLER
        // highlight ListView simple_list_item_1
        //
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData )
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {

                final View renderer = super.getView(position, convertView, parent);
                if (position == intSelectedPostion)
                {
                    renderer.setBackgroundColor(SettingsActivity.this.getResources().getColor(R.color.colorAccent));
                }
                else {
                    renderer.setBackgroundResource(android.R.color.background_light);
                }
                return renderer;
            }
        };
        //
        //
        ////////

        listTimeZones = findViewById(R.id.listTimeZones);
        listTimeZones.setAdapter(adapter);

        ////////
        // SET VALUES
        ////////

        // 24h switch
        swchFormat.setChecked(booFormat);

        // colors
        if (strColor.equals(findViewById(R.id.radoBlue).getTag().toString())) {
            ragpColors.check(R.id.radoBlue);
        }
        else if (strColor.equals(findViewById(R.id.radoLightBlue).getTag().toString())) {
            ragpColors.check(R.id.radoLightBlue);
        }
        else { //if (strColor == findViewById(R.id.radoOrange).getTag().toString) {
            ragpColors.check(R.id.radoOrange);
        }

        // time zones
        i = 0;
        while (i < listTimeZones.getAdapter().getCount()){
            if (listTimeZones.getAdapter().getItem(i) == strTimeZone){
                //set position, update ListView
                intSelectedPostion = i;
                adapter.notifyDataSetChanged();
                //exit
                i = listTimeZones.getAdapter().getCount();
            }
            i++;
        }


        ////////
        // OVERRIDE View onClick ACTIONS
        ////////

        ////////
        //HOLLER
        // highlight ListView simple_list_item_1
        //
        listTimeZones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //set position, update ListView
                intSelectedPostion = position;
                adapter.notifyDataSetChanged();
                //save data
                strTimeZone = listTimeZones.getAdapter().getItem(position).toString();
                DataUtilities.saveAllData(sharedPreferences, booFormat, strTimeZone, strColor, intImage);
            }
        });
        //
        //
        ////////

        // radiogroup
        ragpColors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //save data
                strColor = findViewById(group.getCheckedRadioButtonId()).getTag().toString();
                DataUtilities.saveAllData(sharedPreferences, booFormat, strTimeZone, strColor, intImage);
            }
        });

        // switch
        swchFormat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //save data
                booFormat = !booFormat;
                DataUtilities.saveAllData(sharedPreferences, booFormat, strTimeZone, strColor, intImage);
            }
        });


    }


    // exit button in title bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_exit_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return to main
        finish();
        return true;
    }

}
