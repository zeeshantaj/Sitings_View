package com.example.sitings_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sittings_views.CustomerSeatView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        LinearLayout layout = findViewById(R.id.layoutSeat);
        Button confirmBtn = findViewById(R.id.confirm);


        CustomerSeatView seatSelection = new CustomerSeatView(this)
                .setConfirmButton(confirmBtn)
                .setOnSeatSelectedListener(new CustomerSeatView.OnSeatSelectedListener() {
                    @Override
                    public void onSeatSelected(String selectedIds) {

                    }
                });
    }
}