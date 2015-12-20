package barqsoft.footballscores;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Jenny on 12/19/2015.
 */
public class WidgetScoresAdapter extends SimpleCursorAdapter {

    private static final String LOG_TAG = "WidgetScoresAdapter";
    LayoutInflater mInflater;


    public WidgetScoresAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

/*    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        Log.v(LOG_TAG, "new view");
        return LayoutInflater.from(context).inflate(R.layout.widget_list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.v(LOG_TAG, "bind view");
        *//*LayoutInflater vi = (LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.widget_small, null);*//*

        if(view == null){
            Log.v(LOG_TAG, "bind view, view is empty");
        }

        TextView team1TextView = (TextView)view.findViewById(R.id.team1);
        TextView team2TeaxView = (TextView) view.findViewById(R.id.team2);
        TextView timeTextView = (TextView) view.findViewById(R.id.time);

        team1TextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_COL)));
        team2TeaxView.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_COL)));
        timeTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.TIME_COL)));
    }*/

/*    @Override
    public int getCount() {
        if (getCursor() == null) {
            return 0;
        }
        return super.getCount();
    }*/
}
