package com.example.sitings_view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sittings_views.CustomSeatView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomSeatView customSeatView = new CustomSeatView(this);
        customSeatView.setSeAtType(2);

    }
}