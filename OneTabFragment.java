package com.lihoy21gmail.mywidget;

import android.content.Context;
import android.support.v4.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OneTabFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    final static String TAG = "myLog";
    ListView lvData;
    DB db;
    SimpleCursorAdapter scAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DB(getContext());
        db.open();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_of_tab_1, container, false);
        String[] from = new String[]{DB.COLUMN_DATE, DB.COLUMN_COUNT1, DB.COLUMN_COUNT2,
                DB.COLUMN_COUNT3, DB.COLUMN_COUNT4};
        int[] to = new int[]{R.id.etDate, R.id.etCount1, R.id.etCount2, R.id.etCount3,
                R.id.etCount4};

        // создааем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(getContext(), R.layout.item, null, from, to, 0);
        lvData = (ListView) v.findViewById(R.id.lvData);
        if (lvData == null)
            Log.d(TAG, "onCreate: lvDate = null");
        if (scAdapter == null)
            Log.d(TAG, "onCreate: scAdapter = null");
        lvData.setAdapter(scAdapter);


        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
        return new MyCursorLoader(getContext(), db);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    static class MyCursorLoader extends CursorLoader {
        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            return db.getAllData();
        }

    }
}
