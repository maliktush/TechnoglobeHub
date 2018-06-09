package com.tusharmalik.technoglobe.Models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tushm on 31-05-2018.
 */

public class Seller {
    String name;
    String description;
    int id;
    String price;
    String quantity;
    String imgurl;
    String imgurl2;
    String imgurl3;
    String imgurl4;
    String imgurl5;

    public Seller(int id, String string, String b, String p, String q, String url, String url2, String url3, String url4, String url5) {
        this.id=id;
        this.name=string;
        this.description=b;
        this.price=p;
        this.quantity=q;
        this.imgurl=url;
        this.imgurl2=url2;
        this.imgurl3=url3;
        this.imgurl4=url4;
        this.imgurl5=url5;
    }
//    public Seller(int id, String string, String b, String p, String q) {
//        this.id=id;
//        this.name=string;
//        this.description=b;
//        this.price=p;
//        this.quantity=q;
//    }
    public Seller(String string, String b, String p, String q) {

        this.name=string;
        this.description=b;
        this.price=p;
        this.quantity=q;
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
    public String getQuantity() {
        return quantity;
    }
    public String getImgurl() {return imgurl;}
    public void setDescription(String description) {
        this.description = description;
    }
    public void setData(String name) {
        this.name = name;
    }
    public void setPrice(String p) {
        this.price = p;
    }
    public void setQuantity(String q) {
        this.quantity = q;
    }
    public String getImgurl2() {return imgurl2;}
    public String getImgurl3() {return imgurl3;}
    public String getImgurl4() {return imgurl4;}
    public String getImgurl5() {return imgurl5;}
    public int getId() {return id;}
}
