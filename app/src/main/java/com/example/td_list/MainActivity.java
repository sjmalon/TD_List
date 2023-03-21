package com.example.td_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    EditText input;
    ImageView enter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        input=findViewById(R.id.input);
        enter=findViewById(R.id.add);

        items = new ArrayList<>();
        items.add("Shower");
        items.add("Meal");
        items.add("Work");
        items.add("Feed Dog");
        items.add("Play Music");
        items.add("Sleep");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=items.get(i);
                makeToast(name);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                makeToast("Long Press: " + items.get(i));
                return false;
            }
        });

        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row, items);
        //adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=input.getText().toString();
                if (text == null || text.length()==0) {
                    makeToast("Enter an item.");
                }
                else {
                    addItem(text);
                    input.setText("");
                    makeToast("Added " + text);
                }
            }
        });
    }

   /* public void listViewTextColor(View view) {
        listView.setTextColor(Color.RED);
    }
     public void topEditTextColor(View view) {
        input.setTextColor(Color.RED);
    }

     */



    public void addItem(String item) {
        items.add(item);
        listView.setAdapter(adapter);

    }

    Toast t;


    private void makeToast(String s) {
        if (t != null)t.cancel();
        t=Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}