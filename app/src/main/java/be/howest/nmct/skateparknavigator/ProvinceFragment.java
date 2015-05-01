package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import be.howest.nmct.skateparknavigator.admin.Skatepark;

public class ProvinceFragment extends ListFragment {

    private OnFragmentProvinceListener mListener;
    private ProvinceAdapter myAdapter;

    public ProvinceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_province, container, false);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myAdapter = new ProvinceAdapter();
        setListAdapter(myAdapter);
    }

    class ProvinceAdapter extends ArrayAdapter<Skatepark.PROVINCE> {

        public ProvinceAdapter() {
            super(getActivity(), R.layout.row_province, R.id.textview_name_province, Skatepark.PROVINCE.values());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            final Skatepark.PROVINCE province = Skatepark.PROVINCE.values()[position];

            ViewHolder holder = (ViewHolder) row.getTag();

            if (holder == null) {
                holder = new ViewHolder(row);
                row.setTag(holder);
            }

            TextView textview_name_province = holder.textview_name_province;
            textview_name_province.setText(province.getName());

            ImageButton button_show_map_province = holder.button_show_map_province;
            button_show_map_province.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.DemandMapProvince(province);
                }
            });

            return row;
        }
    }

    class ViewHolder {
        public TextView textview_name_province = null;
        public ImageButton button_show_map_province = null;

        public ViewHolder (View row) {
            this.textview_name_province = (TextView) row.findViewById(R.id.textview_name_province);
            this.button_show_map_province = (ImageButton) row.findViewById(R.id.button_show_map_province);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentProvinceListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentProvinceListener {
        public void DemandMapProvince(Skatepark.PROVINCE province);
    }
}
