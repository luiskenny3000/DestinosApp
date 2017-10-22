package com.kaisapp.destinosapp;

import java.io.Serializable;

/**
 * Created by kennyorellana on 21/10/17.
 */

public class Destiny implements Serializable{
    public String name;
    public String city;
    public String location;
    public String image;

    public String getAddress(){
        return location +", "+ city;
    }
}
