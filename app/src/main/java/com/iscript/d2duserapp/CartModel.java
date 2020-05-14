package com.iscript.d2duserapp;

import com.google.firebase.database.Exclude;

public class CartModel {

    private String Name;
    private String userUrl;
    private String key;
    private String Description;
    private String Price;
    private int position;

    public CartModel() {
        //empty constructor needed
    }
    public CartModel(int position){
        this.position = position;
    }
    public CartModel(String name, String imageUrl , String Des, String price) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.Name = name;
        this.userUrl = imageUrl;
        this.Description = Des;
        this.Price = price;
    }
    public String getPrice() {
        return Price;
    }
    public void setPrice(String price) {
        this.Price = price;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        this.Description = description;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getImageUrl() {
        return userUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.userUrl = imageUrl;
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}


