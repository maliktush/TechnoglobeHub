package com.tusharmalik.technoglobe;

import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tusharmalik.technoglobe.Adapters.BuyerAdapter;
import com.tusharmalik.technoglobe.Adapters.CartAdapter;
import com.tusharmalik.technoglobe.Adapters.SellerAdapter;
import com.tusharmalik.technoglobe.Models.Cartmodel;
import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.CartDatabaseHelper;
import com.tusharmalik.technoglobe.dbseller.CartTable;
import com.tusharmalik.technoglobe.dbseller.SellerTable;
import com.tusharmalik.technoglobe.dbseller.TodoDatabaseHelper;

import java.util.ArrayList;

public class BuyerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Context context;
    EditText search,demand;
    ImageView ivsearch,cart,logout;
    RecyclerView recyclerView;
    BuyerAdapter buyerAdapter;
    CartAdapter cartAdapter;
    Button viewcart,demandbtn;
    ArrayList<Seller> records = new ArrayList<Seller>();
    ArrayList<Seller> records1 = new ArrayList<Seller>();
    ArrayList<Seller> records2 = new ArrayList<Seller>();
    ArrayList<Seller> records3 = new ArrayList<Seller>();
    ArrayList<Seller> records4 = new ArrayList<Seller>();
    ArrayList<Seller> records5 = new ArrayList<Seller>();
    ArrayList<Seller> records6 = new ArrayList<Seller>();
    ArrayList<Seller> records7 = new ArrayList<Seller>();
    ArrayList<Seller> records8 = new ArrayList<Seller>();
    ArrayList<Cartmodel> recordscart = new ArrayList<Cartmodel>();



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
        search=findViewById(R.id.etsearch);
        ivsearch=findViewById(R.id.ivsearch);
        cart=findViewById(R.id.btnviewcart);
//        viewcart=findViewById(R.id.btnviewcart);
        demand=findViewById(R.id.demand);
        logout=findViewById(R.id.button2);
        demandbtn=findViewById(R.id.demandbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exit=new Intent(BuyerActivity.this,Front.class);
                startActivity(exit);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic=new Intent (BuyerActivity.this,Cart.class);
                startActivity(ic);
            }
        });
        demandbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(demand.getText().toString().contains("https://www.amazon")){
                    new AlertDialog.Builder(BuyerActivity.this)
                            .setTitle("Demand price mail")
                            .setMessage("Please send us the simple mail for your demand price by clicking on OK")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                    String[] TO = {"tushmalik09@gmail.com"};
                                    emailIntent.setData(Uri.parse("mailto:"));
                                    emailIntent.setType("text/plain");
                                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Demand  price feature");
                                    emailIntent.putExtra(Intent.EXTRA_TEXT, demand.getText().toString());

                                    try {
                                        startActivity(emailIntent);
                                        finish();
                                    } catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(BuyerActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                                    }

                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                    // do whatever

//                    Toast.makeText(BuyerActivity.this, "Your reques has been recieved and will we repond to u in one hour", Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(BuyerActivity.this, "URL is not valid", Toast.LENGTH_LONG).show();

            }
        });
//        viewcart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent cart=new Intent(BuyerActivity.this,Cart.class);
//                startActivity(cart);
//            }
//        });
        ivsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                records1.clear();
                String word = search.getText().toString();
                TodoDatabaseHelper myDbHelper1 = new TodoDatabaseHelper(BuyerActivity.this);


                final SQLiteDatabase writeDb1 = myDbHelper1.getWritableDatabase();
                Cursor data1=TodoDatabaseHelper.getInfoSearch(word,writeDb1);



                while(data1.moveToNext()){

                    Seller work1=new Seller();

                    work1.name=data1.getString(1);
                    work1.price= data1.getString(3);
                    work1.discount=data1.getString(4);
                    work1.imgurl=data1.getString(7);
                    records1.add(work1);


                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {


                DividerItemDecoration itemDecoration1 = new DividerItemDecoration(BuyerActivity.this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration1);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records1, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
            }
        });
        TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);


        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        final SQLiteDatabase readDb = myDbHelper.getReadableDatabase();

