package com.tusharmalik.technoglobe.Models;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

/**
 * Created by tushm on 31-05-2018.
 */

public class Seller implements Serializable {
    public String name;
    String description;
    int id;
    public String price;
    public String discount;
    String quantity;
    String category;
    public String imgurl;
    String imgurl2;
    String imgurl3;
    String imgurl4;
    String imgurl5;
    String verify;
    public Seller(){}

    public Seller(int id, String string, String b, String p,String d,String q,String category,String verify, String url, String url2, String url3, String url4, String url5) {
        this.id=id;
        this.name=string;
        this.description=b;
        this.price=p;
        this.discount=d;
        this.quantity=q;
        this.category=category;
        this.imgurl=url;
        this.imgurl2=url2;
        this.imgurl3=url3;
        this.imgurl4=url4;
        this.imgurl5=url5;
        this.verify=verify;
    }

    public Seller(int id,String string,String p, String q,String url) {
        this.id=id;
        this.name=string;
        this.price=p;
        this.discount=q;
        this.imgurl=url;
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
    public String getDiscount() {return discount;}
    public String getQuantity() {
        return quantity;
    }
    public  String getCategory() {return category;}
    public String getImgurl() {return imgurl;}
    public String getVerify() {
        return verify;
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
    public void setDiscount(String discount) {this.discount = discount;}
    public void setQuantity(String q) {
        this.quantity = q;
    }
    public void setCategory(String c) {this.category=c;}
    public void setVerify(String c) {this.verify=c;}
    public String getImgurl2() {return imgurl2;}
    public String getImgurl3() {return imgurl3;}
    public String getImgurl4() {return imgurl4;}
    public String getImgurl5() {return imgurl5;}
    public int getId() {return id;}
}
