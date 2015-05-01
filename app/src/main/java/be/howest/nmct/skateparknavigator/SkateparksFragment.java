package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import be.howest.nmct.skateparknavigator.admin.Skatepark;
import be.howest.nmct.skateparknavigator.loader.Contract;
import be.howest.nmct.skateparknavigator.loader.SkateparkLoader;

public class SkateparksFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private OnFragmentSkateparksListener mListener;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mSkateparksRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public SkateparksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skateparks, container, false);

        mSkateparksRecyclerView = (RecyclerView) v.findViewById(R.id.skateparks_recycler_view);
        mSkateparksRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mSkateparksRecyclerView.setLayoutManager(mLayoutManager);

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new SkateparkLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter = new SkateparksAdapter(data);
        mSkateparksRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter = new SkateparksAdapter(null);
    }

    public class SkateparksAdapter extends RecyclerView.Adapter<SkateparksAdapter.SkateparkViewHolder> {

        Cursor cursorSkateparks;

        public SkateparksAdapter(Cursor cursorSkateparks) {
            this.cursorSkateparks = cursorSkateparks;
        }

        @Override
        public SkateparkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_skatepark, viewGroup, false);
            SkateparkViewHolder holder = new SkateparkViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final SkateparkViewHolder viewHolder, int position) {

            cursorSkateparks.moveToPosition(position);

            int colnr1 = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME);
            int colnr2 = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY);
            int colnr3 = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY);

            viewHolder.mTextView_name.setText(cursorSkateparks.getString(colnr1));
            viewHolder.mTextView_city.setText(cursorSkateparks.getString(colnr2));
            viewHolder.mImageView_capacity.setImageResource(giveResourceIdCapacity(cursorSkateparks.getInt(colnr3)));

            viewHolder.mButton_google_maps.setTag(cursorSkateparks.getPosition());

            viewHolder.mButton_google_maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = (Integer) viewHolder.mButton_google_maps.getTag();
                    cursorSkateparks.moveToPosition(position);
                    int colnrName = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME);
                    int colnrLattitude = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_LATTITUDE);
                    int colnrLongitude = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_LONGITUDE);

                    mListener.DemandMapSkatepark(cursorSkateparks.getDouble(colnrLattitude), cursorSkateparks.getDouble(colnrLongitude), cursorSkateparks.getString(colnrName));
                }
            });
        }

        public int giveResourceIdCapacity(int capacity) {
            switch (capacity) {
                case 1:
                    return R.mipmap.capacity_1;
                case 2:
                    return R.mipmap.capacity_2;
                case 3:
                    return R.mipmap.capacity_3;
                default:
                    return R.mipmap.capacity_1;
                }
        }

        @Override
        public int getItemCount() {
            return cursorSkateparks.getCount();
        }

        class SkateparkViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView_name = null;
            public TextView mTextView_city = null;
            public ImageView mImageView_capacity = null;
            public ImageButton mButton_google_maps = null;

            public SkateparkViewHolder(View itemView) {
                super(itemView);

                mTextView_name = (TextView) itemView.findViewById(R.id.textView_name);
                mTextView_city = (TextView) itemView.findViewById(R.id.textView_city);
                mImageView_capacity = (ImageView) itemView.findViewById(R.id.imageView_capacity);
                mButton_google_maps = (ImageButton) itemView.findViewById(R.id.button_google_maps);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentSkateparksListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentSkateparksListener {
        public void DemandMapSkatepark(double dLattidue, double dLongtiude, String sName);
    }
}
