package com.buchuanqiuku.demo.news.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.buchuanqiuku.demo.news.fragment.ContentPageFragment;
import com.buchuanqiuku.demo.news.utils.CurrentPageId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeidong on 2016/6/18.
 */
public class Content_FragmentAdapter extends FragmentPagerAdapter {
    private List<ContentPageFragment> list;
    private ContentPageFragment fragment;
    public Content_FragmentAdapter(FragmentManager fm) {
        super(fm);
        list=new ArrayList<>();
        for(int i=0;i<CurrentPageId.XINWEN_SIZE+1;i++){
            fragment=ContentPageFragment.newInstance((int)CurrentPageId.getXinwenList().get(i));   //传入所属页面ID 为初始化页面做准备
            list.add(fragment);
        }

    }



    @Override
    public int getCount(){

        return 15;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position){

        return list.get(position);
    }



}
