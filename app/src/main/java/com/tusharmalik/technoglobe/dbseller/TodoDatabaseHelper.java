package com.tusharmalik.technoglobe.dbseller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public  Cursor getInfo(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ SellerTable.Columns.NAME +", "+SellerTable.Columns.PRICE+", "+ SellerTable.Columns.DISCOUNT
                + SellerTable.Columns.IMGURL +" FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }
}
