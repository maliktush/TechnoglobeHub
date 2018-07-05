package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

public class New_Product extends AppCompatActivity {

    public EditText edName,edDescription,edPrice,edDiscount,edQuantity,edIMGURL,edIMGURL2,edIMGURL3,edIMGURL4,edIMGURL5;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__product);
        edName = findViewById(R.id.pname);
        edDescription = findViewById(R.id.pdesc);
        edPrice= findViewById(R.id.pprice);
        edDiscount=findViewById(R.id.pdiscount);
        edQuantity=findViewById(R.id.pquantity);
        edIMGURL=findViewById(R.id.pimage);
        edIMGURL2=findViewById(R.id.pimage2);
        edIMGURL3=findViewById(R.id.pimage3);
        edIMGURL4=findViewById(R.id.pimage4);
        edIMGURL5=findViewById(R.id.pimage5);

        btnAdd = findViewById(R.id.btnAdd);
        final Spinner dropdown = findViewById(R.id.pcat);
//create a list of items for the spinner.
        String[] items = new String[]{"SELECT A CATEGORY","Electronics", "Mobiles", "Toys","Fashion","Home","Sports","Automobiles","Food"};
        dropdown.setSelection(0);
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        final String[] item = new String[1];

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                item[0] = (String) dropdown.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





        TodoDatabaseHelper myDbHelper= new TodoDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        final String mob=getIntent().getStringExtra("mob");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edName.getText().toString().isEmpty() && !edDescription.getText().toString().isEmpty() && !edPrice.getText().toString().isEmpty() && !edQuantity.getText().toString().isEmpty() ) {
                     SellerTable.insertBuyerItem(new Seller(0,
                                     edName.getText().toString(),
                                     edDescription.getText().toString(),
                                     edPrice.getText().toString(),
                                     edDiscount.getText().toString(),
                                     edQuantity.getText().toString(),
                                     item[0].toString(),
                                     mob,
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