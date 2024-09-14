package com.example.sittings_views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomerSeatView implements View.OnClickListener {

    private ViewGroup layout;
    private Context context;
    private Button confirmBtn;
    private OnSeatSelectedListener listener;

    String seats = "_UUUUUUAAAAARRRR_/"
            + "_________________/"
            + "UU__AAAARRRRR__RR/"
            + "UU__UUUAAAAAA__AA/"
            + "AA__AAAAAAAAA__AA/"
            + "AA__AARUUUURR__AA/"
            + "UU__UUUA_RRRR__AA/"
            + "AA__AAAA_RRAA__UU/"
            + "AA__AARR_UUUU__RR/"
            + "AA__UUAA_UURR__RR/"
            + "_________________/"
            + "UU_AAAAAAAUUUU_RR/"
            + "RR_AAAAAAAAAAA_AA/"
            + "AA_UUAAAAAUUUU_AA/"
            + "AA_AAAAAAUUUUU_AA/"
            + "_________________/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

    public interface OnSeatSelectedListener {
        void onSeatSelected(String selectedIds);
    }

    public CustomerSeatView(ViewGroup layout, Context context, Button confirmBtn, OnSeatSelectedListener listener) {
        this.layout = layout;
        this.context = context;
        this.confirmBtn = confirmBtn;
        this.listener = listener;
        seats = "/" + seats;
        initSeatLayout();
        setupConfirmButton();
    }

    private void initSeatLayout() {
        LinearLayout layoutSeat = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;
        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked); // Replace with your booked drawable
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(context);
                // ... (rest of the seat creation logic is the same as in your original code)
            } // ... (rest of the seat creation logic for 'R' and '_' is the same)
        }
    }

    private void setupConfirmButton() {
        confirmBtn.setOnClickListener(v -> {
            if (selectedIds.isEmpty()) {
                Toast.makeText(context, "Please select a seat", Toast.LENGTH_SHORT).show();
            } else {
                if (listener != null) {
                    listener.onSeatSelected(selectedIds);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.ic_seats_book); // Replace with your available drawable
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.ic_seats_b); // Replace with your selected drawable
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(context, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(context, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }
}