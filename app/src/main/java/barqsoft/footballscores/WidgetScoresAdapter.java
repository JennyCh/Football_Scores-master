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

}
