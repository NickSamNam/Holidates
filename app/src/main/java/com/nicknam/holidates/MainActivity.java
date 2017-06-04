package com.nicknam.holidates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Holiday> holidays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        holidays = new ArrayList<>();
        List<Period> p1 = new ArrayList<>();
        p1.add(new Period("North", new Date(1496534400000L), new Date(1496966400000L)));
        p1.add(new Period("Middle", new Date(1496707200000L), new Date(1497139200000L)));
        p1.add(new Period("South", new Date(1496880000000L), new Date(1497312000000L)));
        holidays.add(new Holiday("Summer vacation", true, p1));

        ListView holidaysLV = (ListView) findViewById(R.id.activityMain_lv_holidays);
        HolidayAdapter holidayAdapter = new HolidayAdapter(this, getLayoutInflater(), holidays);
        holidaysLV.setAdapter(holidayAdapter);
        holidaysLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), HolidayActivity.class);
        intent.putExtra("holiday", holidays.get(position));
        startActivity(intent);
    }
}