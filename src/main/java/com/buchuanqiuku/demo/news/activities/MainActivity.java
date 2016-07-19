package com.buchuanqiuku.demo.news.activities;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.buchuanqiuku.demo.news.R;
import com.buchuanqiuku.demo.news.adapter.Content_FragmentAdapter;
import com.buchuanqiuku.demo.news.fragment.ContentPageFragment;
import com.buchuanqiuku.demo.news.fragment.NewsFragment;
import com.buchuanqiuku.demo.news.fragment.ReadingFragment;
import com.buchuanqiuku.demo.news.fragment.TopicFragment;
import com.buchuanqiuku.demo.news.fragment.VideoFragment;
import com.buchuanqiuku.demo.news.utils.DataControl;
import com.buchuanqiuku.demo.news.view.CustomViewPager;


import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NewsFragment.OnFragmentInteractionListener,ReadingFragment.OnFragmentInteractionListener
    ,VideoFragment.OnFragmentInteractionListener,TopicFragment.OnFragmentInteractionListener,ContentPageFragment.OnFragmentInteractionListener{

    private CustomViewPager mainPager;
    private FragmentManager fm;
    private Content_FragmentAdapter adapter;
    private RadioGroup radioGroup;
    private List<Fragment> list;
    private TextView netstate;
    private DataControl dataControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        netstate=(TextView)findViewById(R.id.netstatus);



        initView();
    }

    public void initView(){


        list=new ArrayList<>();
        list.add(new NewsFragment());
        list.add(new ReadingFragment());
        list.add(new VideoFragment());
        list.add(new TopicFragment());
        mainPager=(CustomViewPager)findViewById(R.id.main_vp);
        fm=getSupportFragmentManager();
        mainPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        radioGroup=(RadioGroup)findViewById(R.id.rg_bottomtab);
        radioGroup.check(R.id.rb_xinwen);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_xinwen:
                        mainPager.setCurrentItem(0);
                        break;
                    case R.id.rb_yuedu:
                        mainPager.setCurrentItem(1);
                        break;
                    case R.id.rb_shipin:
                        mainPager.setCurrentItem(2);
                        break;
                    case R.id.rb_huati:
                        mainPager.setCurrentItem(3);
                        break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri){



    }
}