//
        Cursor data=TodoDatabaseHelper.getInfo(writeDb);

        Seller work=new Seller();


        while(data.moveToNext()){


            work.name=data.getString(1);
            work.price= data.getString(3);
            work.discount=data.getString(5);
            work.imgurl=data.getString(7);


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
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context ,recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(BuyerActivity.this,Product_Details.class);
                        i.putExtra("pos", position);
                        i.putExtra("record",records);
                        i.putExtra("record2",recordscart);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, final int position) {


                        new AlertDialog.Builder(BuyerActivity.this)
                                .setTitle("CART")
                                .setMessage("Are you sure you want to add this to your cart ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(BuyerActivity.this, "Added to your Cart", Toast.LENGTH_SHORT).show();
                                        TodoDatabaseHelper myDbHelpercart = new TodoDatabaseHelper(BuyerActivity.this);
                                        CartDatabaseHelper myDbHelpercart2 = new CartDatabaseHelper(BuyerActivity.this);
                                        final SQLiteDatabase writeDbcart2 = myDbHelpercart2.getWritableDatabase();
                                        SQLiteDatabase readDbcart2 = myDbHelpercart2.getReadableDatabase();

                                        final SQLiteDatabase writeDbcart = myDbHelpercart.getWritableDatabase();
                                        SQLiteDatabase readDbcart = myDbHelpercart.getReadableDatabase();
                                        final String mob= Main2Activity.mobilenumber[0];
                                        Cursor datacart=TodoDatabaseHelper.getInfo(writeDbcart);


                                        int count=0;
                                        datacart.moveToPosition(position-1);
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


                                        Intent i = new Intent(BuyerActivity.this, Cart.class);
                                        startActivity(i);



                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                        // do whatever
                    }
                })
        );



//        }

//        recyclerView = findViewById(R.id.buyerItemList);
//        buyerAdapter = new BuyerAdapter(records,BuyerActivity.this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
//        recyclerView.setAdapter(buyerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(BuyerActivity.this,Payment.class);
//                startActivity(i);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    //CLASS FOR ON CLICK START
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }
    //CLASS END

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
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_icon)
//                );
                TodoDatabaseHelper myDbHelper = new TodoDatabaseHelper(this);


                SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
                Cursor data=TodoDatabaseHelper.getInfoFashion(writeDb);



                while(data.moveToNext()){

                    Seller work=new Seller();
                    work.name=data.getString(1);
                    work.price= data.getString(3);
                    work.discount=data.getString(4);
                    work.imgurl=data.getString(7);

                    records8.add(work);

                }







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
                buyerAdapter = new BuyerAdapter(records8, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();

                break;
            case R.id.nav_toy:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_puzzle)
//                );
                TodoDatabaseHelper myDbHelper1 = new TodoDatabaseHelper(this);


                final SQLiteDatabase writeDb1 = myDbHelper1.getWritableDatabase();
                Cursor data1=TodoDatabaseHelper.getInfoToys(writeDb1);




                while(data1.moveToNext()){

                    Seller work1=new Seller();
                    work1.name=data1.getString(1);
                    work1.price= data1.getString(3);
                    work1.discount=data1.getString(4);
                    work1.imgurl=data1.getString(7);

                    records1.add(work1);
                }








//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration1 = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration1);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records1, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
                break;
            case R.id.nav_mobiles:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_phone)
//                );
                myDbHelper = new TodoDatabaseHelper(this);


                writeDb = myDbHelper.getWritableDatabase();
                Cursor data2=TodoDatabaseHelper.getInfoMobile(writeDb);





                while(data2.moveToNext()){
                    Seller work2=new Seller();

                    work2.name=data2.getString(1);
                    work2.price= data2.getString(3);
                    work2.discount=data2.getString(4);
                    work2.imgurl=data2.getString(7);
                    records2.add(work2);


                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration2 = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration2);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records2, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
                break;
            case R.id.nav_laps: default:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_laptop_black_24dp)
