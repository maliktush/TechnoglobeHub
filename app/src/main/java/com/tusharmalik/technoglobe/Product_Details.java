package com.tusharmalik.technoglobe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.tusharmalik.technoglobe.Models.Cartmodel;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.CartDatabaseHelper;
import com.tusharmalik.technoglobe.dbseller.CartTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.util.ArrayList;

public class Product_Details extends AppCompatActivity {
    Button btnBack,btnCart,btnPay;
    RecyclerView recyclerView;

    TextView detailName,detaildescription,detailprice,detailtotprice;
    ImageView ivdet1,ivdet2;
    ArrayList<Seller> records = new ArrayList<Seller>();
    ArrayList<Seller> recordscart = new ArrayList<Seller>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);
//        ivdet1=findViewById(R.id.detailimg1);
        ivdet2=findViewById(R.id.detailimg2);

        final Spinner dropdown = findViewById(R.id.detailspinner);
        detailName=findViewById(R.id.detailName);
        detaildescription=findViewById(R.id.detailDesc);
        detailprice=findViewById(R.id.detailprice);
        detailtotprice=findViewById(R.id.detailtotalprice);
        btnPay=findViewById(R.id.DetailBUY);
        btnCart=findViewById(R.id.DetailADDTOCART);
//        btnBack=findViewById(R.id.buttonBACK);
        final float[] total = {0};
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Product_Details.this,Payment.class);
                startActivity(i);
            }
        });
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Product_Details.this,BuyerActivity.class);
//                startActivity(i);
//            }
//        });


//        recyclerView=(RecyclerView) findViewById(R.id.detail);
        Intent mIntent = getIntent();
        final int pos = mIntent.getIntExtra("pos", 0);
        records= (ArrayList<Seller>) getIntent().getSerializableExtra("record");
        final float newval=Float.parseFloat(records.get(pos).getPrice())
                -((Float.parseFloat(records.get(pos).getPrice()))*(Float.parseFloat(records.get(pos).getDiscount()))/100);


        Float[] items = new Float[]{Float.valueOf(1), Float.valueOf(2), Float.valueOf(3), Float.valueOf(4), Float.valueOf(5), Float.valueOf(6), Float.valueOf(7), Float.valueOf(8), Float.valueOf(9)};
        final Float[] item = new Float[1];
        item[0]= Float.valueOf(5);
        dropdown.setSelection(0);
        ArrayAdapter<Float> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item[0] = (Float) dropdown.getSelectedItem();
                total[0] = newval * item[0];
                detailtotprice.setText(String.valueOf(total[0]));

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        float newval=Float.parseFloat(records.get(pos).getPrice())-
//                ((Float.parseFloat(records.get(pos).getPrice()))*(Float.parseFloat(records.get(pos).getDiscount()))/100);
        total[0] = newval * item[0];
        detailName.setText(records.get(pos).getData());
        detaildescription.setText(records.get(pos).getDescription());
        detailprice.setText(String.valueOf(newval));
        detailtotprice.setText(String.valueOf(total[0]));
//        Picasso.get()
//                .load(records.get(pos).getImgurl())
//                .into(ivdet1);
        Picasso.get()
                .load(records.get(pos).getImgurl2())
                .into(ivdet2);


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordscart= (ArrayList<Seller>) getIntent().getSerializableExtra("record2");
                Intent mIntent = getIntent();
                final int pos = mIntent.getIntExtra("pos", 0);

                Toast.makeText(Product_Details.this, "Added to your Cart", Toast.LENGTH_SHORT).show();
                TodoDatabaseHelper myDbHelpercart = new TodoDatabaseHelper(Product_Details.this);
                CartDatabaseHelper myDbHelpercart2 = new CartDatabaseHelper(Product_Details.this);
                final SQLiteDatabase writeDbcart2 = myDbHelpercart2.getWritableDatabase();
                SQLiteDatabase readDbcart2 = myDbHelpercart2.getReadableDatabase();

                final SQLiteDatabase writeDbcart = myDbHelpercart.getWritableDatabase();
                SQLiteDatabase readDbcart = myDbHelpercart.getReadableDatabase();
                final String mob= Main2Activity.mobilenumber[0];
                Cursor datacart=TodoDatabaseHelper.getInfo(writeDbcart);


                int count=0;
                datacart.moveToPosition(pos-1);
                while(datacart.moveToNext()&&count==0){

                    Seller workcart=new Seller();

                    workcart.name=datacart.getString(1);
                    workcart.description=datacart.getString(2);
                    workcart.price=datacart.getString(3);
                    workcart.discount= datacart.getString(4);
                    workcart.quantity= datacart.getString(5);
                    workcart.category= datacart.getString(6);
                    workcart.verify= datacart.getString(7);
                    workcart.imgurl=datacart.getString(8);
                    workcart.imgurl2=datacart.getString(9);
                    workcart.imgurl3=datacart.getString(10);
                    workcart.imgurl4=datacart.getString(11);
                    workcart.imgurl5=datacart.getString(12);
                    count++;
                    CartTable.insertCartItem(new Cartmodel(0,
                                    workcart.name,
                                    workcart.description,
                                    workcart.price,
                                    workcart.discount,
                                    workcart.quantity,
                                    workcart.category,
                                    mob,
                                    workcart.imgurl,
                                    workcart.imgurl2,
                                    workcart.imgurl3,
                                    workcart.imgurl4,
                                    workcart.imgurl5),
                            writeDbcart2);
                }
//









//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {
                Intent i=new Intent(Product_Details.this,Cart.class);
                i.putExtra("record" , recordscart);
                startActivity(i);

                }
            });




    }
}
