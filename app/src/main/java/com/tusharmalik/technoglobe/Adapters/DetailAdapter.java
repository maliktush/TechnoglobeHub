package com.tusharmalik.technoglobe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.R;
import com.tusharmalik.technoglobe.zoomactivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tushm on 22-06-2018.
 */

public class DetailAdapter  extends RecyclerView.Adapter<DetailAdapter.RecordViewHolder>{

    private ArrayList<Seller> records;
    Context context;


    public DetailAdapter(ArrayList<Seller> records, Context context) {
        this.records = records;
        this.context = context;
    }

    public void setRecords(ArrayList<Seller> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public DetailAdapter.RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new DetailAdapter.RecordViewHolder(li.inflate(R.layout.activity_product__details,parent,false));
    }

    @Override
    public void onBindViewHolder(DetailAdapter.RecordViewHolder holder, final int position) {
        final Seller seller=records.get(position);
        holder.bindView(records.get(position));
        Picasso.get()
                .load(seller.getImgurl())
                .into(holder.detailimg1);

        Picasso.get()
                .load(seller.getImgurl2())
                .into(holder.detailimg2);
//        Picasso.get()
//                .load(seller.getImgurl3())
//                .into(holder.iv3);
//        Picasso.get()
//                .load(seller.getImgurl4())
//                .into(holder.iv4);
//        Picasso.get()
//                .load(seller.getImgurl5())
//                .into(holder.iv5);
//
        holder.detailimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= (new Intent(context, zoomactivity.class));
                i.putExtra("image_link",seller.getImgurl());
                context.startActivity(i);
//                    Toast.makeText(context, records.get(position).getImgurl(), Toast.LENGTH_SHORT).show();
            }
        });
//        holder.iv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= (new Intent(context, zoomactivity.class));
//                i.putExtra("image_link",seller.getImgurl2());
//                context.startActivity(i);
//                Toast.makeText(context, records.get(position).getImgurl2(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public Seller getItem(int position) {
        return records.get(position);
    }
    public List<Seller> getList() {
        return this.records;
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView detailname,detaildesc,detailtotprice,detaildiscount;
        ImageView detailimg1,detailimg2;

        public RecordViewHolder(View itemView) {
            super(itemView);
            detailname = itemView.findViewById(R.id.detailName);
            detaildesc = itemView.findViewById(R.id.detailDesc);
            detailtotprice=itemView.findViewById(R.id.detailtotalprice);
            detaildiscount=itemView.findViewById(R.id.detailprice);
            detailimg1=itemView.findViewById(R.id.detailimg1);
            detailimg2=itemView.findViewById(R.id.detailimg2);



        }

        void bindView(Seller record){

//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i=new Intent(HomeActivity.class,zoomactivity.class);
//                    startActivity(i);
//                }
//            });

            detailname.setText(record.getData());
            detaildesc.setText(record.getDescription());
            detailtotprice.setText(record.getPrice());
            detaildiscount.setText(record.getDiscount());


        }
    }
}