//                );
                TodoDatabaseHelper myDbHelper3 = new TodoDatabaseHelper(this);


                final SQLiteDatabase writeDb3 = myDbHelper3.getWritableDatabase();
                Cursor data3=TodoDatabaseHelper.getInfoElectro(writeDb3);



                while(data3.moveToNext()){
                    Seller work3=new Seller();


                    work3.name=data3.getString(1);
                    work3.price= data3.getString(3);
                    work3.discount=data3.getString(4);
                    work3.imgurl=data3.getString(7);

                    records3.add(work3);

                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration3 = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration3);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records3, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
                break;
            case R.id.nav_home:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_restaurant_cutlery)
//                );
                TodoDatabaseHelper myDbHelper4 = new TodoDatabaseHelper(this);


                final SQLiteDatabase writeDb4 = myDbHelper4.getWritableDatabase();
                Cursor data4=TodoDatabaseHelper.getInfoHome(writeDb4);



                while(data4.moveToNext()){
                    Seller work4=new Seller();


                    work4.name=data4.getString(1);
                    work4.price= data4.getString(3);
                    work4.discount=data4.getString(4);
                    work4.imgurl=data4.getString(7);

                    records4.add(work4);

                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration4 = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration4);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records4, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
                break;
            case R.id.nav_sports:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_soccer_ball_variant)
//                );
//                break;
                TodoDatabaseHelper myDbHelper5 = new TodoDatabaseHelper(this);


                final SQLiteDatabase writeDb5 = myDbHelper5.getWritableDatabase();
                Cursor data5=TodoDatabaseHelper.getInfoSport(writeDb5);



                while(data5.moveToNext()){

                    Seller work5=new Seller();

                    work5.name=data5.getString(1);
                    work5.price= data5.getString(3);
                    work5.discount=data5.getString(4);
                    work5.imgurl=data5.getString(7);

                    records5.add(work5);
                }








//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration5= new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration5);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records5, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
            case R.id.nav_automobiles:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_sedan_car_front)
//                );
                TodoDatabaseHelper myDbHelper6 = new TodoDatabaseHelper(this);


                final SQLiteDatabase writeDb6 = myDbHelper6.getWritableDatabase();
                Cursor data6=TodoDatabaseHelper.getInfoCar(writeDb6);




                while(data6.moveToNext()){

                    Seller work6=new Seller();
                    work6.name=data6.getString(1);
                    work6.price= data6.getString(3);
                    work6.discount=data6.getString(4);
                    work6.imgurl=data6.getString(7);
                    records6.add(work6);


                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration6 = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration6);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records6, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
                break;
            case R.id.nav_food:
//                fragTxn.replace(
//                        R.id.flFragContainer,
//                        IconFragment.newInstance(R.drawable.ic_food)
//                );
                TodoDatabaseHelper myDbHelper7 = new TodoDatabaseHelper(this);


                final SQLiteDatabase writeDb7 = myDbHelper7.getWritableDatabase();
                Cursor data7=TodoDatabaseHelper.getInfoFood(writeDb7);



                while(data7.moveToNext()){

                    Seller work7=new Seller();

                    work7.name=data7.getString(1);
                    work7.price= data7.getString(3);
                    work7.discount=data7.getString(4);
                    work7.imgurl=data7.getString(7);
                    records7.add(work7);


                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {

                recyclerView=(RecyclerView) findViewById(R.id.buyerItemList);
                DividerItemDecoration itemDecoration7 = new DividerItemDecoration(this, new LinearLayoutManager(BuyerActivity.this).getOrientation());
                recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this));
                recyclerView.addItemDecoration(itemDecoration7);
                recyclerView.setHasFixedSize(true);
                buyerAdapter = new BuyerAdapter(records7, getBaseContext());
                recyclerView.setAdapter(buyerAdapter);
                buyerAdapter.notifyDataSetChanged();
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
