package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {
    Button btnbuyer,btnseller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        btnbuyer=findViewById(R.id.btnBuyer);
        btnseller=findViewById(R.id.btnSeller);
        btnbuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SelectionActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
        btnseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SelectionActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
