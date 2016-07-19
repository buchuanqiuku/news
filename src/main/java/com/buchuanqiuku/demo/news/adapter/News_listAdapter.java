package com.buchuanqiuku.demo.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.buchuanqiuku.demo.news.Bean.NewsMsg;
import com.buchuanqiuku.demo.news.R;
import com.buchuanqiuku.demo.news.fragment.ContentPageFragment;
import com.buchuanqiuku.demo.news.utils.CacheUtils;
import com.buchuanqiuku.demo.news.utils.DataControl;
import com.buchuanqiuku.demo.news.utils.StringUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeidong on 2016/6/18.
 */
public class News_listAdapter extends BaseAdapter {

    private List<NewsMsg> listNewsMsg;
    private Context context;
    private DataControl dataControl;
    private Drawable drawable;
    List<View> putonglist;
    List<View> santulist;

    final int SANTU_TYPE=0;
    final int PUTONG_TYPE=1;
    Lunbo_PagerAdapter luoboadapter;
    public News_listAdapter(Context context, List<NewsMsg> list,Lunbo_PagerAdapter adapter) {
        this.listNewsMsg = list;
        this.context = context;
        dataControl=new DataControl(context);
        drawable=context.getResources().getDrawable(R.drawable.wangyi);
        putonglist=new ArrayList<>();
        santulist=new ArrayList<>();
        this.luoboadapter=adapter;
    }

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
        luoboadapter.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listNewsMsg.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listNewsMsg.get(position);
    }
    @Override
    public int getItemViewType(int position){
        if(listNewsMsg.get(position).getImgextra()!=null){
            return SANTU_TYPE;
        }else {
            return PUTONG_TYPE;
        }

    }
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderputong viewHolder1=null;
        ViewHoldersantu viewHolder2=null;


        int type=getItemViewType(position);
       if(convertView==null){
           switch (type){
               case SANTU_TYPE:
                   convertView=LayoutInflater.from(context).inflate(R.layout.santuitem,null);
                   viewHolder2=new ViewHoldersantu();
                   viewHolder2.yulantu=(ImageView) convertView.findViewById(R.id.santu1);
                   viewHolder2.yulantu2=(ImageView)convertView.findViewById(R.id.santu2);
                   viewHolder2.yulantu3=(ImageView)convertView.findViewById(R.id.santu3);
                   viewHolder2.comment=(TextView) convertView.findViewById(R.id.huifu);
                   viewHolder2.source=(TextView)convertView.findViewById(R.id.laiyuan);
                   viewHolder2.title=(TextView) convertView.findViewById(R.id.santutxt);
                   convertView.setTag(viewHolder2);
                   break;
               case PUTONG_TYPE:
                   convertView=LayoutInflater.from(context).inflate(R.layout.item_list,null);
                   viewHolder1=new ViewHolderputong();
                   viewHolder1.yulantu=(ImageView) convertView.findViewById(R.id.previouspic);
                   viewHolder1.comment=(TextView) convertView.findViewById(R.id.comment_no);
                   viewHolder1.source=(TextView)convertView.findViewById(R.id.from);
                   viewHolder1.title=(TextView)convertView.findViewById(R.id.titletext);
                   convertView.setTag(viewHolder1);
                    break;
           }
       }else {
           switch (type){
               case SANTU_TYPE:
                   viewHolder2=(ViewHoldersantu) convertView.getTag();
                   break;
               case PUTONG_TYPE:
                   viewHolder1=(ViewHolderputong)convertView.getTag();
                   break;
           }
       }

        switch (type){
            case SANTU_TYPE:
                viewHolder2.title.setText(listNewsMsg.get(position).getTitle());
                viewHolder2.source.setText(listNewsMsg.get(position).getSource());
                viewHolder2.comment.setText(StringUtils.getNumberFormat(listNewsMsg.get(position).getReplyCount())+"回复");


                viewHolder2.yulantu.setTag(listNewsMsg.get(position).getImgsrc());
                viewHolder2.yulantu2.setTag(listNewsMsg.get(position).getImgextra().get(0).getImgsrc());
                viewHolder2.yulantu3.setTag(listNewsMsg.get(position).getImgextra().get(1).getImgsrc());

                viewHolder2.yulantu.setScaleType(ImageView.ScaleType.CENTER);
                viewHolder2.yulantu2.setScaleType(ImageView.ScaleType.CENTER);
                viewHolder2.yulantu3.setScaleType(ImageView.ScaleType.CENTER);


                viewHolder2.yulantu.setImageDrawable(drawable);
                viewHolder2.yulantu2.setImageDrawable(drawable);
                viewHolder2.yulantu3.setImageDrawable(drawable);

                dataControl.getPic(listNewsMsg.get(position).getImgsrc(),viewHolder2.yulantu);
                dataControl.getPic(listNewsMsg.get(position).getImgextra().get(0).getImgsrc(),viewHolder2.yulantu2);
                dataControl.getPic(listNewsMsg.get(position).getImgextra().get(1).getImgsrc(),viewHolder2.yulantu3);

                break;
            case PUTONG_TYPE:

                viewHolder1.title.setText(listNewsMsg.get(position).getTitle());
                viewHolder1.source.setText(listNewsMsg.get(position).getSource());
                viewHolder1.comment.setText(StringUtils.getNumberFormat(listNewsMsg.get(position).getReplyCount())+"回复");
                viewHolder1.yulantu.setTag(listNewsMsg.get(position).getImgsrc());

                viewHolder1.yulantu.setScaleType(ImageView.ScaleType.CENTER);

                viewHolder1.yulantu.setImageDrawable(drawable);
                dataControl.getPic(listNewsMsg.get(position).getImgsrc(),viewHolder1.yulantu);
                break;
        }

















