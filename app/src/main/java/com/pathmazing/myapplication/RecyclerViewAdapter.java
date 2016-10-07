package com.pathmazing.myapplication;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import Utils.MediaObject;

/**
 * Created by Kim Xeang on 9/21/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<MediaObject> item;
    Context context;
    ViewHolder viewHolder;
    View items;
    private int itemLayout;

    public RecyclerViewAdapter(List<MediaObject> item, Context c) {
        this.item = item;
        this.context = c;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txTitle;
        public ImageView imageUrl;


        public ViewHolder(View view) {
            super(view);
            imageUrl = (ImageView) view.findViewById(R.id.img_List);
            txTitle = (TextView) view.findViewById(R.id.txt_List);
        }

    }

    public int getItemLayout() {
        return itemLayout;
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        items = inflater.inflate(itemLayout, parent, false);
        viewHolder = new ViewHolder(items);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MediaObject mediaObject = item.get(position);

        holder.txTitle.setText(mediaObject.getTitle());
        Glide.with(context)
                .load(mediaObject
                        .getImageUrl())
                .into(holder.imageUrl);

        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mediaObject.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return item.size();
    }
}
