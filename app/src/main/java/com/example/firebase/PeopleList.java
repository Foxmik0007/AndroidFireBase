package com.example.firebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PeopleList extends ArrayAdapter {

    private Activity context;
    private List<Person> peopleList;

    public PeopleList (Activity context, List <Person> peopleList){
        super(context, R.layout.list_layout, peopleList);
        this.context = context;
        this.peopleList = peopleList;
    };

    @Override
    public View getView(int position,View convertView, @NonNull ViewGroup  parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.Name);
        TextView lastName = (TextView) listViewItem.findViewById(R.id.LastName);

        Person person = peopleList.get(position);

        name.setText(person.getName());
        lastName.setText(person.getLastName());

        return  listViewItem;




    }
}
