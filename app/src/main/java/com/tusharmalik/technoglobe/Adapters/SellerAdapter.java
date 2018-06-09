package com.tusharmalik.technoglobe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tusharmalik.technoglobe.Front;
import com.tusharmalik.technoglobe.HomeActivity;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.R;
import com.tusharmalik.technoglobe.SelectionActivity;
import com.tusharmalik.technoglobe.zoomactivity;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tushm on 01-06-2018.
 */

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.RecordViewHolder>{

    private ArrayList<Seller> records;
    Context context;


    public SellerAdapter(ArrayList<Seller> records, Context context) {
        this.records = records;
        this.context = context;
    }

    public void setRecords(ArrayList<Seller> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new RecordViewHolder(li.inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, final int position) {
        final Seller seller=records.get(position);
        holder.bindView(records.get(position));
        Picasso.get()
                .load(seller.getImgurl())
                .into(holder.iv);
        Picasso.get()
                .load(seller.getImgurl2())
                .into(holder.iv2);
        Picasso.get()
                .load(seller.getImgurl3())
                .into(holder.iv3);
        Picasso.get()
                .load(seller.getImgurl4())
                .into(holder.iv4);
        Picasso.get()
                .load(seller.getImgurl5())
                .into(holder.iv5);

        holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i= (new Intent(context, zoomactivity.class));
                     i.putExtra("image_link",seller.getImgurl());
                    context.startActivity(i);
//                    Toast.makeText(context, records.get(position).getImgurl(), Toast.LENGTH_SHORT).show();
                }
            });
        holder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= (new Intent(context, zoomactivity.class));
                i.putExtra("image_link",seller.getImgurl2());
                context.startActivity(i);
                Toast.makeText(context, records.get(position).getImgurl2(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= (new Intent(context, zoomactivity.class));
                i.putExtra("image_link",seller.getImgurl3());
                context.startActivity(i);
                Toast.makeText(context, records.get(position).getImgurl3(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= (new Intent(context, zoomactivity.class));
                i.putExtra("image_link",seller.getImgurl4());
                context.startActivity(i);
                Toast.makeText(context, records.get(position).getImgurl4(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= (new Intent(context, zoomactivity.class));
                i.putExtra("image_link",seller.getImgurl5());
                context.startActivity(i);
                Toast.makeText(context, records.get(position).getImgurl5(), Toast.LENGTH_SHORT).show();
            }
        });

        }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvDescription,tvPrice,tvQuantity;
        ImageView iv,iv2,iv3,iv4,iv5,ivzoom;

        public RecordViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity=itemView.findViewById(R.id.tvQuantity);
            iv=itemView.findViewById(R.id.iv);
            iv2=itemView.findViewById(R.id.iv2);
            iv3=itemView.findViewById(R.id.iv3);
            iv4=itemView.findViewById(R.id.iv4);
            iv5=itemView.findViewById(R.id.iv5);
            ivzoom=itemView.findViewById(R.id.ivzoom);

        }

        void bindView(Seller record){

//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i=new Intent(HomeActivity.class,zoomactivity.class);
//                    startActivity(i);
//                }
//            });

            tvName.setText(record.getData());
            tvDescription.setText(record.getDescription());
            tvPrice.setText(record.getPrice());
            tvQuantity.setText(record.getQuantity());

        }
    }
}

