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
import android.widget.Button;

import com.tusharmalik.technoglobe.Adapters.SellerAdapter;
import com.tusharmalik.technoglobe.Models.Buyer;
import com.tusharmalik.technoglobe.dbseller.BuyerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView ProductList;
    SellerAdapter sellerAdapter;
        ArrayList<Buyer> records = new ArrayList<Buyer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);
        SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        SQLiteDatabase readDb = myDbHelper.getReadableDatabase();
        ProductList = findViewById(R.id.RecordsList);
        sellerAdapter = new SellerAdapter(records);
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
                int position = viewHolder.getAdapterPosition() + 1;
                int demoId = BuyerTable.getRecord(position, finalWriteDb).getId();
                BuyerTable.deleteItem(finalWriteDb, demoId);
                records.remove(viewHolder.getAdapterPosition());
                records.trimToSize();
                sellerAdapter.notifyDataSetChanged();
            }
        });

        itemTouchHelper.attachToRecyclerView(ProductList);
//FOR UPDATE of price
//        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                int position = viewHolder.getAdapterPosition() + 1;
//                int demoId = BuyerTable.getRecord(position, finalWriteDb).getId();
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


