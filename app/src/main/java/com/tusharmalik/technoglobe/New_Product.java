package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

public class New_Product extends AppCompatActivity {

    public EditText edName,edDescription,edPrice,edQuantity,edIMGURL,edIMGURL2,edIMGURL3,edIMGURL4,edIMGURL5;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__product);
        edName = findViewById(R.id.pname);
        edDescription = findViewById(R.id.pdesc);
        edPrice= findViewById(R.id.pprice);
        edQuantity=findViewById(R.id.pquantity);
        edIMGURL=findViewById(R.id.pimage);
        edIMGURL2=findViewById(R.id.pimage2);
        edIMGURL3=findViewById(R.id.pimage3);
        edIMGURL4=findViewById(R.id.pimage4);
        edIMGURL5=findViewById(R.id.pimage5);

        btnAdd = findViewById(R.id.btnAdd);


        final String name = edName.getText().toString();
        final String description= edDescription.getText().toString();
        final String price = edPrice.getText().toString();
        final String quantity = edQuantity.getText().toString();


        TodoDatabaseHelper myDbHelper= new TodoDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edName.getText().toString().isEmpty() && !edDescription.getText().toString().isEmpty() && !edPrice.getText().toString().isEmpty() && !edQuantity.getText().toString().isEmpty() ) {
                     SellerTable.insertBuyerItem(new Seller(0,
                                     edName.getText().toString(),
                                     edDescription.getText().toString(),
                                     edPrice.getText().toString(),
                                     edQuantity.getText().toString(),
                                    edIMGURL.getText().toString(),
                                     edIMGURL2.getText().toString(),
                                     edIMGURL3.getText().toString(),
                                     edIMGURL4.getText().toString(),
                                     edIMGURL5.getText().toString()),
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