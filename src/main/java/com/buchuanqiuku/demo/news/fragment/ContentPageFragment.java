package com.buchuanqiuku.demo.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.buchuanqiuku.demo.news.Bean.NewsMsg;
import com.buchuanqiuku.demo.news.R;
import com.buchuanqiuku.demo.news.activities.News_Info_Activity;
import com.buchuanqiuku.demo.news.adapter.Lunbo_PagerAdapter;
import com.buchuanqiuku.demo.news.adapter.News_listAdapter;
import com.buchuanqiuku.demo.news.utils.DataControl;
import com.buchuanqiuku.demo.news.utils.URLutils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int pageId;
    private int startPage;
    PullToRefreshListView plv;
    private static DataControl dataControl;


    private OnFragmentInteractionListener mListener;

    public ContentPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentPageFragment newInstance(String param1, String param2) {
        ContentPageFragment fragment = new ContentPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ContentPageFragment newInstance(int pageId) {
        ContentPageFragment fragment = new ContentPageFragment();
        fragment.pageId = pageId;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dataControl = new DataControl(getActivity());
        newsMsgList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return initView(pageId, inflater);
    }

    public List<NewsMsg> newsMsgList;
    News_listAdapter news_listAdapter;
    ContentPageFragment.LunboView lunboView;
    Lunbo_PagerAdapter lunboAdpter;

    public View initView(final int pageId, LayoutInflater inflater) {
        lunboView=new LunboView(getContext());
        lunboAdpter =new Lunbo_PagerAdapter(getContext(),newsMsgList,lunboView.lunboTxt,lunboView.pointlayout);
        news_listAdapter = new News_listAdapter(getActivity(), newsMsgList,lunboAdpter);


        View view = inflater.inflate(R.layout.fragment_content_page, null, false);
        plv = (PullToRefreshListView) view.findViewById(R.id.pulltolv);
        plv.setMode(PullToRefreshBase.Mode.BOTH);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //获取数据 填充内容 结束刷新 从第0条刷新
                startPage = 0;
//                getShowView(URLutils.getUrl(pageId, startPage), pageId, newsMsgList, true, news_listAdapter);
                Log.i("下拉刷新", "-------------");
                dataControl.getMsgList(URLutils.getUrl(pageId, startPage), pageId, newsMsgList, plv, news_listAdapter,lunboAdpter);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //分页显示 网易新闻最多有400页应当限制最大页数 超出时Toast提示
                if (startPage <= 400 && newsMsgList.size() > 0) {
                    if (newsMsgList.size() % 20 > 1) {
                        startPage = newsMsgList.size() + 20 - newsMsgList.size() % 20;
                    } else {
                        startPage = newsMsgList.size();
                    }
                    Log.i("-----------", newsMsgList.size() + "---------" + startPage);
                    dataControl.getMsgList(URLutils.getUrl(pageId, startPage), pageId, newsMsgList, plv, news_listAdapter,lunboAdpter);

                } else {
                    plv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            plv.onRefreshComplete();
                            if (newsMsgList.size() > 0) {
                                Toast.makeText(getActivity(), "已无更多新闻", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "刷新失败 请检查网络", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 1000);


                }
                Log.i("上拉刷新", "--------");
            }
        });

        //添加轮播



        lunboView.setAdapter(lunboAdpter);
        View headView=lunboView.getLunboView();
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 520);
        headView.setLayoutParams(layoutParams);
        plv.getRefreshableView().addHeaderView(headView);
        plv.getRefreshableView().setAdapter(news_listAdapter);
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), News_Info_Activity.class);
                String url=((NewsMsg)parent.getAdapter().getItem(position)).getUrl_3w();
                intent.putExtra("Url",url);
                startActivity(intent);

            }
        });
        plv.postDelayed(new Runnable() {
            @Override
            public void run() {
                plv.setRefreshing(true);
            }
        }, 1000);
        plv.getRefreshableView().setVisibility(View.GONE);



        return view;
    }

    public class LunboView {
        ViewGroup lunboLayout;
        ViewPager lunboPager;
        TextView lunboTxt;
        LinearLayout pointlayout;
        int preIndex=0;

        public LunboView(Context context) {
            lunboLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.lunbo_layout, null);
            lunboPager = (ViewPager) lunboLayout.findViewById(R.id.lunbopager);
            lunboTxt = (TextView) lunboLayout.findViewById(R.id.lunbotxt);
            pointlayout = (LinearLayout) lunboLayout.findViewById(R.id.point);

        }

        //为内部Viewpager设置adpter
        public void setAdapter(PagerAdapter adapter) {
            lunboPager.setAdapter(adapter);
            lunboPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    int index=position;
                    Log.i("----",""+position+"onpage");
                    lunboTxt.setText(newsMsgList.get(0).getAds().get(position).getTitle());
                    pointlayout.getChildAt(preIndex).setEnabled(false);
                    pointlayout.getChildAt(index).setEnabled(true);
                    preIndex=position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }

        public View getLunboView() {

            return lunboLayout;
        }


    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);

}
}
