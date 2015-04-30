package be.howest.nmct.skateparknavigator.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.JsonReader;
import android.util.JsonToken;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import be.howest.nmct.skateparknavigator.R;
import be.howest.nmct.skateparknavigator.admin.Skatepark;

/*bij onlistenerclick
cursor c = (cursor) madapter.getitem(cursor)
cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME);*/


/**
 * Created by Jonathan on 22/04/2015.
 */
public class SkateparkLoader extends AsyncTaskLoader<Cursor> {
    private Cursor mCursor;
    private static Object lock = new Object();

    private Skatepark.PROVINCE province;

    private final String[] mColumnNames = new String[] {
            BaseColumns._ID,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY,};

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
            loadCursor();

            /*if (province == null) {
                loadCursor(SkateparkAdmin.getSkateparks());
            } else {
                loadCursor(SkateparkAdmin.getSkateparks(this.province));
            }*/
        }
        return mCursor;
    }

    public void loadCursor() {
        synchronized (lock) {
            if (mCursor != null) return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            InputStream input = null;
            JsonReader reader = null;

            try
            {
                input = Resources.getSystem().openRawResource(R.raw.skateparks);
                reader = new JsonReader(new InputStreamReader(input, "UTF-8"));

                //json file inlezen (rij per rij)
                reader.beginArray();
                while (reader.hasNext())
                {
                    reader.beginObject();
                    String name = "";
                    String city = "";
                    Skatepark.CAPACITY capacityEnum = Skatepark.CAPACITY.MEDIUM;

                    while (reader.hasNext()) {
                        String itemName = reader.nextName();

                        switch (itemName) {
                            case "name":
                                if (reader.peek().equals(JsonToken.STRING)) name = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "city":
                                if (reader.peek().equals(JsonToken.STRING)) city = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "capacity":
                                if (reader.peek().equals(JsonToken.STRING)) {
                                    int capacity = reader.nextInt();

                                    switch (capacity) {
                                        case 1:
                                            capacityEnum = Skatepark.CAPACITY.SMALL;
                                            break;
                                        case 2:
                                            capacityEnum = Skatepark.CAPACITY.MEDIUM;
                                            break;
                                        case 3:
                                            capacityEnum = Skatepark.CAPACITY.BIG;
                                            break;
                                        default:
                                            capacityEnum = Skatepark.CAPACITY.MEDIUM;
                                            break;
                                    }
                                }
                                else reader.skipValue();
                                break;
                            default:
                                reader.skipValue();
                                break;
                        }
                    }
                    //hier controle op provincie
                    int id = 1;
                    MatrixCursor.RowBuilder row = cursor.newRow();
                    row.add(id);
                    row.add(name);
                    row.add(city);
                    row.add(capacityEnum);
                    id++;

                    reader.endObject();
                }
                reader.endArray();

                mCursor = cursor;
            }
            catch (IOException ioEx)
            {
                ioEx.printStackTrace();
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioEx)
                {
                }

                try
                {
                    input.close();
                }
                catch (IOException ioEx)
                {
                }
            }
        }
    }
}