package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.tusharmalik.technoglobe.Adapters.SellerAdapter;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView ProductList;
    SellerAdapter sellerAdapter;
        ArrayList<Seller> records = new ArrayList<Seller>();
        ImageView iv,ivzoom;
        FloatingActionButton addb;

    @Override
    protected void onStart() {
        super.onStart();
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        records = SellerTable.getAllTodos(writeDb);
        sellerAdapter = new SellerAdapter(records,HomeActivity.this);
        ProductList.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        ProductList.setAdapter(sellerAdapter);
        sellerAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);
        iv=findViewById(R.id.iv);
        ivzoom=findViewById(R.id.ivzoom);
        addb=findViewById(R.id.addb);


        SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        SQLiteDatabase readDb = myDbHelper.getReadableDatabase();
        ProductList = findViewById(R.id.RecordsList);
        sellerAdapter = new SellerAdapter(records,HomeActivity.this);
        ProductList.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        ProductList.setAdapter(sellerAdapter);

        final SQLiteDatabase finalWriteDb = writeDb;
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition() +1;
//                int demoId = SellerTable.getRecord(position, finalWriteDb).getId();
                SellerTable.deleteItem(finalWriteDb, position);
                records.remove(position);
                records.trimToSize();
                sellerAdapter.notifyDataSetChanged();
            }
        });

        itemTouchHelper.attachToRecyclerView(ProductList);
//FOR UPDATE of price
//        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                int position = viewHolder.getAdapterPosition() + 1;
////                int demoId = SellerTable.getRecord(position, finalWriteDb).getId();
//                Intent i=new Intent(HomeActivity.this,New_Product.class);
//                startActivity(i);
//            }
//        });
//
//        itemTouchHelper2.attachToRecyclerView(ProductList);


        FloatingActionButton addb = (FloatingActionButton) findViewById(R.id.addb);
        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, New_Product.class));
            }
        });

    }
}


