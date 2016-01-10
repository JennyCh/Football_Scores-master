package barqsoft.footballscores;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RemoteViews;

/**
 * Created by Jenny on 12/19/2015.
 */
public class TodayWidgetIntentService extends IntentService{

    private static final String LOG_TAG = "TodayWidgetIntentService";
    private ListView listView;

    //Must have a contractor
    public TodayWidgetIntentService(){
        super("TodayWidgetIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(Intent intent) {

     /*   AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, TodayWidgetProvider.class));

        Cursor data = getContentResolver().query(DatabaseContract.scores_table.buildScoreWithDate(),
                null,
                DatabaseContract.scores_table.DATE_COL + "LIKE = ?",
                new String[]{"'2015-12-19'"},
                null
        );

        if (data == null){
            Log.v("LOG_TAG", "data is null");
            return;
        }
        if (!data.moveToFirst()) {
            Log.v("LOG_TAG", "NO DATA");
            data.close();
            return;
        }

        WidgetScoresAdapter widgetScoresAdapter = new WidgetScoresAdapter(this, data, false);
        this.listView.setAdapter(widgetScoresAdapter);

        int team1 = data.getColumnIndex(DatabaseContract.scores_table.HOME_COL);
        int team2 = data.getColumnIndex(DatabaseContract.scores_table.AWAY_COL);
        int time = data.getColumnIndex(DatabaseContract.scores_table.DATE_COL);

        for (int appWidgetId: appWidgetIds){
            int layoutId = R.layout.widget_small;
            RemoteViews views = new RemoteViews(getPackageName(), layoutId);

            String team1Data;
            String team2Data;
            String timeData;
            for (int i = 0; i < data.getColumnCount(); i++) {

                team1Data = data.getString(team1);
                team2Data = data.getString(team1);
                timeData = data.getString(team1);

               // Log.v(LOG_TAG, team1Data + team2Data + "|" + timeData);
                views.setTextViewText(R.id.team1, team1Data);
                views.setTextViewText(R.id.team2, team2Data);
                views.setTextViewText(R.id.time, timeData);
            }

            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,launchIntent,0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
            Log.v("LAUNCH WIDGET",  "end");*/
        }


    }

