package be.howest.nmct.skateparknavigator;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import be.howest.nmct.skateparknavigator.admin.Skatepark;
public class MapSkateparkFragment extends Fragment implements OnMapReadyCallback {


    private static final String EXTRA_NAME = "be.howest.nmct.skateparknavigator.NAME";
    private static final String EXTRA_PROVINCE = "be.howest.nmct.skateparknavigator.PROVINCE";

    MapFragment mMapFragment;

    public MapSkateparkFragment() {
        // Required empty public constructor
    }

    public static MapSkateparkFragment newInstance(String sName) {
        MapSkateparkFragment fragment = new MapSkateparkFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, sName);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MapSkateparkFragment newInstance(Skatepark.PROVINCE province) {
        MapSkateparkFragment fragment = new MapSkateparkFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_PROVINCE, province);
        fragment.setArguments(bundle);
        return fragment;
    }

    private static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //http://stackoverflow.com/questions/14083950/duplicate-id-tag-null-or-parent-id-with-another-fragment-for-com-google-android
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_skatepark_on_map, container, false);
        } catch (Exception ex) {
            /* map is already there, just return view as it is */
        }

        mMapFragment = (MapFragment) getFragmentManagerCorrect().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        return view;
    }

    private FragmentManager getFragmentManagerCorrect() {
        FragmentManager fm;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            fm = getFragmentManager();
        } else {

            fm = getChildFragmentManager();
        }
        return fm;
    }

    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        Skatepark.PROVINCE province = (Skatepark.PROVINCE) getArguments().getSerializable(EXTRA_PROVINCE);

        //als er een provincie gekozen is: alle skateparks tonen op kaart van die provincie
        if (province != null && MainActivity.skateparks != null) {

            LatLng latLngProvince = new LatLng(province.getLattitude(), province.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngProvince, province.getZoomlevel()));

            for (Skatepark skatepark : MainActivity.skateparks) {

                if (province.getName().equals(skatepark.getProvince())) {
                    LatLng latLngSkatepark = new LatLng(skatepark.getLattitude(), skatepark.getLongitude());

                    googleMap.addMarker(new MarkerOptions()
                            .position(latLngSkatepark)
                            .title(skatepark.getName()));
                }
            }

        //anders enkel kaar tonen van dat skatepark
        } else {

            String sName = getArguments().getString(EXTRA_NAME);
            Skatepark skatepark = Skatepark.getSkateparkFromName(sName, MainActivity.skateparks);

            LatLng latLng = new LatLng(skatepark.getLattitude(), skatepark.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(sName));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //http://stackoverflow.com/questions/14083950/duplicate-id-tag-null-or-parent-id-with-another-fragment-for-com-google-android
        //enkel noodzakelijk voor api 21, anders foutmelding
        if (mMapFragment != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //http://stackoverflow.com/questions/12450024/can-not-perform-this-action-after-onsaveinstancestate-why-am-i-getting-this
            getChildFragmentManager().beginTransaction().remove(mMapFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(MainActivity.fragment_titles[1] + " " + getNameOrProvince());
    }

    private String getNameOrProvince() {
        Skatepark.PROVINCE province = (Skatepark.PROVINCE) getArguments().getSerializable(EXTRA_PROVINCE);
        if (province != null) return province.getName();
        else return getArguments().getString(EXTRA_NAME);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);
    }
}
