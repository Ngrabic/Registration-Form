package com.example.registrationform;

import android.graphics.Bitmap;
import android.net.Uri;

public class People {
    private String name;
    private String email;
    private String password;
    private  String country;
    private int gender;
    private Uri image;


    public People(String name, String email, String password, String country, int gender,Uri img) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country=country;
        this.gender=gender;
        this.image=img;
    }

    public People() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Uri  getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
