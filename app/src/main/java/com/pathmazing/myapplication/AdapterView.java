package com.pathmazing.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Utils.MediaObject;

public class AdapterView extends ArrayAdapter<MediaObject> {
    Context context;
    int layoutResourceId;

    //2
    List<MediaObject> data = new ArrayList<MediaObject>();

    View row;
    ViewHolder holder=null;

    public AdapterView(Context context, int layoutResourceId, List<MediaObject> data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;

        holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            //3
            holder.txTitle = (TextView) row.findViewById(R.id.txt_List);
            holder.imageView = (ImageView) row.findViewById(R.id.img_List);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        //4
      final   MediaObject model = data.get(position);
        holder.txTitle.setText(model.getTitle());
        Glide.with(context)
                .load(model.getImageUrl())
                .into(holder.imageView);


        //5
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "" + model.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        return row;

    }

    //1
    static class ViewHolder {
        TextView txTitle;
        ImageView imageView;
    }
}