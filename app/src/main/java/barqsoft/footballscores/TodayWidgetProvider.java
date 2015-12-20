package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Jenny on 12/18/2015.
 */
public class TodayWidgetProvider extends AppWidgetProvider {

    private static final String LOG_TAG = "TodayWidgetProvider";
    private ListView listView;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.v("LAUNCH WIDGET",  "BEGIN");
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
            int layoutId = R.layout.widget_small;
            RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);


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

            this.listView.setAdapter(cursorAdapter);

            Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);

            Log.v("LAUNCH WIDGET", "end");

        }
    }
}

