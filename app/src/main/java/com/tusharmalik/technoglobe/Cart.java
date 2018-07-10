package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tusharmalik.technoglobe.Adapters.BuyerAdapter;
import com.tusharmalik.technoglobe.Adapters.CartAdapter;
import com.tusharmalik.technoglobe.Adapters.SellerAdapter;
import com.tusharmalik.technoglobe.Models.Cartmodel;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.CartDatabaseHelper;
import com.tusharmalik.technoglobe.dbseller.CartTable;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart extends AppCompatActivity implements Serializable {
    RecyclerView recyclerView;
    Button cartpay;
    ImageView cartdelete;
    CartAdapter cartAdapter;
    ArrayList<Cartmodel> recordscart = new ArrayList<Cartmodel>();

    @Override
    protected void onStart() {
        super.onStart();
        CartDatabaseHelper myDbHelper = new CartDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        recordscart = CartTable.getAllCartTodos(writeDb);
        CartTable.getCartTableAsString(writeDb,"Cart");
        cartAdapter = new CartAdapter(recordscart,Cart.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartdelete=findViewById(R.id.cartdelete);
        CartDatabaseHelper myDbHelper = new CartDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        SQLiteDatabase readDb = myDbHelper.getReadableDatabase();
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(Cart.this, new LinearLayoutManager(Cart.this).getOrientation());
//        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
//        recyclerView.addItemDecoration(itemDecoration);
//        recordscart= (ArrayList<Seller>) getIntent().getSerializableExtra("record");
//
//        recyclerView.setHasFixedSize(true);
//        cartAdapter = new CartAdapter(recordscart, getBaseContext());
//        recyclerView.setAdapter(cartAdapter);
//        cartAdapter.notifyDataSetChanged();


        recyclerView=findViewById(R.id.CartList);
        cartAdapter = new CartAdapter(recordscart,Cart.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        recyclerView.setAdapter(cartAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition() ;
                int demoId = CartTable.getCartRecord((recordscart.get(position).getDescriptionc()), writeDb).getIdc();
                CartTable.deleteCartItem(writeDb, demoId);
                recordscart.remove(viewHolder.getAdapterPosition());
                recordscart.trimToSize();
                cartAdapter.notifyDataSetChanged();
            }
        });

//        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//            int position = viewHolder.getAdapterPosition() ;
//            int demoId = SellerTable.getRecord((records.get(position).getDescription()), writeDb).getId();
//            SellerTable.deleteItem(writeDb, demoId);
//            records.remove(viewHolder.getAdapterPosition());
//            records.trimToSize();
//            sellerAdapter.notifyDataSetChanged();
//        }

        itemTouchHelper.attachToRecyclerView(recyclerView);

//        cartpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Cart.this,Payment.class);
//                startActivity(i);
//            }
//        });

    }
}
