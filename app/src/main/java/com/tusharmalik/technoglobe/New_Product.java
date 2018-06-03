package com.tusharmalik.technoglobe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tusharmalik.technoglobe.Models.Buyer;
import com.tusharmalik.technoglobe.dbseller.BuyerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

public class New_Product extends AppCompatActivity {

    public EditText edName,edDescription,edPrice;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__product);
        edName = findViewById(R.id.pname);
        edDescription = findViewById(R.id.pdesc);
        edPrice= findViewById(R.id.pprice);
        btnAdd = findViewById(R.id.btnAdd);


        final String name = edName.getText().toString();
        final String description= edDescription.getText().toString();
        final String price = edPrice.getText().toString();


        TodoDatabaseHelper myDbHelper= new TodoDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edName.getText().toString().isEmpty() && !edDescription.getText().toString().isEmpty() && !edPrice.getText().toString().isEmpty()  ) {
                     BuyerTable.insertBuyerItem(new Buyer(0,
                             name,
                            description,
                            price),
                            writeDb);

                    Intent i = new Intent(New_Product.this, HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(New_Product.this, "job done" , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(New_Product.this, "Please fill the incomplete fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}