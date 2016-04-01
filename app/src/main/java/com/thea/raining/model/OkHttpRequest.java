package com.thea.raining.model;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public class OkHttpRequest {
    private static final String KEY = "4611090c7807474a893d7734034de1a2";
    private static final String BASE_URL = "https://api.heweather.com/x3";
    private static final String BASE_URL_WEATHER = BASE_URL + "/weather?key=" + KEY + "&";
    private static final String BASE_URL_CODE = BASE_URL + "/condition?key=" + KEY + "&";
    private static final String BASE_URL_CITYLIST = BASE_URL + "/citylist?key=" + KEY + "&";

    private OkHttpClient mClient;

    public OkHttpRequest() {
        mClient = new OkHttpClient();
    }

    /**
     * 通过cityId获取天气信息
     * @param cityId    城市id
     * @param callback  响应回调
     * @throws IOException
     */
    public void getWeather(String cityId, OnResponseCallback callback) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL_WEATHER + "cityid=" + cityId)
                .build();

        Response response = mClient.newCall(request).execute();
        callback(response, callback);
    }

    /**
     * 获取天气码
     * @param callback    响应回调
     * @throws IOException
     */
    public void getCode(OnResponseCallback callback) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL_WEATHER + "search=allcond")
                .build();

        Response response = mClient.newCall(request).execute();
        callback(response, callback);
    }

    /**
     * 获取支持的国内城市列表
     * @param callback    响应回调
     * @throws IOException
     */
    public void getCityList(OnResponseCallback callback) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL_WEATHER + "search=allchina")
                .build();

        Response response = mClient.newCall(request).execute();
        callback(response, callback);
    }

    public void callback(Response response, OnResponseCallback callback) {
        if (response.isSuccessful()) {
            callback.onSuccess(response.code(), response.headers(), response.body());
        } else
            callback.onFail(response.code(), response.message());
    }

    /**
     * 响应回调接口
     */
    public interface OnResponseCallback {
        void onSuccess(int code, Headers headers, ResponseBody responseBody);

        void onFail(int code, String message);
    }
}
