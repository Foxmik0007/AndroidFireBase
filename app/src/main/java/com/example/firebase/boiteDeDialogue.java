package com.example.firebase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class boiteDeDialogue extends AppCompatDialogFragment {


    private EditText documents, formulaires;

    //Recuperation des donnees
    private dialogListener listener;

    //Creation de la boite de dialogue
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue, null);

        //Configuration des boutons
        builder.setView(view)
                //Titre
                .setTitle("UPDATE")
                //Bouton d'annulation + sequences de ligne de code à executer si appuyer
                .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                //Bouton de confirmation + sequences de ligne de code à executer
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Recuperation des données
                        String newDoc = documents.getText().toString();
                        String newFom = formulaires.getText().toString();
                        listener.applyText(newDoc,newFom);
                    }
                });

        //Catching
        documents = (EditText) view.findViewById(R.id.Documents);
        formulaires = (EditText) view.findViewById(R.id.Form);

        return builder.create();
    }


    //Activation de la boite d'ecoute
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (dialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "dialogListener n'a pas été implementé");
        }
    }

    //Transfert de donnees vers l'activité principale
    public interface dialogListener {
        void applyText(String newDoc, String newForm);
    }
}
