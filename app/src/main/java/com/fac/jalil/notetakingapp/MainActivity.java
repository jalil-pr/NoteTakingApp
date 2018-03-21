package com.fac.jalil.notetakingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    ListView listView ;
    static List<String> list;
    static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        list=new ArrayList<>();
        sharedPreferences=getSharedPreferences("com.fac.jalil.notetakingapp", Context.MODE_PRIVATE);
        HashSet<String> set=(HashSet)sharedPreferences.getStringSet("notes",null);
        if(set==null)
        {
            list.add("example text");
        }
        else
        {
            list=new ArrayList<>(set);
        }


        adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),EditActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);

            }
        });


     listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
             final int itemToDelete=i;
             new AlertDialog.Builder(MainActivity.this)
                     .setTitle("Delete?")
                     .setIcon(android.R.drawable.ic_dialog_alert)
                     .setPositiveButton("yes",new DialogInterface.OnClickListener()
                     {

                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             list.remove(itemToDelete);
                             adapter.notifyDataSetChanged();
                             HashSet<String> set=new HashSet<>(MainActivity.list);
                             sharedPreferences.edit().putStringSet("notes",set).apply();

                         }
                     })
                     .setNegativeButton("no",null).show();
             return true;
         }
     });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent=new Intent(getApplicationContext(),EditActivity.class);
        startActivity(intent);
        return true;
    }


}
