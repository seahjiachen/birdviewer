/**
 * Created by root on 10/28/14.
 */
package com.example.user.myapplication;

public class Bird {

    public int id;
    public String name;
    public String location;
    public String image;
    public String sound;

    public Bird(){}

    public Bird(String name, String location, String image, String Sound){
        super();
        this.name = name;
        this.location = location;
        this.image = image;
        this.sound = Sound;

    }

    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setImage(String image){
        this.image = image;
    }
    public void setSound(String sound){
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "Bird [id]:" + this.id+
                " [name]: "+this.name+
                " [location]: " + this.location +
                " [Image]: " + this.image +
                " [Sound]: " + this.sound;
    }
}
