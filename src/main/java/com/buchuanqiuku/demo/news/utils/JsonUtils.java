package com.buchuanqiuku.demo.news.utils;

import android.util.Log;

import com.buchuanqiuku.demo.news.Bean.NewsMsg;
import com.buchuanqiuku.demo.news.Bean.VideoMsg;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeidong on 2016/7/4.
 */
public class JsonUtils {
    public static List<?> defMethod(String json, int pageId) {
        List<NewsMsg> list = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(json);
            JSONArray array = null;
            switch (pageId) {
                case CurrentPageId.XINWEN_0:
                    array = object.getJSONArray("T1429173762551");
                    break;
                case CurrentPageId.XINWEN_1:
                    array = object.getJSONArray("T1348647853363");
                    break;
                case CurrentPageId.XINEWN_2:
                    array = object.getJSONArray("T1348648517839");
                    break;
                case CurrentPageId.XINWEN_3:
                    array = object.getJSONArray("T1348649079062");
                    break;
                case CurrentPageId.XINWEN_4:
                    array = object.getJSONArray("T1348648756099");
                    break;
                case CurrentPageId.XINWEN_5:
                    array = object.getJSONArray("T1348649580692");
                    break;
                case CurrentPageId.XINWEN_6:
                    array = object.getJSONArray("T1348654060988");
                    break;
                case CurrentPageId.XINWEN_7:
                    array = object.getJSONArray("T1348650593803");
                    break;
                case CurrentPageId.XINWEN_8:
                    array = object.getJSONArray("T1348648141035");
                    break;
                case CurrentPageId.XINWEN_9:
                    array = object.getJSONArray("T1368497029546");
                    break;
                case CurrentPageId.XINWEN_10:
                    array = object.getJSONArray("T1356600029035");
                    break;
                case CurrentPageId.XINWEN_11:
                    array = object.getJSONArray("T1348654151579");
                    break;
                case CurrentPageId.XINWEN_12:
                    array = object.getJSONArray("T1348648650048");
                    break;
                case CurrentPageId.XINWEN_13:
                    array = object.getJSONArray("T1419386592923");
                    break;
                case CurrentPageId.XINWEN_14:
                    array = object.getJSONArray("T1348648037603");
                    break;
            }
            for (int i = 0; i < array.length(); i++) {
                NewsMsg newsMsg = new NewsMsg();
                JSONObject arrayobj = array.getJSONObject(i);
                if (!arrayobj.isNull("postid")) {
                    newsMsg.setPostid(arrayobj.getString("postid"));
                }
                if (!arrayobj.isNull("hacCover")) {
                    newsMsg.setHacCover(arrayobj.getBoolean("hasCover"));
                }
                if (!arrayobj.isNull("hasHead")) {
                    newsMsg.setHasAD(arrayobj.getInt("hasHead"));

                }
                if (!arrayobj.isNull("skipID")) {
                    newsMsg.setSkipID(arrayobj.getString("skipID"));
                }
                if (!arrayobj.isNull("skipType")) {
                    newsMsg.setSkipType(arrayobj.getString("skipType"));
                }
                if (!arrayobj.isNull("replyCount")) {
                    newsMsg.setReplyCount(arrayobj.getInt("replyCount"));

                }
                if (!arrayobj.isNull("ltitle")) {
                    newsMsg.setLtitle(arrayobj.getString("ltitle"));
                }
                if (!arrayobj.isNull("hasImg")) {
                    newsMsg.setHasImg(arrayobj.getInt("hasImg"));

                }
                if (!arrayobj.isNull("digest")) {
                    newsMsg.setDigest(arrayobj.getString("digest"));
                }
                if (!arrayobj.isNull("hasIcon")) {
                    newsMsg.setHasIcon(arrayobj.getBoolean("hasIcon"));
                }
                if (!arrayobj.isNull("docid")) {
                    newsMsg.setDocid(arrayobj.getString("docid"));
                }
                if (!arrayobj.isNull("title")) {
                    newsMsg.setTitle(arrayobj.getString("title"));
                }
                if (!arrayobj.isNull("order")) {
                    newsMsg.setOrder(arrayobj.getInt("order"));
                }
                if (!arrayobj.isNull("priority")) {
                    newsMsg.setPriority(arrayobj.getInt("priority"));
                }
                if (!arrayobj.isNull("lmodify")) {
                    newsMsg.setLmodify(arrayobj.getString("lmodify"));
                }
                if (!arrayobj.isNull("boardid")) {
                    newsMsg.setBoardid(arrayobj.getString("boardid"));
                }
                if (!arrayobj.isNull("url_3w")) {
                    newsMsg.setUrl_3w(arrayobj.getString("url_3w"));
                }
                if (!arrayobj.isNull("template")) {
                    newsMsg.setTemplate(arrayobj.getString("template"));
                }
                if (!arrayobj.isNull("votecount")) {
                    newsMsg.setVotecount(arrayobj.getInt("votecount"));
                }
                if (!arrayobj.isNull("alias")) {
                    newsMsg.setAlias(arrayobj.getString("alias"));
                }
                if (!arrayobj.isNull("cid")) {
                    newsMsg.setCid(arrayobj.getString("cid"));
                }
                if (!arrayobj.isNull("url")) {
                    newsMsg.setUrl(arrayobj.getString("url"));
                }
                if (!arrayobj.isNull("hasAD")) {
                    newsMsg.setHasAD(arrayobj.getInt("hasAD"));
                }
                if (!arrayobj.isNull("source")) {
                    newsMsg.setSource(arrayobj.getString("source"));
                }
                if (!arrayobj.isNull("subtitle")) {
                    newsMsg.setSubtitle(arrayobj.getString("subtitle"));
                }
                if (!arrayobj.isNull("ename")) {
                    newsMsg.setSubtitle(arrayobj.getString("ename"));
                }
                if (!arrayobj.isNull("tname")) {
                    newsMsg.setTname(arrayobj.getString("tname"));
                }
                if (!arrayobj.isNull("imgsrc")) {
                    newsMsg.setImgsrc(arrayobj.getString("imgsrc"));
                }
                if (!arrayobj.isNull("ptime")) {
                    newsMsg.setPtime(arrayobj.getString("ptime"));
                }
                if (!arrayobj.isNull("imgextra")) {
                    JSONArray arrayextra = arrayobj.getJSONArray("imgextra");
                    List<NewsMsg.ImgextraEntity> listImgextra = new ArrayList<NewsMsg.ImgextraEntity>();
                    for (int j = 0; j < arrayextra.length(); j++) {
                        NewsMsg.ImgextraEntity imgextraEntity = new NewsMsg.ImgextraEntity();
                        imgextraEntity.setImgsrc(arrayextra.getJSONObject(j).getString("imgsrc"));
                        listImgextra.add(imgextraEntity);
                    }
                    newsMsg.setImgextra(listImgextra);

                }

                if (!arrayobj.isNull("ads")) {
                    JSONArray arrayads = arrayobj.getJSONArray("ads");
                    List<NewsMsg.AdsEntity> listAdsEntity = new ArrayList<NewsMsg.AdsEntity>();
                    for (int j = 0; j < arrayads.length(); j++) {
                        NewsMsg.AdsEntity adsEntity = new NewsMsg.AdsEntity();
                        adsEntity.setImgsrc(arrayads.getJSONObject(j).getString("imgsrc"));
                        adsEntity.setSubtitle(arrayads.getJSONObject(j).getString("subtitle"));
                        adsEntity.setTag(arrayads.getJSONObject(j).getString("tag"));
                        adsEntity.setUrl(arrayads.getJSONObject(j).getString("url"));
                        adsEntity.setTitle(arrayads.getJSONObject(j).getString("title"));
                        listAdsEntity.add(adsEntity);
                    }
                    newsMsg.setAds(listAdsEntity);
                }
                list.add(newsMsg);
            }

        } catch (Exception e) {
            Log.i("-------json", "解析出错");
        }


        return list;
    }

    public static List<?> deGson(String json) {
        List<NewsMsg> list = null;


        return list;

    }

    public static List<?> deVideo(String json){
        List<VideoMsg> list=null;
        Gson gson=new Gson();
        VideoMsg video=null;
        JSONObject object=null;
        JSONArray jsonArray=null;
        try {
            object=new JSONObject(json);
            jsonArray=object.getJSONArray("V9LG4B3A0");
        }catch (Exception e){

        }
        for(int i=0;i<jsonArray.length();i++){
            try {
                video=gson.fromJson(jsonArray.get(i).toString(),VideoMsg.class);
                list.add(video);

            }catch (Exception e){

            }

        }


        return list;
    }

    public static List<?> getReadingMsg(String json){
        List list=null;

        return list;
    }
}
