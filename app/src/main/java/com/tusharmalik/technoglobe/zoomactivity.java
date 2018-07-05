package com.tusharmalik.technoglobe;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class zoomactivity extends AppCompatActivity {
    ImageView ivz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomactivity);
        ivz=findViewById(R.id.ivz);
        String res=getIntent().getStringExtra("image_link");
        Picasso.get()
                .load(res)
                .into(ivz);
//        ivz.setImageURI(Uri.parse(res));
        Toast.makeText(this,getIntent().getStringExtra("image_link") , Toast.LENGTH_SHORT).show();

    }
}
