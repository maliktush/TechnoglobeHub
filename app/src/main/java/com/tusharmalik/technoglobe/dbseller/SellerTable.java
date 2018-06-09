package com.tusharmalik.technoglobe.dbseller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tusharmalik.technoglobe.Models.Seller;

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

public class SellerTable {
    private SellerTable(){}
    public static final String TABLE_NAME = "buyerz";
    public interface Columns {
        String ID="id";
        String NAME="name";
        String DESCRIPTION="description";
        String PRICE="price";
        String QUANTITY="quantity";
        String IMGURL="imgurl";
        String IMGURL2="imgurl2";
        String IMGURL3="imgurl3";
        String IMGURL4="imgurl4";
        String IMGURL5="imgurl5";
    }
    public static final String CMD_CREATE_TABLE =
            CMD_CREATE_TABLE_INE +  TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK_AI + COMMA
                    + Columns.NAME +TYPE_TEXT +COMMA
                    + Columns.DESCRIPTION + TYPE_TEXT+ COMMA
                    + Columns.PRICE + TYPE_TEXT + COMMA
                    + Columns.QUANTITY + TYPE_TEXT+ COMMA
                    + Columns.IMGURL + TYPE_TEXT + COMMA
                    + Columns.IMGURL2 + TYPE_TEXT + COMMA
                    + Columns.IMGURL3 + TYPE_TEXT + COMMA
                    + Columns.IMGURL4 + TYPE_TEXT + COMMA
                    + Columns.IMGURL5 + TYPE_TEXT
                    + RBR + SEMI;
    public static long insertBuyerItem (Seller seller, SQLiteDatabase db) {
        ContentValues newBuyerItem = new ContentValues();

        newBuyerItem.put(Columns.NAME, seller.getData());
        newBuyerItem.put(Columns.DESCRIPTION, seller.getDescription());
        newBuyerItem.put(Columns.PRICE, seller.getPrice());
        newBuyerItem.put(Columns.QUANTITY, seller.getQuantity());
        newBuyerItem.put(Columns.IMGURL, seller.getImgurl());
        newBuyerItem.put(Columns.IMGURL2, seller.getImgurl2());
        newBuyerItem.put(Columns.IMGURL3, seller.getImgurl3());
        newBuyerItem.put(Columns.IMGURL4, seller.getImgurl4());
        newBuyerItem.put(Columns.IMGURL5, seller.getImgurl5());
        return db.insert(TABLE_NAME, null, newBuyerItem);
    }
    public static void deleteItem(SQLiteDatabase db, int t){
        try{

            db.delete(TABLE_NAME, "ID="+t, null);

        }
        catch(Exception e){}

    }

    public static Seller getRecord(int id, SQLiteDatabase db){
        id = 1;
        Cursor cursor = db.query(TABLE_NAME,new String[]{
                Columns.ID,
                Columns.NAME,
                Columns.DESCRIPTION,
                Columns.PRICE,
                Columns.QUANTITY,
                Columns.IMGURL,
                Columns.IMGURL2,
                Columns.IMGURL3,
                Columns.IMGURL4,
                Columns.IMGURL5,


        }, Columns.ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!= null && cursor.moveToFirst()) {

            Seller seller = new Seller(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8), cursor.getString(9));

            return seller;
        }
        else return null;
    }
    public static ArrayList<Seller> getAllTodos (SQLiteDatabase dbseller) {
        Cursor c = dbseller.query(
                TABLE_NAME,
                new String[]{Columns.ID, Columns.NAME, Columns.DESCRIPTION, Columns.PRICE, Columns.QUANTITY,Columns.IMGURL
                        ,Columns.IMGURL2,Columns.IMGURL3,Columns.IMGURL4,Columns.IMGURL5},
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Seller> todos = new ArrayList<>();
        c.moveToFirst();
        int nameIndex = c.getColumnIndex(Columns.NAME);
        int idIndex = c.getColumnIndex(Columns.ID);
        int descriptionIndex = c.getColumnIndex(Columns.DESCRIPTION);
        int priceIndex = c.getColumnIndex(Columns.PRICE);
        int QuantityIndex = c.getColumnIndex(Columns.QUANTITY);
        int ImgURLIndex = c.getColumnIndex(Columns.IMGURL);
        int ImgURLIndex2 = c.getColumnIndex(Columns.IMGURL2);
        int ImgURLIndex3= c.getColumnIndex(Columns.IMGURL3);
        int ImgURLIndex4 = c.getColumnIndex(Columns.IMGURL4);
        int ImgURLIndex5 = c.getColumnIndex(Columns.IMGURL5);


        while (!c.isAfterLast()) {
            todos.add(new Seller(
                    c.getInt(idIndex),
                    c.getString(nameIndex),
                    c.getString(descriptionIndex),
                    c.getString(priceIndex),
                    c.getString(QuantityIndex),
                    c.getString(ImgURLIndex),
                    c.getString(ImgURLIndex2),
                    c.getString(ImgURLIndex3),
                    c.getString(ImgURLIndex4),
                    c.getString(ImgURLIndex5)
            ));

            c.moveToNext();
        }
        return todos;
    }


}
