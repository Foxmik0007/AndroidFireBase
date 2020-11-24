package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConnexionActivity extends AppCompatActivity {

    private EditText name, lastName;
    private Button yes;
    private List<Person> Shit;
    private TextView u, iooo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        name = (EditText) findViewById(R.id.name);
        lastName = (EditText) findViewById(R.id.lastName);
        u = (TextView) findViewById(R.id.love);
        iooo = (TextView) findViewById(R.id.q);
        yes = (Button) findViewById(R.id.ninin);
        Shit =new ArrayList<>();
        Shit = MainActivity.people;
        iooo.setText("Size : " + Integer.toString(Shit.size()));
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (short i = 0; i < Shit.size(); i++)
                    if (Shit.get(i).getName().equals(name.getText().toString()) && Shit.get(i).getLastName().equals(lastName.getText().toString()))
                        u.setText("Connexion Success");
            }
        });

    }
}