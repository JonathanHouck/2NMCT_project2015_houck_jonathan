package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements SkateparksFragment.OnFragmentSkateparksListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SkateparksFragment(), "SkateparksFragment")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void ShowMapSkatepark(double dLattiude, double dLongitude, String sName) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        MapSkateparkFragment fragment = MapSkateparkFragment.newInstance(dLattiude, dLongitude, sName);
        fragmentTransaction.replace(R.id.container, fragment, "MapSkateparkFragment");
        fragmentTransaction.addToBackStack("show_new_map");
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
}
