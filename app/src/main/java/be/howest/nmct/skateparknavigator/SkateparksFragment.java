package be.howest.nmct.skateparknavigator;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.howest.nmct.skateparknavigator.loader.Contract;

public class SkateparksFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SkateparksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skateparks, container, false);
        return v;
    }

    /*public class SkateparksAdapter extends RecyclerView.Adapter<SkateparksAdapter.SkateparkViewHolder> {

        Cursor cursorSkateparks;

        public SkateparksAdapter(Cursor cursorSkateparks) {
            this.cursorSkateparks = cursorSkateparks;
        }

        @Override
        public SkateparkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_studentenhuis, viewGroup, false);
            SkateparkViewHolder holder = new SkateparkViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(SkateparkViewHolder viewHolder, int position) {

            cursorSkateparks.moveToPosition(position);

            int colnr1 = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_NAME);
            int colnr2 = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_CITY);
            int colnr3 = cursorSkateparks.getColumnIndex(Contract.SkateparkColumns.COLUMN_SKATEPARK_CAPACITY);

            viewHolder.mTextView_straat.setText(cursorSkateparks.getString(colnr1));
            viewHolder.mTextView_huisnr.setText(cursorSkateparks.getString(colnr2));
            viewHolder.mTextView_gemeente.setText(cursorSkateparks.getString(colnr3));;
        }

        //dataset is null --> activitycreated eerst en daarna wordt loader ingeladen, dit moet omgekeer zijn
        @Override
        public int getItemCount() {
            return cursorSkateparks.getCount();
        }

        class SkateparkViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView_straat = null;
            public TextView mTextView_huisnr = null;
            public TextView mTextView_gemeente = null;
            public TextView mTextView_aantal_kamers = null;

            public SkateparkViewHolder(View itemView) {
                super(itemView);

                mTextView_straat = (TextView) itemView.findViewById(R.id.textView_straat);
                mTextView_huisnr = (TextView) itemView.findViewById(R.id.textView_huisnr);
                mTextView_gemeente = (TextView) itemView.findViewById(R.id.textView_gemeente);
                mTextView_aantal_kamers = (TextView) itemView.findViewById(R.id.textView_aantal_kamers);
            }
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
