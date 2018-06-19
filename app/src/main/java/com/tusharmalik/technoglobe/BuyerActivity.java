package com.tusharmalik.technoglobe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tusharmalik.technoglobe.Adapters.BuyerAdapter;
import com.tusharmalik.technoglobe.Adapters.SellerAdapter;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.util.ArrayList;

public class BuyerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    BuyerAdapter buyerAdapter;
    ArrayList<Seller> records = new ArrayList<Seller>();
     FragmentManager fragmentManager;

    protected void onStart() {
        super.onStart();
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
//        SellerTable.getTableAsString(writeDb,"Sellers");
        records = SellerTable.getAllTodos(writeDb);
        buyerAdapter = new BuyerAdapter(records,BuyerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
        recyclerView.setAdapter(buyerAdapter);
        buyerAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);


        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        SQLiteDatabase readDb = myDbHelper.getReadableDatabase();

//
        Cursor data=TodoDatabaseHelper.getInfo();

        Seller work=new Seller();


        while(data.moveToNext()){


            work.name=data.getString(1);
            work.price= data.getString(2);
            work.discount=data.getString(3);
            work.imgurl=data.getString(4);


        }

        records.add(work);






//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

            recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
            DividerItemDecoration itemDecoration = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
            recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setHasFixedSize(true);
            buyerAdapter = new BuyerAdapter(records, getBaseContext());
            recyclerView.setAdapter(buyerAdapter);
            buyerAdapter.notifyDataSetChanged();



//        }

        recyclerView = findViewById(R.id.buyerItemList);
        buyerAdapter = new BuyerAdapter(records,BuyerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
        recyclerView.setAdapter(buyerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(BuyerActivity.this,Payment.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragTxn = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_fashion:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_icon)
                );

                break;
            case R.id.nav_toy:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_puzzle)
                );
                break;
            case R.id.nav_mobiles:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_phone)
                );
                break;
            case R.id.nav_laps: default:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_laptop_black_24dp)
                );
                break;
            case R.id.nav_home:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_restaurant_cutlery)
                );
                break;
            case R.id.nav_sports:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_soccer_ball_variant)
                );
                break;
            case R.id.nav_automobiles:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_sedan_car_front)
                );
                break;
            case R.id.nav_food:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_food)
                );
                break;
            case R.id.nav_call:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_call)
                );
                String input1="tel:"+"9891296000";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(input1));
                startActivity(intent);
                break;
            case R.id.nav_mail:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_menu_send)
                );
                String input2="harshthakral5@gmail.com";
                Intent mail = new Intent(Intent.ACTION_SENDTO);
                mail.setData(Uri.fromParts("mailto",input2,null));
                startActivity(mail);
                break;
            case R.id.nav_fb:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_facebook)
                );
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = "https://www.facebook.com/RafaMyAdrenaline/";
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
                break;
            case R.id.nav_twitter:
                fragTxn.replace(
                        R.id.flFragContainer,
                        IconFragment.newInstance(R.drawable.ic_twitter)
                );
                Intent twitterIntent = new Intent(Intent.ACTION_VIEW);
                String twitterUrl = "https://www.twitter.com/RafaMyAdrenaline/";
                twitterIntent.setData(Uri.parse(twitterUrl));
                startActivity(twitterIntent);

                break;
        }
        fragTxn.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
