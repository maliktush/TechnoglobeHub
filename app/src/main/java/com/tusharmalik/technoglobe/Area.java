package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Area extends AppCompatActivity {
    EditText etarea;
    Button btnarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        etarea=findViewById(R.id.etarea);
        btnarea=findViewById(R.id.btnarea);
        btnarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(etarea.getText().toString())>110000&&Integer.parseInt(etarea.getText().toString())<110100){
                    Intent i=new Intent(Area.this,BuyerActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(Area.this, "Products not delivered in this area", Toast.LENGTH_LONG).show();
            }
        });
    }
}