//        //viewHolder1是正常item viewHolder2是三图
//        ViewHolderputong viewHolder1 = null;
//        ViewHoldersantu viewHolder2 = null;
//        View viewputong=null;
//        View viewsantu=null;
//
//
//
//        if (convertView == null) {
//            viewputong=LayoutInflater.from(context).inflate(R.layout.item_list,null);
//            viewsantu=LayoutInflater.from(context).inflate(R.layout.santuitem,null);
//            if(listNewsMsg.get(position).getImgextra()!=null){
//                convertView=viewsantu;
//
//            }else{
//                convertView = viewputong;
//
//            }
//            viewHolder2=new ViewHoldersantu();
//            viewHolder2.yulantu=(ImageView) viewsantu.findViewById(R.id.santu1);
//            viewHolder2.yulantu2=(ImageView)viewsantu.findViewById(R.id.santu2);
//            viewHolder2.yulantu3=(ImageView)viewsantu.findViewById(R.id.santu3);
//            viewHolder2.title=(TextView) viewsantu.findViewById(R.id.santutxt);
//            viewHolder2.source=(TextView)viewsantu.findViewById(R.id.laiyuan);
//            viewHolder2.comment=(TextView)viewsantu.findViewById(R.id.huifu);
//            viewsantu.setTag(viewHolder2);
//
//            viewHolder1 = new ViewHolderputong();
//            viewHolder1.comment = (TextView) viewputong.findViewById(R.id.comment_no);
//            viewHolder1.title = (TextView) viewputong.findViewById(R.id.titletext);
//            viewHolder1.yulantu = (ImageView) viewputong.findViewById(R.id.previouspic);
//            viewHolder1.source=(TextView)viewputong.findViewById(R.id.from);
//            viewputong.setTag(viewHolder1);
//
//
//            putonglist.add(viewputong);
//            santulist.add(viewsantu);
//
//        } else {
//            if(listNewsMsg.get(position).getImgextra()!=null){
//                Log.i("------------convertview","santu"+santulist.size()+"puto"+putonglist.size());
//                convertView=santulist.get(position%santulist.size());
//                viewHolder2=(ViewHoldersantu)santulist.get(position%santulist.size()).getTag();
//                viewHolder2.yulantu.setScaleType(ImageView.ScaleType.CENTER);
//                viewHolder2.yulantu2.setScaleType(ImageView.ScaleType.CENTER);
//                viewHolder2.yulantu3.setScaleType(ImageView.ScaleType.CENTER);
//                viewHolder2.yulantu.setImageDrawable(drawable);
//                viewHolder2.yulantu2.setImageDrawable(drawable);
//                viewHolder2.yulantu3.setImageDrawable(drawable);
//            }else {
//                Log.i("------------convertview","santu"+santulist.size()+"puto"+putonglist.size());
//                convertView=putonglist.get(position%putonglist.size());
//                viewHolder1=(ViewHolderputong)putonglist.get(position%putonglist.size()).getTag();
//                viewHolder1.yulantu.setScaleType(ImageView.ScaleType.CENTER);
//                viewHolder1.yulantu.setImageDrawable(drawable);
//
//            }
//        }
//        //向viewholder中填充数据
//
//        if(listNewsMsg.get(position).getImgextra()!=null){
//            viewHolder2.title.setText(listNewsMsg.get(position).getTitle());
//            viewHolder2.source.setText(listNewsMsg.get(position).getSource());
//            viewHolder2.comment.setText(StringUtils.getNumberFormat(listNewsMsg.get(position).getReplyCount())+"回复");
//
//            viewHolder2.yulantu.setTag(listNewsMsg.get(position).getImgsrc());
//            viewHolder2.yulantu2.setTag(listNewsMsg.get(position).getImgextra().get(0).getImgsrc());
//            viewHolder2.yulantu3.setTag(listNewsMsg.get(position).getImgextra().get(1).getImgsrc());
//
//            dataControl.getPic(listNewsMsg.get(position).getImgsrc(),viewHolder2.yulantu);
//            dataControl.getPic(listNewsMsg.get(position).getImgextra().get(0).getImgsrc(),viewHolder2.yulantu2);
//            dataControl.getPic(listNewsMsg.get(position).getImgextra().get(1).getImgsrc(),viewHolder2.yulantu3);
//        }else{
//                viewHolder1.title.setText(listNewsMsg.get(position).getTitle());
//                viewHolder1.comment.setText(StringUtils.getNumberFormat(listNewsMsg.get(position).getReplyCount())+"回复");
//                viewHolder1.source.setText(listNewsMsg.get(position).getSource());
//                viewHolder1.yulantu.setTag(listNewsMsg.get(position).getImgsrc());
//
//                dataControl.getPic(listNewsMsg.get(position).getImgsrc(),viewHolder1.yulantu);
//
//        }
//
//
//
//

        return convertView;

    }

    //管理convertView子视图组件
    public class ViewHolderputong {
        public TextView title;
        public TextView comment;
        public TextView source;
        public ImageView yulantu;

    }
    public class ViewHoldersantu{
        public TextView title;
        public TextView comment;
        public TextView source;
        public ImageView yulantu;
        public ImageView yulantu2;
        public ImageView yulantu3;
    }




}
