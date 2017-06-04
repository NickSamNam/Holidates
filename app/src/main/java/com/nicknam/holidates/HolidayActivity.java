package com.nicknam.holidates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class HolidayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        Holiday holiday = (Holiday) getIntent().getSerializableExtra("holiday");

        getSupportActionBar().setTitle(holiday.getName());

        List<Period> periods = holiday.getPeriods();
        ListView periodLV = (ListView) findViewById(R.id.activityHoliday_lv_periods);
        PeriodAdapter periodAdapter = new PeriodAdapter(this, getLayoutInflater(), periods);
        periodLV.setAdapter(periodAdapter);
    }
}
