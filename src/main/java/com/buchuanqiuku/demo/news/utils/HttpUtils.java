package com.buchuanqiuku.demo.news.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lipeidong on 2016/6/28.
 */
public class HttpUtils {
    String url;

    public String httpGet(String urlpath) {
        String response = null;

        StringBuffer buffer = new StringBuffer();
        String ap;
        try {
            URL url = new URL(urlpath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);

            InputStream is = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((ap = bufferedReader.readLine()) != null) {
                buffer.append(ap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        response = buffer.toString();

        return response;
    }

    public Bitmap bitmapGet(String bitmapUrl) {
        Bitmap bitmap = null;
        try{
            URL url = new URL(bitmapUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            InputStream is = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);


        }catch (Exception e){
            Log.i("--------位图出错","..............");
        }
        return bitmap;
    }


}
