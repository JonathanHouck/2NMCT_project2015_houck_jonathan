package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import be.howest.nmct.skateparknavigator.admin.Skatepark;
import be.howest.nmct.skateparknavigator.loader.Contract;
import be.howest.nmct.skateparknavigator.loader.SkateparkLoader;

public class MainActivity extends ActionBarActivity implements SkateparksFragment.OnFragmentSkateparksListener, ProvinceFragment.OnFragmentProvinceListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static List<Skatepark> skateparks = new ArrayList<>();

    private DrawerLayout dl;
    public static String[] fragment_titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment_titles = getResources().getStringArray(R.array.title_fragments);

        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(0, null, this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SkateparksFragment(), "SkateparksFragment")
                    .commit();
        }

        fragment_titles = getResources().getStringArray(R.array.title_fragments);
        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void DemandMapSkatepark(double dLattiude, double dLongitude, String sName) {
        ShowMapSkatepark(dLattiude, dLongitude, sName);
    }

    @Override
    public void DemandSkateparkDetail(String sName) {
        ShowSkateparkDetail(sName);
    }

    private void ShowSkateparkDetail(String sName) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        SkateparkDetailFragment fragment = SkateparkDetailFragment.newInstance(sName);
        fragmentTransaction.replace(R.id.container, fragment, "SkateparkDetailFragment");
        fragmentTransaction.addToBackStack("show_skatepark_detail");
        fragmentTransaction.commit();
    }

    public void ShowMapSkatepark(double dLattiude, double dLongitude, String sName) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        MapSkateparkFragment fragment = MapSkateparkFragment.newInstance(dLattiude, dLongitude, sName);
        fragmentTransaction.replace(R.id.container, fragment, "MapSkateparkFragment");
        fragmentTransaction.addToBackStack("show_new_map");
        fragmentTransaction.commit();
    }

    @Override
    public void DemandMapProvince(Skatepark.PROVINCE province) {
        ShowMapProvince(province);
    }

    public void ShowMapProvince(Skatepark.PROVINCE province) {
        dl.closeDrawer(Gravity.LEFT);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        MapSkateparkFragment fragment = MapSkateparkFragment.newInstance(province);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //http://stackoverflow.com/questions/27029998/actionbaractivity-back-button-not-popping-from-backstack
    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new SkateparkLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        while (c.moveToNext()) {
            int colnr1 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME);
            int colnr2 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_DESCRIPTION);
            int colnr3 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_STREET);
            int colnr4 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY);
            int colnr5 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_POSTCODE);
            int colnr6 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_PROVINCE);
            int colnr7 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_WEBSITE);
            int colnr8 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY);
            int colnr9 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_INDOOR);
            int colnr10 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_FREE);
            int colnr11 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_LATTITUDE);
            int colnr12 = c.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_LONGITUDE);

            //http://stackoverflow.com/questions/4088080/android-sqlite-get-boolean-from-database
            boolean bIndoor = c.getInt(colnr9) > 0;
            boolean bFree = c.getInt(colnr10) > 0;

            Skatepark sp = new Skatepark(
                    c.getString(colnr1),
                    c.getString(colnr2),
                    c.getString(colnr3),
                    c.getString(colnr4),
                    c.getString(colnr5),
                    c.getString(colnr6),
                    c.getString(colnr7),
                    c.getInt(colnr8),
                    bIndoor,
                    bFree,
                    c.getDouble(colnr11),
                    c.getDouble(colnr12));
            skateparks.add(sp);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
