package com.example.sittings_views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class CustomerSeatView extends View implements View.OnClickListener {

    private Context context;
    private Button confirmBtn;
    private OnSeatSelectedListener listener;

//    String seatType = "_UUUUUUAAAAARRRR_/"
//            + "_________________/"
//            + "UU__AAAARRRRR__RR/"
//            + "UU__UUUAAAAAA__AA/"
//            + "AA__AAAAAAAAA__AA/"
//            + "AA__AARUUUURR__AA/"
//            + "UU__UUUA_RRRR__AA/"
//            + "AA__AAAA_RRAA__UU/"
//            + "AA__AARR_UUUU__RR/"
//            + "AA__UUAA_UURR__RR/"
//            + "_________________/"
//            + "UU_AAAAAAAUUUU_RR/"
//            + "RR_AAAAAAAAAAA_AA/"
//            + "AA_UUAAAAAUUUU_AA/"
//            + "AA_AAAAAAUUUUU_AA/"
//            + "_________________/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

    String seats;

    public interface OnSeatSelectedListener {
        void onSeatSelected(String selectedIds);
    }

    // Default constructor
    public CustomerSeatView(Context context,String seatType) {
        super(context);
        this.context = context;
//        seatType = "/" + seatType;
//        seats = "/" + SeatsType.getSeatLayout(SeatsType.BUS_SEATS);
        setSeatType(seatType);
        createSeatLayout();
        this.listener = listener;
    }

    // Method to set the confirm button
    public CustomerSeatView setConfirmButton(Button confirmBtn) {
        this.confirmBtn = confirmBtn;
        setupConfirmButton(); // Set up the button when it's set
        return this;
    }
    public String setSeatType(String seat){
        return seats = "/" + SeatsType.getSeatLayout(seat);
    }

    // Method to set the seat selection listener
    public CustomerSeatView setOnSeatSelectedListener(OnSeatSelectedListener listener) {
        this.listener = listener;
        return this;
    }
    private void createSeatLayout() {
        ScrollView scrollView = new ScrollView(context);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        LinearLayout layoutSeat = new LinearLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);

        horizontalScrollView.addView(layoutSeat);
        scrollView.addView(horizontalScrollView);

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
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book); // Replace with your available drawable
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved); // Replace with your reserved drawable
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }

        // Add a Confirm button at the bottom of the seat layout
        Button confirmButton = new Button(context);
        confirmButton.setText("Confirm");
        confirmButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        confirmButton.setGravity(Gravity.CENTER);
        confirmButton.setPadding(20, 10, 20, 10);

        // Set an OnClickListener to handle button click events
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle confirm button click event here
                if (listener != null) {
                    listener.onSeatSelected(selectedIds);
                }
            }
        });

