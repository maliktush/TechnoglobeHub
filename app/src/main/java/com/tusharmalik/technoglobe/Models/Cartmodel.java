package com.tusharmalik.technoglobe.Models;

/**
 * Created by tushm on 10-07-2018.
 */

public class Cartmodel {
    public String namecart;
    public String descriptioncart;
    int idcart;
    public String pricecart;
    public String discountcart;
    public String quantitycart;
    public String categorycart;
    public String imgurlcart;
    public String imgurl2cart;
    public String imgurl3cart;
    public String imgurl4cart;
    public String imgurl5cart;
    public String verifycart;
    public Cartmodel(){}

    public Cartmodel(int id, String string, String b, String p,String d,String q,String category,String verify, String url, String url2, String url3, String url4, String url5) {
        this.idcart=id;
        this.namecart=string;
        this.descriptioncart=b;
        this.pricecart=p;
        this.discountcart=d;
        this.quantitycart=q;
        this.categorycart=category;
        this.imgurlcart=url;
        this.imgurl2cart=url2;
        this.imgurl3cart=url3;
        this.imgurl4cart=url4;
        this.imgurl5cart=url5;
        this.verifycart=verify;
    }

//    public Cartmodel(int id,String string,String p, String q,String url) {
//        this.id=id;
//        this.name=string;
//        this.price=p;
//        this.discount=q;
//        this.imgurl=url;
//    }
    public String getDescriptionc() {
        return descriptioncart;
    }
    public String getDatac() {
        return namecart;
    }
    public String getPricec() {
        return pricecart;
    }
    public String getDiscountc() {return discountcart;}
    public String getQuantityc() {
        return quantitycart;
    }
    public  String getCategoryc() {return categorycart;}
    public String getImgurlc() {return imgurlcart;}
    public String getVerifyc() {return verifycart;
    }
    public void setDescriptionc(String description) {
        this.descriptioncart = description;
    }
    public void setDatac(String name) {this.namecart = name;
    }
    public void setPricec(String p) {
        this.pricecart = p;
    }
    public void setDiscountc(String discount) {this.discountcart = discount;}
    public void setQuantityc(String q) {
        this.quantitycart = q;
    }
    public void setCategoryc(String c) {this.categorycart=c;}
    public void setVerifyc(String c) {this.verifycart=c;}
    public String getImgurl2c() {return imgurl2cart;}
    public String getImgurl3c() {return imgurl3cart;}
    public String getImgurl4c() {return imgurl4cart;}
    public String getImgurl5c() {return imgurl5cart;}
    public int getIdc() {return idcart;}
}

