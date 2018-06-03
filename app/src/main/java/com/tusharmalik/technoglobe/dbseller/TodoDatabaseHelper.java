package com.tusharmalik.technoglobe.dbseller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tushm on 31-05-2018.
 */

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Buyer.db";
    public static final int DB_VER = 1;
    public TodoDatabaseHelper(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BuyerTable.CMD_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
