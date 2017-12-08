package com.example.deepakrattan.broadcastwidgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class BroadcastWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_BROADCASTWIDGET = "ACTION_BROADCASTWIDGETSAMPLE";
    private static int mCounter = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = "Broadcast Widget";
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.broadcast_widget_provider);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intent = new Intent(context, BroadcastWidgetProvider.class);
        intent.setAction(ACTION_BROADCASTWIDGET);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(ACTION_BROADCASTWIDGET)) {
            mCounter++;

            //Construct the RemoteViews object
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.broadcast_widget_provider);
            remoteViews.setTextViewText(R.id.appwidget_text, String.valueOf(mCounter));

            //We don't have widget id .
            //reaching widget

            ComponentName appWidget = new ComponentName(context,BroadcastWidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            //instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget,remoteViews);

        }

    }
}

