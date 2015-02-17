
package com.example.pleasure.instagramclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Pleasure on 2/14/2015.
 */

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    // data from the activity
    // take in params: context, data source
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        // Attach the adapter to the listview
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for this position
        InstagramPhoto photo = getItem(position);
        // check if we are using a recycle view, if not we need to inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

        }
        // lookup the views for populating the data
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);


        // insert the model data into each of the view items
        // insert the image using Picasso.  While waiting, clear out the imageview
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        //Picasso.with(getContext()).load(photo.imageUrl).fit().center(rop().placeholder.ic_launcher).into(ivPhoto);

        tvCaption.setText(photo.caption);

        // return the created item as a view
        return convertView;

    }
}

