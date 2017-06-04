package com.nicknam.holidates;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by snick on 4-6-2017.
 */

public class PeriodAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Period> periods;

    public PeriodAdapter(Context context, LayoutInflater layoutInflater, List<Period> periods) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.periods = periods;
    }

    @Override
    public int getCount() {
        return periods.size();
    }

    @Override
    public Object getItem(int position) {
        return periods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return periods.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_period, null);

            viewHolder = new ViewHolder();
            viewHolder.region = (TextView) convertView.findViewById(R.id.itemPeriod_tv_region);
            viewHolder.startDate = (TextView) convertView.findViewById(R.id.itemPeriod_tv_startDate);
            viewHolder.endDate = (TextView) convertView.findViewById(R.id.itemPeriod_tv_endDate);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Period period = periods.get(position);

        viewHolder.region.setText(period.getRegion());

        SimpleDateFormat dateFormat;
        if (Build.VERSION.SDK_INT < 24)
            dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        else
            dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault(Locale.Category.FORMAT));

        viewHolder.startDate.setText(dateFormat.format(period.getStartDate()));
        viewHolder.endDate.setText(dateFormat.format(period.getEndDate()));

        return convertView;
    }

    private static class ViewHolder {
        public TextView region;
        public TextView startDate;
        public TextView endDate;
    }
}
