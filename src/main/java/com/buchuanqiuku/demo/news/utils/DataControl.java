package com.buchuanqiuku.demo.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.buchuanqiuku.demo.news.Bean.NewsMsg;
import com.buchuanqiuku.demo.news.Bean.VideoMsg;
import com.buchuanqiuku.demo.news.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeidong on 2016/6/26.
 */
public class DataControl {
    RequestQueue queue;
    Context context;
    Drawable drawable;
    SharedPreferences shared;
    private static DataControl instance;

    public DataControl(Context context) {
        //全局只需使用一个volley的requestqueue 方便有效管理
        queue = Volley.newRequestQueue(context,20971520);
        this.context = context;
        shared = context.getSharedPreferences("NewsMsg", Context.MODE_PRIVATE);
        cache = new CacheUtils();
        drawable=context.getResources().getDrawable(R.drawable.wangyi);
    }
    public DataControl getInstance(Context context){
        if(instance==null){
            instance=new DataControl(context);
        }
        return instance;
    }




    //获取新闻信息集合
    public void getMsgList(final String url, final int pageId, final List<NewsMsg> list, final PullToRefreshBase plv, final BaseAdapter adapter, final PagerAdapter pagerAdapter) {

        JsonRequest json = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (url.equals(URLutils.getUrl(pageId, 0))) {
                    list.clear();
                }
                list.addAll((List<NewsMsg>) JsonUtils.defMethod(response.toString(), pageId));
                adapter.notifyDataSetChanged();
                pagerAdapter.notifyDataSetChanged();
                plv.getRefreshableView().setVisibility(View.VISIBLE);
                plv.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "加载失败 请重试", Toast.LENGTH_LONG).show();

                if (list.size() == 0) {

                }
                if(getStringFromSD("0:"+url)!=null){
                    if(url.equals(URLutils.getUrl(pageId, 0))){
                        list.clear();
                    }
                    list.addAll((List<NewsMsg>) JsonUtils.defMethod(getStringFromSD("0:"+url), pageId));
                    adapter.notifyDataSetChanged();
                    pagerAdapter.notifyDataSetChanged();
                    plv.getRefreshableView().setVisibility(View.VISIBLE);
                }

                plv.onRefreshComplete();

            }
        });
        queue.add(json);
    }


    public List<VideoMsg> getVideoList(){
        List list=new ArrayList();


        return list;
    }

    private CacheUtils cache;

    public void getPic(final String url, final ImageView imageView) {

        final ImageLoader imageLoader = new ImageLoader(queue, cache);
        ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
            //这里的onresponce方法没网络也会调用 同时调用onerrorresponce
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {

                if(imageContainer.getBitmap()!=null){
                    if(imageView.getTag().equals(url)){
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setImageBitmap(imageContainer.getBitmap());
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(imageView.getTag().equals(url)){
                    getPicFromSD("0:"+url,imageView);
                }
            }
        };
        imageLoader.get(url, listener);
    }

    public String getStringFromSD(String url){
        String str=null;
        if(queue.getCache().get(url)!=null){
            str=new String(queue.getCache().get(url).data);
        }
        return str;
    }

    public int getPicFromSD(String url,ImageView im){
        int i=0;
        if(queue.getCache().get(url)!=null){
            Bitmap bitmap=null;
            bitmap= BitmapFactory.decodeByteArray(queue.getCache().get(url).data,0,queue.getCache().get(url).data.length);
            im.setScaleType(ImageView.ScaleType.CENTER_CROP);
            im.setImageBitmap(bitmap);
            i=1;
        }else{
            im.setImageDrawable(drawable);
            i=2;
        }
        return i;
    }



}
