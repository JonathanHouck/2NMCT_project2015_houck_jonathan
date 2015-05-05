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
import java.net.URI;
import java.net.URL;

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

    private final String[] mColumnNames = new String[] {
            BaseColumns._ID,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_DESCRIPTION,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_STREET,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_POSTCODE,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_PROVINCE,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_WEBSITE,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_INDOOR,
            Contract.SkateparkColumns.COLUMN_SKATEPARK_FREE,
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
                    String description = "";
                    String street = "";
                    String city = "";
                    String postcode = "";
                    String province = "";
                    String website = "";
                    int capacity = 0;
                    int indoor = 0;
                    int free = 0;
                    double lattitude = 0.0;
                    double longitude = 0.0;

                    while (reader.hasNext()) {
                        String itemName = reader.nextName();

                        switch (itemName) {
                            case "name":
                                if (reader.peek().equals(JsonToken.STRING)) name = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "description":
                                if (reader.peek().equals(JsonToken.STRING)) description = reader.nextString();
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
                            case "postcode":
                                if (reader.peek().equals(JsonToken.STRING)) postcode = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "province":
                                if (reader.peek().equals(JsonToken.STRING)) province = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "website":
                                if (reader.peek().equals(JsonToken.STRING)) website = reader.nextString();
                                else reader.skipValue();
                                break;
                            case "capacity":
                                if (reader.peek().equals(JsonToken.NUMBER)) capacity = reader.nextInt();
                                else reader.skipValue();
                                break;
                            case "indoor":
                                if (reader.peek().equals(JsonToken.BOOLEAN)) {
                                    if (reader.nextBoolean()) indoor = 1;
                                    else indoor = 0;
                                }
                                else reader.skipValue();
                                break;
                            case "free":
                                if (reader.peek().equals(JsonToken.BOOLEAN)) {
                                    if (reader.nextBoolean()) free = 1;
                                    else free = 0;
                                }
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

                    int id = 1;
                    MatrixCursor.RowBuilder row = cursor.newRow();
                    row.add(id);
                    row.add(name);
                    row.add(description);
                    row.add(street);
                    row.add(city);
                    row.add(postcode);
                    row.add(province);
                    row.add(website);
                    row.add(capacity);
                    row.add(indoor);
                    row.add(free);
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