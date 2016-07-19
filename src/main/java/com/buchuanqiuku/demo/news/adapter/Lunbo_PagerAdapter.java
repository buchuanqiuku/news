package com.buchuanqiuku.demo.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.buchuanqiuku.demo.news.Bean.NewsMsg;
import com.buchuanqiuku.demo.news.R;
import com.buchuanqiuku.demo.news.activities.LunboViewActivity;
import com.buchuanqiuku.demo.news.activities.News_Info_Activity;
import com.buchuanqiuku.demo.news.fragment.ContentPageFragment;
import com.buchuanqiuku.demo.news.utils.DataControl;
import com.buchuanqiuku.demo.news.utils.URLutils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeidong on 2016/6/18.
 */
public class Lunbo_PagerAdapter extends PagerAdapter {
    private Context context;
    private List<NewsMsg> msgList;
    private DataControl dataControl;
    private TextView lunbotxt;
    private Drawable drawable;
    private LinearLayout pointLayout;
    private List<ImageView> imageViewList;

    public Lunbo_PagerAdapter(Context context, List<NewsMsg> list, TextView lunbotxt, LinearLayout pointLayout) {
        this.context = context;
        this.msgList = list;
        this.dataControl = new DataControl(context);
        this.lunbotxt = lunbotxt;
        this.pointLayout = pointLayout;
        this.imageViewList = new ArrayList<>();
        this.drawable=context.getResources().getDrawable(R.drawable.wangyi);
    }

    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
        imageViewList.clear();
        //只有newsmsg不为空时 才有轮播


        if (msgList.size() != 0) {

            if (msgList.get(0).getAds() == null) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageResource(R.drawable.wangyi);
                imageViewList.add(imageView);
                lunbotxt.setText(msgList.get(0).getTitle());

            } else {
                pointLayout.removeAllViews();
                //adsentity大于1时才添加指示点
                if(msgList.get(0).getAds().size()==1){
                    ImageView imageView=new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    imageView.setImageResource(R.drawable.wangyi);
                    imageViewList.add(imageView);
                    lunbotxt.setText(msgList.get(0).getAds().get(0).getTitle());

                }else{
                    for (int i = 0; i < msgList.get(0).getAds().size(); i++) {
                        ImageView imageView = new ImageView(context);
                        imageView.setScaleType(ImageView.ScaleType.CENTER);
                        imageView.setImageResource(R.drawable.wangyi);
                        imageViewList.add(imageView);
                        lunbotxt.setText(msgList.get(0).getAds().get(0).getTitle());

                        View view = new View(context);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
                        params.leftMargin = 10;
                        view.setBackgroundResource(R.drawable.lunbo_point);
                        view.setLayoutParams(params);
                        view.setEnabled(false);
                        pointLayout.addView(view);
                    }
                    pointLayout.getChildAt(0).setEnabled(true);

                }

            }
        }

        Log.i("-------------aaaaaaaa","notigy");

    }


    @Override
    public int getCount() {


        return imageViewList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {


        return view==object;
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

         container.addView(imageViewList.get(position));
        if (msgList.size() > 0) {
            if (msgList.get(0).getAds() != null) {
                if (imageViewList.size() > 1) {
                    imageViewList.get(position).setTag(msgList.get(0).getAds().get(position).getImgsrc());
                    dataControl.getPic(msgList.get(0).getAds().get(position).getImgsrc(), imageViewList.get(position));
                    imageViewList.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(context, LunboViewActivity.class);
                            intent.putExtra("lunbourl", URLutils.getLunbojson(msgList.get(0).getAds().get(position).getUrl()));
                            Log.i("-------------json",URLutils.getLunbojson(msgList.get(0).getAds().get(position).getUrl()));
                        }
                    });

                } else if (imageViewList.size() == 1) {
                    imageViewList.get(position).setTag(msgList.get(0).getAds().get(0).getImgsrc());
                    dataControl.getPic(msgList.get(0).getAds().get(0).getImgsrc(), imageViewList.get(position));
                    imageViewList.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(context, LunboViewActivity.class);
                            intent.putExtra("lunbourl", URLutils.getLunbojson(msgList.get(0).getAds().get(position).getUrl()));
                            context.startActivity(intent);
                        }
                    });
                }
            } else {
                imageViewList.get(position).setTag(msgList.get(0).getImgsrc());
                dataControl.getPic(msgList.get(0).getImgsrc(), imageViewList.get(position));
                imageViewList.get(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(context,News_Info_Activity.class);
                        intent.putExtra("Url",msgList.get(0).getUrl_3w());
                        context.startActivity(intent);
                    }
                });

            }
        }

        return imageViewList.get(position);

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));
    }
    @Override
    public int getItemPosition(Object obj){
        return POSITION_NONE;
    }

}
