package com.tusharmalik.technoglobe.dbseller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.tusharmalik.technoglobe.Cart;
import com.tusharmalik.technoglobe.Main2Activity;
import com.tusharmalik.technoglobe.MainActivity;
import com.tusharmalik.technoglobe.Models.Cartmodel;
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
 * Created by tushm on 09-07-2018.
 */

public class CartTable {
    private CartTable(){}

    public static final String TABLE_NAME2 = "Cart";

    public interface Columns {
        String IDCART="idcart";
        String NAMECART="namecart";
        String DESCRIPTIONCART="descriptioncart";
        String PRICECART="pricecart";
        String DISCOUNTCART="discountcart";
        String QUANTITYCART="quantitycart";
        String CATEGORYCART="categorycart";
        String VERIFYCART="verifycart";
        String IMGURLCART="imgurlcart";
        String IMGURL2CART="imgurl2cart";
        String IMGURL3CART="imgurl3cart";
        String IMGURL4CART="imgurl4cart";
        String IMGURL5CART="imgurl5cart";
    }


    public static final String CMD_CREATE_TABLE =
            CMD_CREATE_TABLE_INE +  TABLE_NAME2
                    + LBR
                    + Columns.IDCART + TYPE_INT + TYPE_PK_AI + COMMA
                    + Columns.NAMECART +TYPE_TEXT +COMMA
                    + Columns.DESCRIPTIONCART + TYPE_TEXT+ COMMA
                    + Columns.PRICECART + TYPE_TEXT + COMMA
                    + Columns.DISCOUNTCART + TYPE_TEXT + COMMA
                    + Columns.QUANTITYCART + TYPE_TEXT+ COMMA
                    + Columns.CATEGORYCART + TYPE_TEXT+ COMMA
                    + Columns.VERIFYCART + TYPE_TEXT+ COMMA
                    + Columns.IMGURLCART + TYPE_TEXT + COMMA
                    + Columns.IMGURL2CART + TYPE_TEXT + COMMA
                    + Columns.IMGURL3CART + TYPE_TEXT + COMMA
                    + Columns.IMGURL4CART + TYPE_TEXT + COMMA
                    + Columns.IMGURL5CART + TYPE_TEXT
                    + RBR + SEMI;
    public static long insertCartItem (Cartmodel cart, SQLiteDatabase db) {
        ContentValues newBuyerItem = new ContentValues();

        newBuyerItem.put(Columns.NAMECART, cart.getDatac());
        newBuyerItem.put(Columns.DESCRIPTIONCART, cart.getDescriptionc());
        newBuyerItem.put(Columns.PRICECART, cart.getPricec());
        newBuyerItem.put(Columns.DISCOUNTCART, cart.getDiscountc());
        newBuyerItem.put(Columns.QUANTITYCART, cart.getQuantityc());
        newBuyerItem.put(Columns.CATEGORYCART, cart.getCategoryc());
        newBuyerItem.put(Columns.VERIFYCART, cart.getVerifyc());
        newBuyerItem.put(Columns.IMGURLCART, cart.getImgurlc());
        newBuyerItem.put(Columns.IMGURL2CART, cart.getImgurl2c());
        newBuyerItem.put(Columns.IMGURL3CART, cart.getImgurl3c());
        newBuyerItem.put(Columns.IMGURL4CART, cart.getImgurl4c());
        newBuyerItem.put(Columns.IMGURL5CART, cart.getImgurl5c());
        return db.insert(TABLE_NAME2, null, newBuyerItem);
    }
    public static void deleteCartItem(SQLiteDatabase db, int t){
        try{

            db.delete(TABLE_NAME2, "IDCART="+t, null);
        }
        catch(Exception e){}

    }

    public static  String getCartTableAsString(SQLiteDatabase db, String tableName) {

        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName , null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }
    public static Cartmodel getCartRecord(String description, SQLiteDatabase db){
//        id = 1;
        Cursor cursor = db.query(TABLE_NAME2,new String[]{
                Columns.IDCART,
                Columns.NAMECART,
                Columns.DESCRIPTIONCART,
                Columns.PRICECART,
                Columns.DISCOUNTCART,
                Columns.QUANTITYCART,
                Columns.CATEGORYCART,
                Columns.VERIFYCART,
                Columns.IMGURLCART,
                Columns.IMGURL2CART,
                Columns.IMGURL3CART,
                Columns.IMGURL4CART,
                Columns.IMGURL5CART,


        }, Columns.DESCRIPTIONCART + "=?", new String[]{String.valueOf(description)},null,null,null,null);

        if(cursor!= null && cursor.moveToFirst()) {

            Cartmodel cartmodel = new Cartmodel(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),cursor.getString(11),cursor.getString(12));

            return cartmodel;
        }
        else return null;
    }
    public static ArrayList<Cartmodel> getAllCartTodos (SQLiteDatabase dbcart) {
        String abc= Main2Activity.mobilenumber[0];
        Cursor c = dbcart.query(
                TABLE_NAME2,
                new String[]{Columns.IDCART, Columns.NAMECART, Columns.DESCRIPTIONCART, Columns.PRICECART, Columns.DISCOUNTCART,
                        Columns.QUANTITYCART, Columns.CATEGORYCART, Columns.VERIFYCART, Columns.IMGURLCART
                        , Columns.IMGURL2CART, Columns.IMGURL3CART, Columns.IMGURL4CART, Columns.IMGURL5CART},
                Columns.VERIFYCART + "=?", new String[]{String.valueOf(abc)},
                null,
                null,
                null
        );
        ArrayList<Cartmodel> todo = new ArrayList<>();
        c.moveToFirst();
        int nameIndex = c.getColumnIndex(Columns.NAMECART);
        int idIndex = c.getColumnIndex(Columns.IDCART);
        int descriptionIndex = c.getColumnIndex(Columns.DESCRIPTIONCART);
        int priceIndex = c.getColumnIndex(Columns.PRICECART);
        int DiscountIndex = c.getColumnIndex(Columns.DISCOUNTCART);
        int QuantityIndex = c.getColumnIndex(Columns.QUANTITYCART);
        int CategoryIndex = c.getColumnIndex(Columns.CATEGORYCART);
        int VerifyIndex = c.getColumnIndex(Columns.VERIFYCART);
        int ImgURLIndex = c.getColumnIndex(Columns.IMGURLCART);
        int ImgURLIndex2 = c.getColumnIndex(Columns.IMGURL2CART);
        int ImgURLIndex3= c.getColumnIndex(Columns.IMGURL3CART);
        int ImgURLIndex4 = c.getColumnIndex(Columns.IMGURL4CART);
        int ImgURLIndex5 = c.getColumnIndex(Columns.IMGURL5CART);


        while (!c.isAfterLast()) {
            todo.add(new Cartmodel(
                    c.getInt(idIndex),
                    c.getString(nameIndex),
                    c.getString(descriptionIndex),
                    c.getString(priceIndex),
                    c.getString(DiscountIndex),
                    c.getString(QuantityIndex),
                    c.getString(CategoryIndex),
                    c.getString(VerifyIndex),
                    c.getString(ImgURLIndex),
                    c.getString(ImgURLIndex2),
                    c.getString(ImgURLIndex3),
                    c.getString(ImgURLIndex4),
                    c.getString(ImgURLIndex5)

            ));

            c.moveToNext();
        }
        return todo;
    }

}