//        // Add the button to the layout
        layoutSeat.addView(confirmButton);

        // Add the complete layout to your activity's content view
        ViewGroup activityContentView = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        activityContentView.addView(scrollView);
    }

    private void setupConfirmButton() {
        if (confirmBtn != null) {
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
//public class  CustomerSeatView extends View implements View.OnClickListener {
//
//    private Context context;
//    private Button confirmBtn;
//    private OnSeatSelectedListener listener;
//
//    List<TextView> seatViewList = new ArrayList<>();
//    int seatSize = 100;
//    int seatGaping = 10;
//
//    int STATUS_AVAILABLE = 1;
//    int STATUS_BOOKED = 2;
//    int STATUS_RESERVED = 3;
//    String selectedIds = "";
//    String seats;
//
//    public interface OnSeatSelectedListener {
//        void onSeatSelected(String selectedIds);
//    }
//
//
//    public void setSeats(OnSeatSelectedListener seatSelectedListener){
//        listener = seatSelectedListener;
//    }
//
//    public CustomerSeatView(Context context, Button confirmBtn,String seatsType, OnSeatSelectedListener listener ) {
//        super(context);
//        this.context = context;
//        this.confirmBtn = confirmBtn;
//        this.listener = listener;
//        //seats = "/" + seats;
//        seats = "/" + SeatsType.getSeatLayout(seatsType);
//        createSeatLayout();
//        setupConfirmButton();
//    }
//
//    private void createSeatLayout() {
//        ScrollView scrollView = new ScrollView(context);
//        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
//        LinearLayout layoutSeat = new LinearLayout(context);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutSeat.setOrientation(LinearLayout.VERTICAL);
//        layoutSeat.setLayoutParams(params);
//        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
//
//        horizontalScrollView.addView(layoutSeat);
//        scrollView.addView(horizontalScrollView);
//
//        LinearLayout layout = null;
//        int count = 0;
//        for (int index = 0; index < seats.length(); index++) {
//            if (seats.charAt(index) == '/') {
//                layout = new LinearLayout(context);
//                layout.setOrientation(LinearLayout.HORIZONTAL);
//                layoutSeat.addView(layout);
//            } else if (seats.charAt(index) == 'U') {
//                count++;
//                TextView view = new TextView(context);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
//                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
//                view.setLayoutParams(layoutParams);
//                view.setPadding(0, 0, 0, 2 * seatGaping);
//                view.setId(count);
//                view.setGravity(Gravity.CENTER);
//                view.setBackgroundResource(R.drawable.ic_seats_booked); // Replace with your booked drawable
//                view.setTextColor(Color.WHITE);
//                view.setTag(STATUS_BOOKED);
//                view.setText(count + "");
//                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
//                layout.addView(view);
//                seatViewList.add(view);
//                view.setOnClickListener(this);
//            } else if (seats.charAt(index) == 'A') {
//                count++;
//                TextView view = new TextView(context);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
//                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
//                view.setLayoutParams(layoutParams);
//                view.setPadding(0, 0, 0, 2 * seatGaping);
//                view.setId(count);
//                view.setGravity(Gravity.CENTER);
//                view.setBackgroundResource(R.drawable.ic_seats_book); // Replace with your available drawable
//                view.setText(count + "");
//                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
//                view.setTextColor(Color.BLACK);
//                view.setTag(STATUS_AVAILABLE);
//                layout.addView(view);
//                seatViewList.add(view);
//                view.setOnClickListener(this);
//            } else if (seats.charAt(index) == 'R') {
//                count++;
//                TextView view = new TextView(context);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
//                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
//                view.setLayoutParams(layoutParams);
//                view.setPadding(0, 0, 0, 2 * seatGaping);
//                view.setId(count);
//                view.setGravity(Gravity.CENTER);
//                view.setBackgroundResource(R.drawable.ic_seats_reserved); // Replace with your reserved drawable
//                view.setText(count + "");
//                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
//                view.setTextColor(Color.WHITE);
//                view.setTag(STATUS_RESERVED);
//                layout.addView(view);
//                seatViewList.add(view);
//                view.setOnClickListener(this);
//            } else if (seats.charAt(index) == '_') {
//                TextView view = new TextView(context);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
//                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
//                view.setLayoutParams(layoutParams);
//                view.setBackgroundColor(Color.TRANSPARENT);
//                view.setText("");
//                layout.addView(view);
//            }
//        }
//
//        // Add the complete layout to your activity's content view
//        ViewGroup activityContentView = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
//        activityContentView.addView(scrollView);
//    }
//
//    // ... rest of the class (setupConfirmButton, onClick) remains the same
//
//    private void setupConfirmButton() {
//        confirmBtn.setOnClickListener(v -> {
//            if (selectedIds.isEmpty()) {
//                Toast.makeText(context, "Please select a seat", Toast.LENGTH_SHORT).show();
//            } else {
//                if (listener != null) {
//                    listener.onSeatSelected(selectedIds);
//                }
//            }
//        });
//    }
//    @Override
//    public void onClick(View view) {
//        if ((int) view.getTag() == STATUS_AVAILABLE) {
//            if (selectedIds.contains(view.getId() + ",")) {
//                selectedIds = selectedIds.replace(view.getId() + ",", "");
//                view.setBackgroundResource(R.drawable.ic_seats_book); // Replace with your available drawable
//            } else {
//                selectedIds = selectedIds + view.getId() + ",";
//                view.setBackgroundResource(R.drawable.ic_seats_b); // Replace with your selected drawable
//            }
//        } else if ((int) view.getTag() == STATUS_BOOKED) {
//            Toast.makeText(context, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
//        } else if ((int) view.getTag() == STATUS_RESERVED) {
//            Toast.makeText(context, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
//        }
//    }
//}