package com.tusharmalik.technoglobe.dbseller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tusharmalik.technoglobe.Models.Seller;
import com.tusharmalik.technoglobe.dbseller.SellerTable.Columns;

import static com.tusharmalik.technoglobe.dbseller.SellerTable.Columns.*;
import static com.tusharmalik.technoglobe.dbseller.SellerTable.TABLE_NAME;

/**
 * Created by tushm on 31-05-2018.
 */

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Seller.db";
    public static final int DB_VER = 1;
    public TodoDatabaseHelper(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SellerTable.CMD_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public static Cursor getInfo(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }



    public static Cursor getInfoElectro(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Electronics" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoMobile(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
 String q = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Mobiles" + "'";

        Cursor data = db.rawQuery(q , null);
        return data;

    }
    public static Cursor getInfoToys(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Toys" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoFashion(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Fashion" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoHome(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Home" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoSport(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Sports" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoCar(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Automobiles" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoFood(SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE category = '" + "Food" + "'";
//        rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
        Cursor data = db.rawQuery(query, null);
        return data;

    }
    public static Cursor getInfoSearch(String word,SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String searchitem="%"+word+"%";
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE name LIKE '" + searchitem + "'" ;
        Cursor data = db.rawQuery(query, null);
        return data;

    }
}
