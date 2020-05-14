package com.iscript.d2duserapp;

import com.google.firebase.database.Exclude;

/**
 * Created by AkshayeJH on 15/12/17.
 */

public class Users {

    private String key;

    public String name, imageUrl, description;

    public Users(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return description;
    }

    public void setStatus(String status) {
        this.description = description;
    }

    public Users(String name, String image, String status) {
        this.name = name;
        this.imageUrl = image;
        this.description = status;
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
