package com.tusharmalik.technoglobe.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tusharmalik.technoglobe.Models.Buyer;
import com.tusharmalik.technoglobe.R;

import java.util.ArrayList;

/**
 * Created by tushm on 01-06-2018.
 */

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.RecordViewHolder>{

    private ArrayList<Buyer> records;

    public SellerAdapter(ArrayList<Buyer> records) {
        this.records = records;
    }

    public void setRecords(ArrayList<Buyer> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new RecordViewHolder(li.inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        holder.bindView(records.get(position));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvDescription,tvPrice;

        public RecordViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }
        void bindView(Buyer record){
            tvName.setText(record.getData());
            tvDescription.setText(record.getDescription());
            tvPrice.setText(record.getPrice());
        }
    }
}

