package com.lihoy21gmail.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class My_Widget extends AppWidgetProvider {
    final static String TAG = "myLog";
    final static String CLICK_BUTTON_1 = "com.lihoy21gmail.mywidge.click_button1";
    final static String CLICK_BUTTON_2 = "com.lihoy21gmail.mywidge.click_button2";
    final static String CLICK_BUTTON_3 = "com.lihoy21gmail.mywidge.click_button3";
    final static String CLICK_BUTTON_4 = "com.lihoy21gmail.mywidge.click_button4";
    final static String CLICK_ARROW_BUTTON_LEFT = "com.lihoy21gmail.mywidge.click_arrow_button1";
    final static String CLICK_ARROW_BUTTON_RIGHT = "com.lihoy21gmail.mywidge.click_arrow_button2";

    final static String WIDGET_PREF = "widget_pref";
    final static String WIDGET_COUNT = "widget_count";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        // обновляем все экземпляры
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted:  ");
        // Удаляем Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(
                WIDGET_PREF, Context.MODE_PRIVATE).edit();
        editor.remove(WIDGET_COUNT).apply();
    }

    static void updateWidget(Context ctx, AppWidgetManager appWidgetManager, int widgetID) {
        SharedPreferences sp = ctx.getSharedPreferences(
                WIDGET_PREF, Context.MODE_PRIVATE);
        String count[] = new String[4];

        // Читаем счетчик
        for (int i = 0; i < 4; i++)
            count[i] = String.valueOf(sp.getInt(WIDGET_COUNT + i, 0));

        // Button_1
        RemoteViews widgetView = new RemoteViews(ctx.getPackageName(),
                R.layout.widget);
        widgetView.setTextViewText(R.id.button_1, count[0]);
        Intent countIntent1 = new Intent(ctx, My_Widget.class);
        countIntent1.setAction(CLICK_BUTTON_1);
        countIntent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent1 = PendingIntent.getBroadcast(ctx, 0, countIntent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_1, pIntent1);

        // Button_2
        widgetView.setTextViewText(R.id.button_2, count[1]);
        Intent countIntent2 = new Intent(ctx, My_Widget.class);
        countIntent2.setAction(CLICK_BUTTON_2);
        countIntent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent2 = PendingIntent.getBroadcast(ctx, 0, countIntent2,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_2, pIntent2);

        // Button_3
        widgetView.setTextViewText(R.id.button_3, count[2]);
        Intent countIntent3 = new Intent(ctx, My_Widget.class);
        countIntent3.setAction(CLICK_BUTTON_3);
        countIntent3.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent3 = PendingIntent.getBroadcast(ctx, 0, countIntent3,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_3, pIntent3);

        // Button_4
        widgetView.setTextViewText(R.id.button_4, count[3]);
        Intent countIntent4 = new Intent(ctx, My_Widget.class);
        countIntent4.setAction(CLICK_BUTTON_4);
        countIntent4.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent4 = PendingIntent.getBroadcast(ctx, 0, countIntent4,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.button_4, pIntent4);

        // Arrow_Button_Left
        widgetView.setTextViewText(R.id.button_4, count[3]);
        Intent arrow_Intent1 = new Intent(ctx, My_Widget.class);
        arrow_Intent1.setAction(CLICK_ARROW_BUTTON_LEFT);
        arrow_Intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent5 = PendingIntent.getBroadcast(ctx, 0, arrow_Intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.left_arrow_button, pIntent5);

        // Arrow_Button_Right
        widgetView.setTextViewText(R.id.button_4, count[3]);
        Intent arrow_Intent2 = new Intent(ctx, My_Widget.class);
        arrow_Intent2.setAction(CLICK_ARROW_BUTTON_RIGHT);
        arrow_Intent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent6 = PendingIntent.getBroadcast(ctx, 0, arrow_Intent2,
                PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.right_arrow_button, pIntent6);

        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        int mAppWidgetId;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            if(mAppWidgetId!= AppWidgetManager.INVALID_APPWIDGET_ID) {
                // Читаем значение счетчика, увеличиваем на 1 и записываем
                SharedPreferences sp = context.getSharedPreferences(
                        WIDGET_PREF, Context.MODE_PRIVATE);
                int cnt;
                switch (intent.getAction()) {
                    case CLICK_BUTTON_1:
                        cnt = sp.getInt(WIDGET_COUNT + 0, 0);
                        sp.edit().putInt(WIDGET_COUNT + 0, ++cnt).apply();
                        Log.d(TAG, "onReceive: change count Button_1 +1");
                        break;
                    case CLICK_BUTTON_2:
                        cnt = sp.getInt(WIDGET_COUNT + 1, 0);
                        sp.edit().putInt(WIDGET_COUNT + 1, ++cnt).apply();
                        Log.d(TAG, "onReceive: change count Button_2 +1");
                        break;
                    case CLICK_BUTTON_3:
                        cnt = sp.getInt(WIDGET_COUNT + 2, 0);
                        sp.edit().putInt(WIDGET_COUNT + 2, ++cnt).apply();
                        Log.d(TAG, "onReceive: change count Button_3 +1");
                        break;
                    case CLICK_BUTTON_4:
                        cnt = sp.getInt(WIDGET_COUNT + 3, 0);
                        sp.edit().putInt(WIDGET_COUNT + 3, ++cnt).apply();
                        Log.d(TAG, "onReceive: change count Button_4 +1");
                        break;
                    case CLICK_ARROW_BUTTON_LEFT:
                        Log.d(TAG, "onReceive: change date -1");
                        break;
                    case CLICK_ARROW_BUTTON_RIGHT:
                        Log.d(TAG, "onReceive: change date +1");
                        break;
                    default:
                        Log.d(TAG, "onReceive: Message not receive\n action = " + intent.getAction());
                }
                // Обновляем виджет
                updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId);
            }
        }
    }
}
