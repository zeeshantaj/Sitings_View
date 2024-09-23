package com.example.sitings_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sittings_views.CustomerSeatView;
import com.example.sittings_views.SeatsType;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        Button confirmBtn = findViewById(R.id.confirm);

        CustomerSeatView seatView = new CustomerSeatView(this, confirmBtn, SeatsType.PLANE_SEATS, new CustomerSeatView.OnSeatSelectedListener() {
            @Override
            public void onSeatSelected(String selectedIds) {

                Toast.makeText(MainActivity.this, "selected seat "+selectedIds, Toast.LENGTH_SHORT).show();
            }
        });

    }
}