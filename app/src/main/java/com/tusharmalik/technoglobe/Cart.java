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
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart extends AppCompatActivity implements Serializable {
    RecyclerView recyclerView;
    Button cartpay;
    ImageView cartdelete;
    CartAdapter cartAdapter;
    ArrayList<Seller> recordscart = new ArrayList<Seller>();

    @Override
    protected void onStart() {
        super.onStart();
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        recordscart = SellerTable.getAllTodos(writeDb);
        SellerTable.getTableAsString(writeDb,"Sellers");
        cartAdapter = new CartAdapter(recordscart,Cart.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView=(RecyclerView) findViewById(R.id.CartList);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(Cart.this, new LinearLayoutManager(Cart.this).getOrientation());
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        recyclerView.addItemDecoration(itemDecoration);
        recordscart= (ArrayList<Seller>) getIntent().getSerializableExtra("record");

        recyclerView.setHasFixedSize(true);
        cartAdapter = new CartAdapter(recordscart, getBaseContext());
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        cartdelete=findViewById(R.id.cartdelete);

//        cartpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Cart.this,Payment.class);
//                startActivity(i);
//            }
//        });

    }
}
