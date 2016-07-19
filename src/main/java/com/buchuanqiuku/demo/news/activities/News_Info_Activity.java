package com.buchuanqiuku.demo.news.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buchuanqiuku.demo.news.R;

public class News_Info_Activity extends AppCompatActivity {

    String url;

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url=this.getIntent().getStringExtra("Url");

        setContentView(R.layout.activity_news__info_);
        webView=(WebView)findViewById(R.id.newsweb);
        webView.getSettings().setDefaultTextEncodingName("UTF-8") ;
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }
}
