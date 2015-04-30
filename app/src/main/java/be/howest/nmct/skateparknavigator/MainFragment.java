package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    private OnMainFragmentListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    private Button button_show_map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        button_show_map = (Button) v.findViewById(R.id.button_show_map);

        button_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.DemandMapSkatepark();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMainFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnMainFragmentListener {
        public void DemandMapSkatepark();
    }

}
