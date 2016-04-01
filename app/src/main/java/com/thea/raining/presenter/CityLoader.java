package com.thea.raining.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.ResponseBody;
import com.thea.raining.bean.City;
import com.thea.raining.model.CityModel;
import com.thea.raining.model.CitySQLiteOpenHelper;
import com.thea.raining.model.ICityModel;
import com.thea.raining.model.OkHttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public class CityLoader {
    private static final String TAG = CityLoader.class.getSimpleName();

    private OkHttpRequest mHttpRequest;
    private ICityModel mCityModel;

    private OkHttpRequest.OnResponseCallback mCallback = new OkHttpRequest.OnResponseCallback() {
        @Override
        public void onSuccess(int code, Headers headers, ResponseBody responseBody) {
            Log.i(TAG, "success: " + code);
            try {
                parseResponse(new JSONObject(responseBody.string()));
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFail(int code, String message) {
            Log.i(TAG, "fail: " + code + "  " + message);
        }
    };

    public CityLoader(Context context) {
        mHttpRequest = new OkHttpRequest();
        mCityModel = new CityModel(new CitySQLiteOpenHelper(context));
    }

    public void load() {
        try {
            mHttpRequest.getCityList(mCallback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseResponse(JSONObject response) {
        try {
            JSONArray cityArray = response.getJSONArray("city_info");
            Gson gson = new Gson();

            for (int i = 0; i < cityArray.length(); i++) {
                City city = gson.fromJson(cityArray.getString(i), City.class);
                mCityModel.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
