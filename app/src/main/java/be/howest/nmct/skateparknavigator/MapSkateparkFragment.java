package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import be.howest.nmct.skateparknavigator.admin.Skatepark;
import be.howest.nmct.skateparknavigator.loader.Contract;
import be.howest.nmct.skateparknavigator.loader.SkateparkLoader;

public class MapSkateparkFragment extends Fragment implements OnMapReadyCallback {

    private static final String EXTRA_LATTITUDE = "be.howest.nmct.skateparknavigator.LATTITUDE";
    private static final String EXTRA_LONGITUDE = "be.howest.nmct.skateparknavigator.LONGITUDE";
    private static final String EXTRA_NAME = "be.howest.nmct.skateparknavigator.NAME";
    private static final String EXTRA_PROVINCE = "be.howest.nmct.skateparknavigator.PROVINCE";

    public MapSkateparkFragment() {
        // Required empty public constructor
    }

    public static MapSkateparkFragment newInstance(double dLattitude, double dLongitude, String sName) {
        MapSkateparkFragment fragment = new MapSkateparkFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(EXTRA_LATTITUDE, dLattitude);
        bundle.putDouble(EXTRA_LONGITUDE, dLongitude);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skatepark_on_map, container, false);

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
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
            Double dLattidue = getArguments().getDouble(EXTRA_LATTITUDE);
            Double dLongitude = getArguments().getDouble(EXTRA_LONGITUDE);

            LatLng latLng = new LatLng(dLattidue, dLongitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(getArguments().getString(EXTRA_NAME)));
        }
    }
}
