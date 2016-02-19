package com.lihoy21gmail.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class My_Widget extends AppWidgetProvider {
    final static String TAG = "myLog";
    final static String CLICK_BUTTON_1_inc = "com.lihoy21gmail.mywidge.click_button1_inc";
    final static String CLICK_BUTTON_2_inc = "com.lihoy21gmail.mywidge.click_button2_inc";
    final static String CLICK_BUTTON_3_inc = "com.lihoy21gmail.mywidge.click_button3_inc";
    final static String CLICK_BUTTON_4_inc = "com.lihoy21gmail.mywidge.click_button4_inc";
    final static String CLICK_BUTTON_1_dic = "com.lihoy21gmail.mywidge.click_button1_dic";
    final static String CLICK_BUTTON_2_dic = "com.lihoy21gmail.mywidge.click_button2_dic";
    final static String CLICK_BUTTON_3_dic = "com.lihoy21gmail.mywidge.click_button3_dic";
    final static String CLICK_BUTTON_4_dic = "com.lihoy21gmail.mywidge.click_button4_dic";
    final static String CLICK_ARROW_BUTTON_LEFT = "com.lihoy21gmail.mywidge.click_arrow_button1";
    final static String CLICK_ARROW_BUTTON_RIGHT = "com.lihoy21gmail.mywidge.click_arrow_button2";
    final static String CLICK_DATE_TEXT = "com.lihoy21gmail.mywidge.click_date_text";
    final static String CHANGE_DATE = "com.lihoy21gmail.mywidge.change_date";
    final static String GO_TO_STATISTICS = "com.lihoy21gmail.mywidge.go_to_statistics";
    final static String WIDGET_PREF = "widget_pref";
    final static String WIDGET_COUNT = "widget_count";
    static Date Current_date;
    static String Min_date = "01-01-2016";
    private static DB db;
    //static DBHelper dbHelper;
    //DB db;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        // создаем объект для создания и управления версиями БД
        Log.d(TAG, "onEnabled:");


    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG, "onDisabled: ");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        // обновляем все экземпляры
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
            Log.d(TAG, "onUpdate: Update widget " + i);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted:  ");

    }

    static void updateWidget(Context ctx, AppWidgetManager appWidgetManager, int widgetID) {
        Log.d(TAG, "updateWidget: ");
        RemoteViews widgetView = new RemoteViews(ctx.getPackageName(),
                R.layout.widget);

        db = new DB(ctx);
        db.open();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (Current_date == null)
            Current_date = Calendar.getInstance().getTime();

        String selection = "Date = ? ";
        String selection_arg[] = new String[]{sdf.format(Current_date)};

        Cursor c = db.getSomeData(selection, selection_arg);

        if (c.moveToFirst()) {
            int Count1Col = c.getInt(c.getColumnIndex("Count1"));
            int Count2Col = c.getInt(c.getColumnIndex("Count2"));
            int Count3Col = c.getInt(c.getColumnIndex("Count3"));
            int Count4Col = c.getInt(c.getColumnIndex("Count4"));
            // Обновление полей
            widgetView.setTextViewText(R.id.tv_1, String.valueOf(Count1Col));
            widgetView.setTextViewText(R.id.tv_2, String.valueOf(Count2Col));
            widgetView.setTextViewText(R.id.tv_3, String.valueOf(Count3Col));
            widgetView.setTextViewText(R.id.tv_4, String.valueOf(Count4Col));
        }
        c.close();
        widgetView.setTextViewText(R.id.data_text, sdf.format(Current_date));
        Log.d(TAG, "updateWidget: Current date: " + sdf.format(Current_date));
        // Button_1_inc
        Intent countIntent1 = new Intent(ctx, My_Widget.class);
        countIntent1.setAction(CLICK_BUTTON_1_inc);
        countIntent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent1 = PendingIntent.getBroadcast(ctx, 0, countIntent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_1_inc, pIntent1);

        // Button_2_inc
        Intent countIntent2 = new Intent(ctx, My_Widget.class);
        countIntent2.setAction(CLICK_BUTTON_2_inc);
        countIntent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent2 = PendingIntent.getBroadcast(ctx, 0, countIntent2,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_2_inc, pIntent2);

        // Button_3_inc
        Intent countIntent3 = new Intent(ctx, My_Widget.class);
        countIntent3.setAction(CLICK_BUTTON_3_inc);
        countIntent3.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent3 = PendingIntent.getBroadcast(ctx, 0, countIntent3,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_3_inc, pIntent3);

        // Button_4_inc
        Intent countIntent4 = new Intent(ctx, My_Widget.class);
        countIntent4.setAction(CLICK_BUTTON_4_inc);
        countIntent4.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent4 = PendingIntent.getBroadcast(ctx, 0, countIntent4,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_4_inc, pIntent4);

        // Arrow_Button_Left
        Intent arrow_Intent1 = new Intent(ctx, My_Widget.class);
        arrow_Intent1.setAction(CLICK_ARROW_BUTTON_LEFT);
        arrow_Intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent5 = PendingIntent.getBroadcast(ctx, 0, arrow_Intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.left_arrow_button, pIntent5);

        // Arrow_Button_Right
        Intent arrow_Intent2 = new Intent(ctx, My_Widget.class);
        arrow_Intent2.setAction(CLICK_ARROW_BUTTON_RIGHT);
        arrow_Intent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent6 = PendingIntent.getBroadcast(ctx, 0, arrow_Intent2,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.right_arrow_button, pIntent6);

        // Button_1_dic
        Intent countIntent5 = new Intent(ctx, My_Widget.class);
        countIntent5.setAction(CLICK_BUTTON_1_dic);
        countIntent5.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent7 = PendingIntent.getBroadcast(ctx, 0, countIntent5,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_1_dic, pIntent7);

        // Button_2_dic
        Intent countIntent6 = new Intent(ctx, My_Widget.class);
        countIntent6.setAction(CLICK_BUTTON_2_dic);
        countIntent6.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent8 = PendingIntent.getBroadcast(ctx, 0, countIntent6,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_2_dic, pIntent8);

        // Button_3_dic
        Intent countIntent7 = new Intent(ctx, My_Widget.class);
        countIntent7.setAction(CLICK_BUTTON_3_dic);
        countIntent7.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent9 = PendingIntent.getBroadcast(ctx, 0, countIntent7,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_3_dic, pIntent9);

        // Button_4_dic
        Intent countIntent8 = new Intent(ctx, My_Widget.class);
        countIntent8.setAction(CLICK_BUTTON_4_dic);
        countIntent8.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent10 = PendingIntent.getBroadcast(ctx, 0, countIntent8,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_4_dic, pIntent10);

        // Date_Text view handler
        Intent countIntent9 = new Intent(ctx, My_Widget.class);
        countIntent9.setAction(CLICK_DATE_TEXT);
        countIntent9.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent11 = PendingIntent.getBroadcast(ctx, 0, countIntent9,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.data_text, pIntent11);

        //
        Intent countIntent10 = new Intent(ctx, My_Widget.class);
        countIntent10.setAction(GO_TO_STATISTICS);
        countIntent10.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        PendingIntent pIntent12 = PendingIntent.getBroadcast(ctx, 0, countIntent10,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.tv_1, pIntent12);
        PendingIntent pIntent13 = PendingIntent.getBroadcast(ctx, 0, countIntent10,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.tv_2, pIntent13);
        PendingIntent pIntent14 = PendingIntent.getBroadcast(ctx, 0, countIntent10,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.tv_3, pIntent14);
        PendingIntent pIntent15 = PendingIntent.getBroadcast(ctx, 0, countIntent10,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.tv_4, pIntent15);

        db.close();
        Log.d(TAG, "updateWidget: id" + widgetID);
        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        int mAppWidgetId;
        Bundle extras = intent.getExtras();
        if (Current_date == null)
            Current_date = Calendar.getInstance().getTime();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                Log.d(TAG, "onReceive: ");
                db = new DB(context);
                db.open();
                ContentValues cv = new ContentValues();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Cursor c = db.getAllData();
                if (c.getCount() == 0) {
                    Calendar ms = Calendar.getInstance();
                    ms.setTime(Current_date);
                    cv.put("Date", sdf.format(Current_date));
                    cv.put("Date_ms", ms.getTimeInMillis());
                    cv.put("Count1", "0");
                    cv.put("Count2", "0");
                    cv.put("Count3", "0");
                    cv.put("Count4", "0");
                    // вставляем запись и получаем ее ID
                    long rowID = db.addRec(cv);
                    Log.d(TAG, "initial insert. ID = " + rowID);
                }
                String selection = "Date = ? ";
                String selection_arg[] = new String[]{sdf.format(Current_date)};
                c = db.getSomeData(selection, selection_arg);
                if (c.getCount() == 0) {
                    Calendar ms = Calendar.getInstance();
                    ms.setTime(Current_date);
                    cv.put("Date", sdf.format(Current_date));
                    cv.put("Date_ms", ms.getTimeInMillis());
                    cv.put("Count1", "0");
                    cv.put("Count2", "0");
                    cv.put("Count3", "0");
                    cv.put("Count4", "0");
                    // вставляем запись и получаем ее ID
                    long rowID = db.addRec(cv);
                    Log.d(TAG, "insert if new date. ID = " + rowID);
                }
                int Count1Col, Count2Col, Count3Col, Count4Col, updCount;
                c = db.getSomeData(selection, selection_arg);

                c.moveToFirst();
                Count1Col = c.getInt(c.getColumnIndex("Count1"));
                Count2Col = c.getInt(c.getColumnIndex("Count2"));
                Count3Col = c.getInt(c.getColumnIndex("Count3"));
                Count4Col = c.getInt(c.getColumnIndex("Count4"));

                Calendar calendar;

                switch (intent.getAction()) {
                    case CLICK_BUTTON_1_inc:
                        Log.d(TAG, "onReceive: change count Button_1 +1");
                        cv.put("Count1", ++Count1Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_2_inc:
                        Log.d(TAG, "onReceive: change count Button_2 +1");
                        cv.put("Count2", ++Count2Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_3_inc:
                        Log.d(TAG, "onReceive: change count Button_3 +1");
                        cv.put("Count3", ++Count3Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_4_inc:
                        Log.d(TAG, "onReceive: change count Button_4 +1");
                        cv.put("Count4", ++Count4Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_1_dic:
                        Log.d(TAG, "onReceive: change count Button_1 -1");
                        if (Count1Col == 0) break;
                        cv.put("Count1", --Count1Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_2_dic:
                        Log.d(TAG, "onReceive: change count Button_2 -1");
                        if (Count2Col == 0) break;
                        cv.put("Count2", --Count2Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_3_dic:
                        Log.d(TAG, "onReceive: change count Button_3 -1");
                        if (Count3Col == 0) break;
                        cv.put("Count3", --Count3Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_BUTTON_4_dic:
                        Log.d(TAG, "onReceive: change count Button_4 -1");
                        if (Count4Col == 0) break;
                        cv.put("Count4", --Count4Col);
                        updCount = db.update(cv, selection, selection_arg);
                        Log.d(TAG, "updated rows count = " + updCount);
                        break;

                    case CLICK_ARROW_BUTTON_LEFT:
                        calendar = Calendar.getInstance();
                        //assert date1 != null;
                        calendar.setTime(Current_date);
                        calendar.add(Calendar.DATE, -1);
                        Date temp_date = null;
                        try {
                            temp_date = sdf.parse(Min_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        assert temp_date != null;
                        if (temp_date.before(calendar.getTime())) {
                            Current_date = calendar.getTime();
                            selection_arg[0] = sdf.format(Current_date);
                            c = db.getSomeData(selection, selection_arg);
                            if (c.getCount() == 0) {
                                Calendar ms = Calendar.getInstance();
                                ms.setTime(Current_date);
                                Log.d(TAG, "--- Insert in main_table: ---");
                                cv.put("Date", sdf.format(Current_date));
                                cv.put("Date_ms", ms.getTimeInMillis());
                                cv.put("Count1", "0");
                                cv.put("Count2", "0");
                                cv.put("Count3", "0");
                                cv.put("Count4", "0");
                                long rowID = db.addRec(cv);
                                Log.d(TAG, "row inserted, ID = " + rowID);
                            }
                        }
                        break;

                    case CLICK_ARROW_BUTTON_RIGHT:
                        Date now = Calendar.getInstance().getTime();
                        calendar = Calendar.getInstance();
                        //assert date1 != null;
                        calendar.setTime(Current_date);
                        calendar.add(Calendar.DATE, 1);

                        if (now.after(calendar.getTime())) {
                            Current_date = calendar.getTime();

                            selection_arg[0] = sdf.format(Current_date);
                            c = db.getSomeData(selection, selection_arg);

                            if (c.getCount() == 0) {
                                Log.d(TAG, "--- Insert in main_table: ---");
                                Calendar ms = Calendar.getInstance();
                                ms.setTime(Current_date);
                                cv.put("Date", sdf.format(Current_date));
                                cv.put("Date_ms", ms.getTimeInMillis());
                                cv.put("Count1", "0");
                                cv.put("Count2", "0");
                                cv.put("Count3", "0");
                                cv.put("Count4", "0");
                                long rowID = db.addRec(cv);
                                Log.d(TAG, "row inserted, ID = " + rowID);
                            }
                        }
                        break;

                    case CLICK_DATE_TEXT:
                        Log.d(TAG, "onReceive: CLICK_DATE_TEXT");
                        Intent i = new Intent(context, My_Activity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("Min_date", Min_date);
                        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                        context.startActivity(i);
                        break;

                    case CHANGE_DATE:
                        Log.d(TAG, "onReceive: CHANGE_DATE");
                        Bundle extra = intent.getExtras();
                        ArrayList<Integer> date = extra.getIntegerArrayList("date");
                        calendar = Calendar.getInstance();
                        assert date != null;
                        calendar.set(date.get(0), date.get(1), date.get(2));
                        Current_date = calendar.getTime();
                        selection_arg[0] = sdf.format(Current_date);
                        Log.d(TAG, "onReceive: receive_date" + selection_arg[0]);
                        c = db.getSomeData(selection, selection_arg);
                        Log.d(TAG, "onReceive: count^" + c.getCount());
                        if (c.getCount() == 0) {
                            Calendar ms = Calendar.getInstance();
                            ms.setTime(Current_date);
                            Log.d(TAG, "--- Insert in main_table: ---");
                            cv.put("Date", sdf.format(Current_date));
                            cv.put("Date_ms", ms.getTimeInMillis());
                            cv.put("Count1", "0");
                            cv.put("Count2", "0");
                            cv.put("Count3", "0");
                            cv.put("Count4", "0");
                            // вставляем запись и получаем ее ID
                            long rowID = db.addRec(cv);
                            Log.d(TAG, "row inserted, ID = " + rowID);
                        }
                        break;

                    case GO_TO_STATISTICS:
                        Log.d(TAG, "onReceive: CLICK_DATE_TEXT");
                        Intent Start_Statistics_Intent = new Intent(context, Statistics_Activity.class);
                        Start_Statistics_Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Start_Statistics_Intent);
                        break;

                    case AppWidgetManager.ACTION_APPWIDGET_DELETED:
                        Log.d(TAG, "onReceive: widget has be deleted");

                        break;

                    default:
                        Log.d(TAG, "onReceive: Message not receive\n" +
                                " action = " + intent.getAction());
                }

                c.close();
                db.close();
                updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId);
            }
        }
    }
}
