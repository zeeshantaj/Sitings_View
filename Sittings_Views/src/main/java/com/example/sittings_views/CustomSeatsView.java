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

public class CustomSeatsView extends View implements View.OnClickListener {

    private Context context;
    private Button confirmBtn;
    private OnSeatSelectedListener listener;
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

    // Static method to access the Builder
    public static Builder with(Context context) {
        return new Builder(context);
    }

    public static class Builder {
        private Context context;
        private Button confirmBtn;
        private OnSeatSelectedListener listener;
        private String seats;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder withConfirmButton(Button confirmBtn) {
            this.confirmBtn = confirmBtn;
            return this;
        }

        public Builder withListener(OnSeatSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder withSeatType(String seatType) {
            this.seats = "/" + SeatsType.getSeatLayout(seatType);
            return this;
        }

        public CustomSeatsView build() {
            return new CustomSeatsView(context, confirmBtn, seats, listener);
        }
    }

    public CustomSeatsView(Context context, Button confirmBtn, String seatsType, OnSeatSelectedListener listener) {
        super(context);
        this.context = context;
        this.confirmBtn = confirmBtn;
        this.listener = listener;
        this.seats = "/" + SeatsType.getSeatLayout(seatsType);
        createSeatLayout();
        setupConfirmButton();
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
                TextView view = createSeatView(count, R.drawable.ic_seats_booked, STATUS_BOOKED, Color.WHITE);
                layout.addView(view);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = createSeatView(count, R.drawable.ic_seats_book, STATUS_AVAILABLE, Color.BLACK);
                layout.addView(view);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = createSeatView(count, R.drawable.ic_seats_reserved, STATUS_RESERVED, Color.WHITE);
                layout.addView(view);
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

        ViewGroup activityContentView = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        activityContentView.addView(scrollView);
    }

    private TextView createSeatView(int count, int backgroundResource, int statusTag, int textColor) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
        view.setLayoutParams(layoutParams);
        view.setPadding(0, 0, 0, 2 * seatGaping);
        view.setId(count);
        view.setGravity(Gravity.CENTER);
        view.setBackgroundResource(backgroundResource);
        view.setTextColor(textColor);
        view.setTag(statusTag);
        view.setText(count + "");
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
        seatViewList.add(view);
        view.setOnClickListener(this);
        return view;
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
                view.setBackgroundResource(R.drawable.ic_seats_book);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.ic_seats_b);
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(context, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(context, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }
}
