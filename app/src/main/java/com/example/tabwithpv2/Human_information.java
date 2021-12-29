package com.example.tabwithpv2;

public class Human_information {
    private int image;
    private String name;
    private String phoneNumber;

    public Human_information(int image, String name, String phoneNumber){
        this.image = image;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getImage()
    {
        return this.image;
    }

    public String getName()
    {
        return this.name;
    }

    public String getNumber()
    {
        return this.phoneNumber;
    }
}
