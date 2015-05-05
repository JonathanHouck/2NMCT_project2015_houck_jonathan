package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import be.howest.nmct.skateparknavigator.admin.Skatepark;

public class SkateparkDetailFragment extends Fragment {

    private static final String EXTRA_NAME = "be.howest.nmct.skateparknavigator.NAME";

    public SkateparkDetailFragment() {
        // Required empty public constructor
    }

    public static SkateparkDetailFragment newInstance(String name) {
        SkateparkDetailFragment fragment = new SkateparkDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skatepark_detail, container, false);

        String sName = getArguments().getString(EXTRA_NAME);
        final Skatepark skatepark = Skatepark.getSkateparkFromName(sName, MainActivity.skateparks);

        ViewHolder holder = new ViewHolder(v);
        holder.textView_name_detail.setText(skatepark.getName());

        holder.button_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebsite(skatepark.getWebsite());
            }
        });

        holder.textView_description.setText(skatepark.getDescription());
        holder.textView_street_detail.setText(skatepark.getStreet());
        holder.textView_postcode.setText(skatepark.getPostcode());
        holder.textView_city_detail.setText(skatepark.getCity());
        holder.imageButton_map_pin_detail.setImageResource(R.drawable.map_pin);

        if (skatepark.isFree()) holder.textView_price.setText("- Gratis");
        else holder.textView_price.setText("- Betalend");

        if (skatepark.isIndoor()) holder.textView_indoor.setText("- Indoor");
        else holder.textView_indoor.setText("- Outdoor");

        holder.textView_capacity_detail.setText(giveCapacity(skatepark.getCapacity()));

        return v;
    }

    private void showWebsite(String website) {
        Uri webpage = Uri.parse(website);
        Intent browser = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(browser);
    }

    public String giveCapacity(int capacity) {
        switch (capacity) {
            case 1:
                return "Klein skatepark";
            case 2:
                return "Middelgroot skatepark";
            case 3:
                return "Groot skatepark";
            default:
                return "Grootte niet gevonden";
        }
    }

    class ViewHolder {
        public TextView textView_name_detail = null;
        public Button button_website = null;
        public TextView textView_description = null;
        public TextView textView_street_detail = null;
        public TextView textView_postcode = null;
        public TextView textView_city_detail = null;
        public ImageButton imageButton_map_pin_detail = null;
        public TextView textView_price = null;
        public TextView textView_indoor = null;
        public TextView textView_capacity_detail = null;

        public ViewHolder (View row) {
            this.textView_name_detail = (TextView) row.findViewById(R.id.textView_name_detail);
            this.button_website = (Button) row.findViewById(R.id.button_website);
            this.textView_description = (TextView) row.findViewById(R.id.textView_description);
            this.textView_street_detail = (TextView) row.findViewById(R.id.textView_street_detail);
            this.textView_postcode = (TextView) row.findViewById(R.id.textView_postcode);
            this.textView_city_detail = (TextView) row.findViewById(R.id.textView_city_detail);
            this.imageButton_map_pin_detail = (ImageButton) row.findViewById(R.id.imageButton_map_pin_detail);
            this.textView_price = (TextView) row.findViewById(R.id.textView_price);
            this.textView_indoor = (TextView) row.findViewById(R.id.textView_indoor);
            this.textView_capacity_detail = (TextView) row.findViewById(R.id.textView_capacity_detail);
        }
    }
}
