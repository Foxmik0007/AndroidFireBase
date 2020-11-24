package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button Enter, access, sElse, Connexion;
    public static List<Person> people, people2;
    private String theRealName;
    private TextView number, showSize;
    ListView LisViewPeople;

    DatabaseReference PersonDataBase;
    DatabaseReference CarDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // CATCHING
        PersonDataBase = FirebaseDatabase.getInstance().getReference("People");
        CarDataBase = FirebaseDatabase.getInstance().getReference("Car");

        nom = (EditText) findViewById(R.id.Name);
        prenom = (EditText) findViewById(R.id.Last);
        Enter = (Button) findViewById(R.id.Enter);
        Connexion = (Button) findViewById(R.id.Connexion);

        people = new ArrayList<>();
//$
        LisViewPeople = (ListView) findViewById(R.id.People);
        access = (Button) findViewById(R.id.Access);
        sElse = (Button) findViewById(R.id.sElse);
        people.add(new Person("0", "Mika", "Rafaralahy"));
        theRealName = nom.getText().toString().trim();
        people2 = new ArrayList<>();
        number = (TextView)findViewById(R.id.number);
        showSize = (TextView)findViewById(R.id.showSize);



       //Update();
        // Affichage taille du tableau
        showSize.setText(Integer.toString(people.size()));

        //Entrer des données sur la base de donnée
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPerson();
            }
        });

        //Voir les données sur la base de donnée
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Liste.class);
                startActivityForResult(intent, 0);
            }
        });

        // Test avec autre type de classe
        sElse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Gallardo";
                String marque = "Lamborghini";
                String id = PersonDataBase.push().getKey();
                Car car = new Car(name, marque, id);
                CarDataBase.child(id).setValue(car);
            }
        });
        Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),  ConnexionActivity.class);
                    startActivityForResult(intent, 0 );
            }
        });
    }

    protected void Update() {

        PersonDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot peopleSnapShot : dataSnapshot.getChildren()) {
                    Person person = peopleSnapShot.getValue(Person.class);
                    boolean Verify = false;

                    // Ajout/ Mise à jour de la base de donnée
                    for (short i = 0; i < people.size(); i++) {
                        if (person.getName() == people.get(i).getName())
                            Verify = true;

                    }
                    if (!Verify)
                        people.add(person);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        PersonDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot peopleSnapShot : dataSnapshot.getChildren()) {
                    Person person = peopleSnapShot.getValue(Person.class);
                    boolean Verify = false;

                    // Ajout/ Mise à jour de la base de donnée
                    for (short i = 0; i < people.size(); i++) {
                        if (person.getName() == people.get(i).getName())
                            Verify = true;

                    }
                    if (!Verify)
                        people.add(person);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void AddPerson() {
        String name = nom.getText().toString().trim();
        String lastName = prenom.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {
            String id = PersonDataBase.push().getKey();
            Person person = new Person(id, name, lastName);

            PersonDataBase.child(id).setValue(person);

            Toast.makeText(this, "Complete", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "You MF", Toast.LENGTH_LONG).show();
        }

    }

    public boolean isHere( /*Person anyone*/ String a, List<Person> list) {
        for (short i = 0; i < list.size(); i++) {
            if (/*anyone.getName() */a == list.get(i).getName())
                return true;
        }
        return false;
    }

    public void goToNext(){
        Intent intent = new Intent(getApplicationContext(), connexionSample.class);
        if (isHere("Anna", people))
            startActivityForResult(intent, 0 );
    }
}
