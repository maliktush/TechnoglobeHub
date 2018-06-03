package com.tusharmalik.technoglobe.Models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tushm on 31-05-2018.
 */

public class Buyer {
    String name;
    String description;
    int id;
    String price;

    public Buyer(int id,String string, String b,String p) {
        this.id=id;
        this.name=string;
        this.description=b;
        this.price=p;
    }
    public Buyer(String string, String b, String p) {

        this.name=string;
        this.description=b;
        this.price=p;
    }
    public String getDescription() {
        return description;
    }
    public String getData() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setData(String name) {
        this.name = name;
    }
    public void setPrice(String p) {
        this.price = p;
    }

    public int getId() {return id;}
}
