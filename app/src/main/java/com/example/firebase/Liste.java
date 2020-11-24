package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import static com.example.firebase.MainActivity.people;

public class Liste extends AppCompatActivity implements boiteDeDialogue.dialogListener{
    ListView Table;
    List <Person> ListeDePersonne = people;
    String documents;
    String formulaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        //*************************AFFICHAGE EN LISTE********************************************
        Table = (ListView) findViewById(R.id.Table);
        PeopleList adapter = new PeopleList(Liste.this,ListeDePersonne );
        Table.setAdapter(adapter);
        //***************************************************************************************

        //Atachement d'un ecouteur
        Table.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialogBox();
                return false;
            }
        });
    }


//***********************************BOITE DE DIALOGUE***************************************************
    //Ouverture d'une boite de dialogue
    public void openDialogBox (){
        boiteDeDialogue updateDialogue = new boiteDeDialogue();
        updateDialogue.show(getSupportFragmentManager(), "Updating");
    }


    // Recuperation des donnees de la boite de dialogue et Update
    @Override
    public void applyText(String newDoc, String newForm) {
        documents = newDoc;
        formulaire = newForm;
    }
//********************************************************************************************************
}