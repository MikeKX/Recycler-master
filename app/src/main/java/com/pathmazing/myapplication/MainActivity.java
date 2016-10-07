package com.pathmazing.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import Utils.DataEntry;
import Utils.MediaObject;

/**
 * Created by Kevin on 9/21/2016.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private DataEntry mDataEntry;

    private List<MediaObject> list = new ArrayList<MediaObject>();
    private ArrayList<MediaObject> arrayList = new ArrayList<MediaObject>();

    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private GridView mGridView;
    private AdapterView mAdapterView;
    private GridLayoutManager mGridLayoutManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataEntry = new DataEntry(getApplicationContext());
        list = mDataEntry.getAllMedias();
        arrayList.addAll(mDataEntry.getAllMedias());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mGridView = (GridView) findViewById(R.id.grid_view);
        mGridView.setVisibility(View.GONE);

        mLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this,8);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(list, getApplicationContext());
        mRecyclerViewAdapter.setItemLayout(R.layout.list_show_activity);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }


    //-----------------------menu---------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_listview) {

//            mGridView.setVisibility(View.GONE);
//            mRecyclerView.setVisibility(View.VISIBLE);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerViewAdapter = new RecyclerViewAdapter(list, getApplicationContext());
            mRecyclerViewAdapter.setItemLayout(R.layout.list_show_activity);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
        } else if (id == R.id.action_gridview) {
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mRecyclerViewAdapter.setItemLayout(R.layout.gridview);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
//            mRecyclerView.setVisibility(View.GONE);
//            mGridView.setVisibility(View.VISIBLE);

//            mAdapterView = new AdapterView(this, R.layout.gridview, list);
//            mGridView.setAdapter(mAdapterView);

        }
        return super.onOptionsItemSelected(item);
    }
}
