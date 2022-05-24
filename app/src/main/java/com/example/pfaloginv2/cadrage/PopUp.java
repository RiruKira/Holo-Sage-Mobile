package com.example.pfaloginv2.cadrage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfaloginv2.cadrage.SomeView;
import com.example.pfaloginv2.R;

public class PopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pop_up);
        setContentView(new SomeView(PopUp.this));
    }
}