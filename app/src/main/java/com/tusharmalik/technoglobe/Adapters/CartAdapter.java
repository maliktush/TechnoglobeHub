package com.tusharmalik.technoglobe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tusharmalik.technoglobe.Cart;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.Payment;
import com.tusharmalik.technoglobe.R;
import com.tusharmalik.technoglobe.zoomactivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tushm on 26-06-2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.RecordViewHolder>{

    private ArrayList<Seller> records;
    Context context;


    public CartAdapter(ArrayList<Seller> records, Context context) {
        this.records = records;
        this.context = context;
    }

    public void setRecords(ArrayList<Seller> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public CartAdapter.RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new CartAdapter.RecordViewHolder(li.inflate(R.layout.cart_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CartAdapter.RecordViewHolder holder, final int position) {
        final Seller seller=records.get(position);
        holder.bindView(records.get(position));
        Picasso.get()
                .load(seller.getImgurl())
                .into(holder.Cartivbuyer);
//        Picasso.get()
//                .load(seller.getImgurl2())
//                .into(holder.iv2);
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
        holder.Cartivbuyer.setOnClickListener(new View.OnClickListener() {
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
//        holder.iv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= (new Intent(context, zoomactivity.class));
//                i.putExtra("image_link",seller.getImgurl3());
//                context.startActivity(i);
//                Toast.makeText(context, records.get(position).getImgurl3(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.iv4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= (new Intent(context, zoomactivity.class));
//                i.putExtra("image_link",seller.getImgurl4());
//                context.startActivity(i);
//                Toast.makeText(context, records.get(position).getImgurl4(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.iv5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= (new Intent(context, zoomactivity.class));
//                i.putExtra("image_link",seller.getImgurl5());
//                context.startActivity(i);
//                Toast.makeText(context, records.get(position).getImgurl5(), Toast.LENGTH_SHORT).show();
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
        TextView CartNamebuyer,CartPricebuyer;
        ImageView Cartivbuyer;
        Button cartpay;

        public RecordViewHolder(View itemView) {
            super(itemView);
            CartNamebuyer = itemView.findViewById(R.id.CartName);
            CartPricebuyer = itemView.findViewById(R.id.CartPrice);
            cartpay=itemView.findViewById(R.id.cartpayy);
            Cartivbuyer=itemView.findViewById(R.id.buyimg);


        }

        void bindView(Seller record){



            CartNamebuyer.setText(record.getData());
            CartPricebuyer.setText(record.getPrice());
            cartpay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context,Payment.class);
                    context.startActivity(i);
                }

            });


        }
    }
}


