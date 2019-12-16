package com.tingbob.nestscrollview.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tingbob.nestscrollview.R;

/**
 * 参考地址：https://github.com/tingbob/nestscrollview/
 * @author ShaoWenWen
 * @date 2019-12-11
 */
public class ScrollWebviewActivity extends Activity {

    private MediaPlayerView mediaPlayerView;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView imageBack;
    private TextView textTitle;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_webview);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        // TODO: toolbar的包不要导错了 by ShaoWenWen 2019-12-12
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textTitle = (TextView) findViewById(R.id.text_title);
        imageBack = (ImageView) findViewById(R.id.image_back);
        webView = (WebView) findViewById(R.id.webView);
        mediaPlayerView = (MediaPlayerView) findViewById(R.id.meida);
    }

    private void initListener() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    textTitle.setAlpha(0);
                    mediaPlayerView.start();
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    textTitle.setAlpha(1);
                } else {
                    float alpha = (Math.abs(verticalOffset)) / (appBarLayout.getTotalScrollRange());
                    textTitle.setAlpha(alpha);
                }
            }
        });
    }

    private void initData() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.loadUrl("https://www.baidu.com");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayerView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerView.onStop();
    }

}
