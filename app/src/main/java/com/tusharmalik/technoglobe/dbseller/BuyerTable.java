package com.tusharmalik.technoglobe.dbseller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tusharmalik.technoglobe.Models.Buyer;

import java.util.ArrayList;

import static com.tusharmalik.technoglobe.dbseller.Const.CMD_CREATE_TABLE_INE;
import static com.tusharmalik.technoglobe.dbseller.Const.COMMA;
import static com.tusharmalik.technoglobe.dbseller.Const.LBR;
import static com.tusharmalik.technoglobe.dbseller.Const.RBR;
import static com.tusharmalik.technoglobe.dbseller.Const.SEMI;
import static com.tusharmalik.technoglobe.dbseller.Const.TYPE_INT;
import static com.tusharmalik.technoglobe.dbseller.Const.TYPE_PK_AI;
import static com.tusharmalik.technoglobe.dbseller.Const.TYPE_TEXT;

/**
 * Created by tushm on 31-05-2018.
 */

public class BuyerTable {
    private BuyerTable(){}
    public static final String TABLE_NAME = "buyerz";
    public interface Columns {
        String ID="id";
        String NAME="name";
        String DESCRIPTION="description";
        String PRICE="price";
    }
    public static final String CMD_CREATE_TABLE =
            CMD_CREATE_TABLE_INE +  TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK_AI + COMMA
                    + Columns.NAME +TYPE_TEXT +COMMA
                    + Columns.DESCRIPTION + TYPE_TEXT+ COMMA
                    + Columns.PRICE + TYPE_TEXT
                    + RBR + SEMI;
    public static long insertBuyerItem (Buyer buyer, SQLiteDatabase db) {
        ContentValues newBuyerItem = new ContentValues();

        newBuyerItem.put(Columns.NAME, buyer.getData());
        newBuyerItem.put(Columns.DESCRIPTION, buyer.getDescription());
        newBuyerItem.put(Columns.PRICE, buyer.getPrice());
        return db.insert(TABLE_NAME, null, newBuyerItem);
    }
    public static void deleteItem(SQLiteDatabase db, int t){
        try{

            db.delete(TABLE_NAME, "ID="+t, null);

        }
        catch(Exception e){}

    }

    public static Buyer getRecord(int id, SQLiteDatabase db){
        id = 1;
        Cursor cursor = db.query(TABLE_NAME,new String[]{
                Columns.ID,
                Columns.NAME,
                Columns.DESCRIPTION,
                Columns.PRICE,

        }, Columns.ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!= null && cursor.moveToFirst()) {

            Buyer buyer = new Buyer(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));

            return buyer;
        }
        else return null;
    }
    public static ArrayList<Buyer> getAllTodos (SQLiteDatabase dbseller) {
        Cursor c = dbseller.query(
                TABLE_NAME,
                new String[]{Columns.ID, Columns.NAME, Columns.DESCRIPTION, Columns.PRICE},
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Buyer> todos = new ArrayList<>();
        c.moveToFirst();
        int nameIndex = c.getColumnIndex(Columns.NAME);
        int idIndex = c.getColumnIndex(Columns.ID);
        int descriptionIndex = c.getColumnIndex(Columns.DESCRIPTION);
        int priceIndex = c.getColumnIndex(Columns.PRICE);

        while (!c.isAfterLast()) {
            todos.add(new Buyer(
                    c.getInt(idIndex),
                    c.getString(nameIndex),
                    c.getString(descriptionIndex),
                    c.getString(priceIndex)
            ));

            c.moveToNext();
        }
        return todos;
    }


}
