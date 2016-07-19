package com.buchuanqiuku.demo.news.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.buchuanqiuku.demo.news.R;
import com.buchuanqiuku.demo.news.adapter.Content_FragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int pageId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news, container, false);
        return initView(inflater);
    }

    public View initView(LayoutInflater inflater){
        View view=inflater.inflate(R.layout.fragment_news,null,false);
        final ViewPager viewPager=(ViewPager)view.findViewById(R.id.viewpager_news);
        viewPager.setAdapter(new Content_FragmentAdapter(getActivity().getSupportFragmentManager()));
        final RadioGroup radioGroup=(RadioGroup)view.findViewById(R.id.xinwen_rg);
        radioGroup.check(R.id.rb_xinwen_1);
        final HorizontalScrollView horizontalScrollView=(HorizontalScrollView)view.findViewById(R.id.horizon_sv);
        final TextView scroolTextView=(TextView)view.findViewById(R.id.top_tv);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_xinwen_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_xinwen_2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_xinwen_3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_xinwen_4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_xinwen_5:
                        viewPager.setCurrentItem(4);
                        break;
                    case R.id.rb_xinwen_6:
                        viewPager.setCurrentItem(5);
                        break;
                    case R.id.rb_xinwen_7:
                        viewPager.setCurrentItem(6);
                        break;
                    case R.id.rb_xinwen_8:
                        viewPager.setCurrentItem(7);
                        break;
                    case R.id.rb_xinwen_9:
                        viewPager.setCurrentItem(8);
                        break;
                    case R.id.rb_xinwen_10:
                        viewPager.setCurrentItem(9);
                        break;
                    case R.id.rb_xinwen_11:
                        viewPager.setCurrentItem(10);
                        break;
                    case R.id.rb_xinwen_12:
                        viewPager.setCurrentItem(11);
                        break;
                    case R.id.rb_xinwen_13:
                        viewPager.setCurrentItem(12);
                        break;
                    case R.id.rb_xinwen_14:
                        viewPager.setCurrentItem(13);
                        break;
                    case R.id.rb_xinwen_15:
                        viewPager.setCurrentItem(14);
                        break;

                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) scroolTextView
                        .getLayoutParams();
                params.leftMargin = (int) ((position + positionOffset) * params.width);
                scroolTextView.setLayoutParams(params);


            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());

                DisplayMetrics outMetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

                int radioBtnWidth=radioGroup.getChildAt(position).getWidth();
                int distance = (int) ((position + 0.5) * radioBtnWidth - outMetrics.widthPixels / 2);


                horizontalScrollView.scrollTo(distance, 0);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
