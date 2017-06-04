package com.nicknam.holidates;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by snick on 4-6-2017.
 */

public class holidayRetrievalTask extends AsyncTask<String, Void, String> {
    private HolidayRetrievalListener holidayRetrievalListener;

    public holidayRetrievalTask(HolidayRetrievalListener holidayRetrievalListener) {
        this.holidayRetrievalListener = holidayRetrievalListener;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        BufferedReader reader = null;
        String urlString = "";
        String response = "";

        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

            reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            response = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
        } catch (MalformedURLException e) {
            Log.e("MalformedURLException", e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("IOException", e.getLocalizedMessage());
            return null;
        } catch (Exception e) {
            Log.e("Exception", e.getLocalizedMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("IOException", e.getLocalizedMessage());
                    return null;
                }
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        try {
            JSONObject data = new JSONObject(response);
            JSONArray holidays = data
                    .getJSONArray("content")
                    .getJSONObject(0)
                    .getJSONArray("vacations");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", new Locale(data.getString("language")));
            for (int i = 0; i < holidays.length(); i++) {
                JSONObject holiday = holidays.getJSONObject(i);

                String name = holiday.getString("type");
                boolean compulsory = holiday.getBoolean("compulsorydates");

                List<Period> periods = new ArrayList<>();
                JSONArray regions = holiday.getJSONArray("regions");
                for (int j = 0; j < regions.length(); j++) {
                    JSONObject region = regions.getJSONObject(j);

                    String regionName = region.getString("region");
                    String startDateString = region.getString("startdate");
                    String endDateString = region.getString("enddate");
                    Date startDate = format.parse(startDateString.substring(0, startDateString.length()-1));
                    Date endDate = format.parse(endDateString.substring(0, endDateString.length()-1));

                    periods.add(new Period(regionName, startDate, endDate));
                }

                holidayRetrievalListener.onHolidayRetrieved(new Holiday(name, compulsory, periods));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getLocalizedMessage());
        } catch (ParseException e) {
            Log.e("ParseException", e.getLocalizedMessage());
        }
    }

    public interface HolidayRetrievalListener {
        void onHolidayRetrieved(Holiday holiday);
    }
}
