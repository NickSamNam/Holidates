package com.nicknam.holidates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by snick on 4-6-2017.
 */

public class HolidayAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Holiday> holidays;

    public HolidayAdapter(Context context, LayoutInflater layoutInflater, List<Holiday> holidays) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.holidays = holidays;
    }

    @Override
    public int getCount() {
        return holidays.size();
    }

    @Override
    public Object getItem(int position) {
        return holidays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return holidays.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_holiday, null);

            viewHolder = new ViewHolder();
            viewHolder.holidayName = (TextView) convertView.findViewById(R.id.itemHoliday_tv_holidayName);
            viewHolder.regions = (TextView) convertView.findViewById(R.id.itemHoliday_tv_region);
            viewHolder.regionCount = (TextView) convertView.findViewById(R.id.itemHoliday_tv_regionCount);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Holiday holiday = holidays.get(position);
        viewHolder.holidayName.setText(holiday.getName());
        if (holiday.getPeriods().size() > 1)
            viewHolder.regions.setText(context.getString(R.string.region_multi));
        else
            viewHolder.regions.setText(context.getString(R.string.region_single));
        viewHolder.regionCount.setText(String.valueOf(holiday.getPeriods().size()));

        return convertView;
    }

    private static class ViewHolder {
        public TextView holidayName;
        public TextView regions;
        public TextView regionCount;
    }
}
