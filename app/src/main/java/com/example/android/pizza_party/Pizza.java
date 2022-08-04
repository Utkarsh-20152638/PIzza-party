package com.example.android.pizza_party;

public class Pizza {
    int id;
    int imgid;
    String name;
    int price;
    int quantity = 0;
    String address;
    public Pizza() {
    }

    public Pizza( String name, int price, int imgid ) {
        this.name = name;
        this.imgid = imgid;
        this.price = price;

    }
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }
    public int getImgid() {
        return imgid;
    }

    public void setImgid( int imgid ) {
        this.imgid = imgid;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress ( String address ) {
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice( int price ) {
        this.price = price;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

}
