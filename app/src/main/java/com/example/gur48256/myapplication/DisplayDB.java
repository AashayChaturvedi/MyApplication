package com.example.gur48256.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.gur48256.myapplication.database.DataItem;
import com.example.gur48256.myapplication.database.DataSource;

import java.util.ArrayList;
import java.util.List;

public class DisplayDB extends AppCompatActivity {

//    long num;
    public List<DataItem> dataItemList = new ArrayList<>();

    DataSource mDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydb);

        mDataSource = new DataSource(this);
        mDataSource.open();
        Toast.makeText(this, "DB acquired", Toast.LENGTH_SHORT).show();

//        num = mDataSource.dataCount();
        dataItemList = mDataSource.getItems();

        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);
    }
}
