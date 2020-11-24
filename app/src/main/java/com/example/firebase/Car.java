package com.example.firebase;

public class Car {
    private String name, marque, carID;

    public Car(){};

    public Car(String name, String marque, String carID) {
        this.name = name;
        this.marque = marque;
        this.carID = carID;
    }

    public String getName() {
        return name;
    }

    public String getMarque() {
        return marque;
    }

    public String getCarID(){
        return carID;
    }
}
