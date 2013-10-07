package com.elimak.chap6tut01;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elimak on 10/4/13.
 */
public class PropertyListFragment extends ListFragment {

    private static final String ARGUMENTS_KEY_PROPERTIES = "properties";

    public static PropertyListFragment newInstance(ArrayList<Property> properties){
        final Bundle args = new Bundle();
        args.putParcelableArrayList(ARGUMENTS_KEY_PROPERTIES, properties);

        final PropertyListFragment f = new PropertyListFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final List<Property> properties = getArguments().getParcelableArrayList(ARGUMENTS_KEY_PROPERTIES);
        setListAdapter(new PropertyListAdapter(getActivity(), properties));
    }

    private class PropertyListAdapter extends ArrayAdapter<Property> {

        private final LayoutInflater mInflater;

        private final String mBath;
        private final String mBed;
        private final String mSqFt;

        public PropertyListAdapter(Context context, List<Property> objects) {
            super(context, -1, objects);
            mInflater = LayoutInflater.from(context);
            final Resources res = context.getResources();
            mBath = " " + res.getString(R.string.bath);
            mBed = " "+res.getString(R.string.bed);
            mSqFt = " "+res.getString(R.string.sq_ft);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Property property = getItem(position);
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.property_listitem, parent, false);
            }

            TextView tv = (TextView) convertView.findViewById(R.id.city);
            tv.setText(property.getCity() + ", "+property.getState());

            tv = (TextView) convertView.findViewById(R.id.price);
            tv.setText(property.getPrice());

            tv = (TextView) convertView.findViewById(R.id.street);
            tv.setText(property.getStreetAddress());

            tv = (TextView) convertView.findViewById(R.id.beds);
            tv.setText(property.getBedroomCount()+ mBed);

            tv = (TextView) convertView.findViewById(R.id.baths);
            tv.setText(property.getBedroomCount()+mBath);

            tv = (TextView) convertView.findViewById(R.id.footage);
            tv.setText(property.getFootage()+ mSqFt);

            return convertView;
        }
    }

    
}
