package com.example.gur48256.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gur48256.myapplication.database.DataItem;
import com.example.gur48256.myapplication.database.DataSource;

public class DisplayActivity extends Activity {

    public String comm, first, last, phone, addr, gender;
    public TextView tv_comm, tv_fName, tv_lName, tv_ph_no, tv_addr, tv_gender;

    DataSource mDataSource;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mDataSource = new DataSource(this);
        mDataSource.open();
        Toast.makeText(this, "DB acquired", Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        comm = bundle.getString("comments");
        first = bundle.getString("first");
        last = bundle.getString("last");
        phone = bundle.getString("phone");
        addr = bundle.getString("address");
        gender = bundle.getString("gender");

        tv_comm = findViewById(R.id.comm);
        tv_comm.setText(comm);
        tv_fName = findViewById(R.id.fName);
        tv_fName.setText(first);
        tv_lName = findViewById(R.id.lName);
        tv_lName.setText(last);
        tv_ph_no = findViewById(R.id.ph_no);
        tv_ph_no.setText(phone);
        tv_addr = findViewById(R.id.addr);
        tv_addr.setText(addr);
        tv_gender = findViewById(R.id.gender);
        tv_gender.setText(gender);

        DataItem dataItem = new DataItem(comm, first, last, phone, addr, gender);

        mDataSource.insertInTable(dataItem);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }
}
