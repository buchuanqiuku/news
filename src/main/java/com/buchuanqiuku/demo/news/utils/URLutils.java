package com.buchuanqiuku.demo.news.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by buchuanqiuku on 2016/6/23.
 */
public class URLutils {
    private static String redian = "http://c.3g.163.com/nc/article/list/T1429173762551/" ;//热点                                           //热点
    private static String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" ;//头条
    private static String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" ;//娱乐
    private static String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" ;//体育
    private static String caijing = "http://c.m.163.com/nc/article/list/T1348648756099/" ;//财经
    private static String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" ;//科技
    private static String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" ;//汽车
    private static String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" ;//时尚
    private static String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" ;//军事
    private static String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" ;//历史
    private static String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" ;//彩票
    private static String youxi = "http://c.m.163.com/nc/article/list/T1348654151579/" ;//游戏
    private static String yingshi = "http://c.m.163.com/nc/article/list/T1348648650048/" ;//影视
    private static String luntan = "http://c.m.163.com/nc/article/list/T1419386592923/" ;//论坛
    private static String shehui = "http://c.m.163.com/nc/article/list/T1348648037603/" ; //社会




    private static String lunbopic="http://c.m.163.com/photo/api/set/";//轮播


    //[{"tname":"推荐","tid":"T1457068979049"},{"tname":"搞笑","tid":"T1457069041911"},{"tname":"新闻现场","tid":"T1457069205071"},
    // {"tname":"八卦","tid":"T1457069261743"},{"tname":"猎奇","tid":"T1457069319264"},{"tname":"萌物","tid":"T1457069232830"},
    // {"tname":"美女帅哥","tid":"T1457069080899"},{"tname":"体育","tid":"T1457069346235"},{"tname":"黑科技","tid":"T1457069387259"},
    // {"tname":"涨姿势","tid":"T1457069475980"},{"tname":"音乐","tid":"T1464751736259"},{"tname":"二次元","tid":"T1457069446903"},
    // {"tname":"军武","tid":"T1457069421892"},{"tname":"全景","tid":"T1461563165622"}]
    private static String shipin ="http://c.3g.163.com/recommend/getChanListNews?channel=T1457068979049&size=10";//视频
    private static String yuedu ="http://c.3g.163.com/recommend/getSubDocPic?from=yuedu&size=20"; //阅读
    private static String shiting ="http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-10.html";//视听


    public  String getVideoUrl(int startpage){
        String url=null;
        url="http://c.m.163.com/nc/video/list/V9LG4B3A0/y/"+startpage+"-10.html";
        return url;
    }
    public static String getLunbojson(String abc){

        return lunbopic+StringUtils.getLunboFormat(abc)+".json";
    }
    public static String getUrl(int pageId, int startPage) {
        String url = null;
        switch (pageId) {
            case CurrentPageId.XINWEN_0:
                url = redian+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_1:
                url = toutiao+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINEWN_2:
                url =  yule+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_3:
                url =   tiyu+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_4:
                url =   caijing+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_5:
                url =   keji+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_6:
                url =   qiche+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_7:
                url =   shishang+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_8:
                url =   junshi+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_9:
                url =   lishi+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_10:
                url =   caipiao+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_11:
                url =   youxi+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_12:
                url =   yingshi+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_13:
                url =   luntan+startPage+"-"+20+".html";
                break;
            case CurrentPageId.XINWEN_14:
                url =   shehui+startPage+"-"+20+".html";
                break;


        }

        return url;
    }
}
