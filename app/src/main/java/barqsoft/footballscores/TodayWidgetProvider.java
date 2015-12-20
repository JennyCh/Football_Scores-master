package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jenny on 12/18/2015.
 */
public class TodayWidgetProvider extends AppWidgetProvider {

    public static final String TOAST_ACTION = "com.example.android.barqsoft.footballscores.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.barqsoft.footballscores.EXTRA_ITEM";

    private static final String LOG_TAG = "TodayWidgetProvider";
    private ListView listView;

    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Touched view " + viewIndex, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.v("LAUNCH WIDGET",  "BEGIN");
        //TODO: this is hard coded
        Cursor data = context.getContentResolver().query(
                DatabaseContract.scores_table.buildScoreWithDate(),
                null,
                null,
                new String[]{"2015-12-19"},
                null
        );

        if (data == null) {
            Log.v("LOG_TAG", "data is null");
            return;
        }
        if (!data.moveToFirst()) {
            Log.v("LOG_TAG", "NO DATA");
            data.close();
            return;
        }



        for (int appWidgetId : appWidgetIds) {
           // int layoutId = R.layout.widget_small;
           // RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);

            Intent intent = new Intent(context, ListViewService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_small);
            //remoteViews.setTextViewText(R.id.team1, "1");
            remoteViews.setRemoteAdapter(R.id.widget_list_view, intent);

            remoteViews.setEmptyView(R.id.widget_list_view, R.id.empty_view);


            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);



            Intent toastIntent = new Intent(context, TodayWidgetProvider.class);
            // Set the action for the intent.
            // When the user touches a particular view, it will have the effect of
            // broadcasting TOAST_ACTION.
            toastIntent.setAction(TodayWidgetProvider.TOAST_ACTION);
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.widget_list_view, toastPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

            Log.v("LAUNCH WIDGET", "end");

      /*   //Working version of the widget with simple textViews

         String t1Data = data.getString(data.getColumnIndex(DatabaseContract.scores_table.HOME_COL));
            String t2Data = data.getString(data.getColumnIndex(DatabaseContract.scores_table.AWAY_COL));
            String timeData = data.getString(data.getColumnIndex(DatabaseContract.scores_table.TIME_COL));

            Log.v(LOG_TAG, t1Data + " | " + t2Data + " | " + timeData);

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.widget_small, null);

            views.setTextViewText(R.id.team1, t1Data);
            views.setTextViewText(R.id.team2, t2Data);
            views.setTextViewText(R.id.time, timeData);
            */


    /*
    //Not working attempt to create ListView in the widget

     LayoutInflater inflater = LayoutInflater.from(context);
            View viewFloat = inflater.inflate(R.layout.widget_small, null);
            this.listView = (ListView) viewFloat.findViewById(R.id.widget_list_view);
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(context, R.layout.widget_list_item, data, new String[]{
                    DatabaseContract.scores_table.HOME_COL,
                    DatabaseContract.scores_table.AWAY_COL,
                    DatabaseContract.scores_table.TIME_COL
            }, new int[]{
                    R.id.team1,
                    R.id.team2,
                    R.id.time
            },0);

            if(cursorAdapter == null){
                Log.v(LOG_TAG, "cursorAdapter is null");
            }

            views.setRemoteAdapter(layoutId, new Intent());*/




           /* Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);

           */

        }

        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }
}

