package com.example.sitings_view;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        //LinearLayout layout = findViewById(R.id.layoutSeat);
        Button confirmBtn = findViewById(R.id.confirm);


//        CustomerSeatView seatSelection = new CustomerSeatView( this, confirmBtn, new CustomerSeatView.OnSeatSelectedListener() {
//            @Override
//            public void onSeatSelected(String selectedIds) {
//                Toast.makeText(MainActivity.this, "seat no "+selectedIds, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}