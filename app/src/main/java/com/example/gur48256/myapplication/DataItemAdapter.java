package com.example.gur48256.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gur48256.myapplication.database.DataItem;

import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    private List<DataItem> mItems;
    private Context mContext;
    public String comm = "", first = "", phone = "", addr = "", gender = "";

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.activity_dataadapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {

        DataItem item = mItems.get(position);

        comm = item.getComm();
        first = item.getFirst();
        phone = item.getPhone();
        addr = item.getAddr();
        gender = item.getGender();

        holder.tv_comm.setText(comm);
        holder.tv_fName.setText(first);
        holder.tv_ph_no.setText(phone);
        holder.tv_addr.setText(addr);
        holder.tv_gender.setText(gender);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_comm, tv_fName, tv_ph_no, tv_addr, tv_gender;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_comm = itemView.findViewById(R.id.comm);
            tv_fName = itemView.findViewById(R.id.fName);
            tv_ph_no = itemView.findViewById(R.id.ph_no);
            tv_addr = itemView.findViewById(R.id.addr);
            tv_gender = itemView.findViewById(R.id.gender);

            mView = itemView;
        }
    }
}