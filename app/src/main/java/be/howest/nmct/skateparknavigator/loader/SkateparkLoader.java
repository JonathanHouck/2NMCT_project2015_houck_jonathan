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
            Contract.SkateparkColumns.COLUMN_SKATEPARK_STREET,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_LATTITUDE,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_LONGITUDE};

    public SkateparkLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        //super.onStartLoading();

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
                input = getContext().getResources().openRawResource(R.raw.skateparks);
                reader = new JsonReader(new InputStreamReader(input, "UTF-8"));

                //json file inlezen (rij per rij)
                reader.beginArray();
                while (reader.hasNext())
                {
                    reader.beginObject();
                    String name = "";
                    String street = "";
                    String city = "";
                    int capacity = 0;
                    double lattitude = 0.0;
                    double longitude = 0.0;

                    while (reader.hasNext()) {
                        String itemName = reader.nextName();

                        switch (itemName) {
                            case "name":
                                if (reader.peek().equals(JsonToken.STRING)) name = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "street":
                                if (reader.peek().equals(JsonToken.STRING)) street = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "city":
                                if (reader.peek().equals(JsonToken.STRING)) city = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "capacity":
                                if (reader.peek().equals(JsonToken.NUMBER)) capacity = reader.nextInt();
                                else reader.skipValue();
                                break;
                            case "lattitude":
                                if (reader.peek().equals(JsonToken.NUMBER)) lattitude = reader.nextDouble();
                                break;
                            case "longitude":
                                if (reader.peek().equals(JsonToken.NUMBER)) longitude = reader.nextDouble();
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
                    row.add(street);
                    row.add(city);
                    row.add(capacity);
                    row.add(lattitude);
                    row.add(longitude);
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