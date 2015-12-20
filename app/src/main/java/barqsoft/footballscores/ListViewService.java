package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny on 12/19/2015.
 */
public class ListViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.v("ListViewService", "onGetViewFactory");
        return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
        private static final int mCount = 10;
        private List<WidgetItem> mWidgetItems = new ArrayList<WidgetItem>();
        private Context context;
        private int mAppWidgetId;

        public ListViewRemoteViewsFactory(Context context, Intent intent){
            Log.v("ListViewService", "ListViewRemoteViewsFactory");
            this.context = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        public void onCreate(){
            Log.v("ListViewService", "onCreate");
            for (int i = 0; i < mCount; i++){
                mWidgetItems.add(new WidgetItem(i + "!"));
                //getViewAt(i);
            }
            for(int a = 0; a < mWidgetItems.size(); a++){
                Log.v("ITEMS IN THE LIST", mWidgetItems.get(a).getText());
            }

        }
        public RemoteViews getViewAt(int position) {
            Log.v("ListViewService", "getViewAt");
            // position will always range from 0 to getCount() - 1.

            // Construct a RemoteViews item based on the app widget item XML file, and set the
            // text based on the position.
            Log.v("ITEM ", mWidgetItems.get(position).getText());
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
            rv.setTextViewText(R.id.team1, mWidgetItems.get(position).getText());

            // Next, set a fill-intent, which will be used to fill in the pending intent template
            // that is set on the collection view in StackWidgetProvider.
            Bundle extras = new Bundle();
            extras.putInt(TodayWidgetProvider.EXTRA_ITEM, position);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            // Make it possible to distinguish the individual on-click
            // action of a given item
            rv.setOnClickFillInIntent(R.id.widget_list_view, fillInIntent);

                        // Return the RemoteViews object.
            return rv;
        }

        @Override
        public int getCount() {
            //TODO: must return the number of items, because if 0 by default, you will always end up with an empty list
            return mWidgetItems.size();
        }

        @Override
        public void onDataSetChanged() {
            Log.v("ListViewService", "onDataSetChanged");
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            //TODO: must fix initial 0 value, otherwise constant "Looading..." on each item
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
