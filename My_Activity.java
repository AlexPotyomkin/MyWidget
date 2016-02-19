package com.lihoy21gmail.mywidget;


import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class My_Activity extends Activity {
    Button OK_Button;
    Calendar Chosen_date;
    DatePicker datePicker;
    String TAG = "myLog";
    String Min_date;
    int mAppWidgetId;
    final static String CHANGE_DATE = "com.lihoy21gmail.mywidge.change_date";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        OK_Button = (Button) findViewById(R.id.OK_Button);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        Bundle extra = getIntent().getExtras();
        mAppWidgetId = extra.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        Min_date = extra.getString("Min_date");
        //Calendar today = Calendar.getInstance();
        Chosen_date = Calendar.getInstance();
        //Log.d(TAG, "onClick: " + sdf.format(Chosen_date.getTime()));
        datePicker.init(Chosen_date.get(Calendar.YEAR), Chosen_date.get(Calendar.MONTH),
                Chosen_date.get(Calendar.DAY_OF_MONTH), null);
        datePicker.setMaxDate(Chosen_date.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date temp_date = null;
        try {
            temp_date = sdf.parse(Min_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert temp_date != null;
        Chosen_date.setTime(temp_date);
        datePicker.setMinDate(Chosen_date.getTimeInMillis());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar temp = Calendar.getInstance();
                temp.set(datePicker.getYear(), datePicker.getMonth(),
                        datePicker.getDayOfMonth());
                Date date1 = Calendar.getInstance().getTime();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date temp_date = null;
                try {
                    temp_date = sdf.parse(Min_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assert temp_date != null;
                Log.d(TAG, "onClick: temp = " + sdf.format(temp.getTime()));
                if (date1.after(temp.getTime())) {
                    if(temp_date.before(temp.getTime())) {
                        Chosen_date = temp;
                    }
                }
                else Chosen_date.setTime(date1);

                Log.d(TAG, "onClick: " + sdf.format(Chosen_date.getTime()));
                Intent intent = new Intent(getBaseContext(), My_Widget.class);
                intent.setAction(CHANGE_DATE);
                ArrayList<Integer> date = new ArrayList<>(3);
                //Integer date[] = new Integer[3];
                date.add(0, Chosen_date.get(Calendar.YEAR));
                date.add(1, Chosen_date.get(Calendar.MONTH));
                date.add(2, Chosen_date.get(Calendar.DAY_OF_MONTH));
                intent.putIntegerArrayListExtra("date", date);
                if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID)
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                Log.d(TAG, "onClick: id = " + mAppWidgetId);
                PendingIntent pIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }

                finish();
                // send intent broadcast receiver
            }
        };
        OK_Button.setOnClickListener(onClickListener);
    }
}
