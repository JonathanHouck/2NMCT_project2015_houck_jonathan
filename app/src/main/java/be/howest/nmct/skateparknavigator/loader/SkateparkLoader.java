package be.howest.nmct.skateparknavigator.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

import java.util.List;
import java.util.Objects;

import be.howest.nmct.skateparknavigator.admin.Skatepark;
import be.howest.nmct.skateparknavigator.data.SkateparkAdmin;

/**
 * Created by Jonathan on 22/04/2015.
 */
public class SkateparkLoader extends AsyncTaskLoader<Cursor> {
    private Cursor mCursor;
    private Skatepark.PROVINCE province;

    private final String[] mColumnNames = new String[] {
            BaseColumns._ID,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY };

    private static Object lock = new Object();

    public SkateparkLoader(Context context) {
        super(context);
    }

    public SkateparkLoader(Context context, Skatepark.PROVINCE province) {
        super(context);
        this.province = province;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        if (mCursor == null) {
            if (province == null) {
                loadCursor(SkateparkAdmin.getSkateparks());
            } else {
                loadCursor(SkateparkAdmin.getSkateparks(this.province));
            }
        }

        return mCursor;
    }

    private void loadCursor(List<Skatepark> skateparks) {

        synchronized (lock) {
            if (mCursor != null) return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            int id = 1;

            for (Skatepark skatepark : skateparks) {
                MatrixCursor.RowBuilder row = cursor.newRow();
                row.add(id);
                row.add(skatepark.getName());
                row.add(skatepark.getCapacity());
                row.add(skatepark.getCity());
                id++;
            }
            mCursor = cursor;
        }
    }
}