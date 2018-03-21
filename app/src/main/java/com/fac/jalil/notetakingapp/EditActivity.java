package com.fac.jalil.notetakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashMap;
import java.util.HashSet;

public class EditActivity extends AppCompatActivity {

    EditText editText;
    int position;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        sharedPreferences=getSharedPreferences("com.fac.jalil.notetakingapp", Context.MODE_PRIVATE);
        editText=(EditText)findViewById(R.id.editText);
        position=getIntent().getIntExtra("position",-1);
        if(position!=-1)
        {
            editText.setText(MainActivity.list.get(position));
        }
        else
        {
            MainActivity.list.add("");
            position=MainActivity.list.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.list.set(position,String.valueOf(charSequence));
                MainActivity.adapter.notifyDataSetChanged();
                HashSet<String> set=new HashSet<>(MainActivity.list);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
