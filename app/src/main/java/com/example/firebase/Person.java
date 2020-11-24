package com.example.firebase;

public class Person {
    private String name;
    private String lastName;
    private String PersonID;

    //Constructeur par defaut
    public Person(){

    }

    // Constructeur d'initialisation
    public Person(String id, String Name, String LastName ){
        this.PersonID = id;
        this.name = Name;
        this.lastName = LastName;
    }

    //Accesseur
    public String getName(){
        return name;
    }
    public String getLastName() { return lastName;}

    // Mutateur
   /* public void setName (String OName){
        name = OName;
    }
    public void setLastname ( String OLastName){ lastName = OLastName; }*/
}
