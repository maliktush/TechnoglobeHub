package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Product_Details extends AppCompatActivity {
    Button btnBack,btnCart,btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);
        final Spinner dropdown = findViewById(R.id.detailspinner);
        btnPay=findViewById(R.id.DetailBUY);
        btnCart=findViewById(R.id.DetailADDTOCART);
        btnBack=findViewById(R.id.buttonBACK);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Product_Details.this,Payment.class);
                startActivity(i);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Product_Details.this,BuyerActivity.class);
                startActivity(i);
            }
        });
        String[] items = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20(max)"};
        dropdown.setSelection(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        final String[] item = new String[1];

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                item[0] = (String) dropdown.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
