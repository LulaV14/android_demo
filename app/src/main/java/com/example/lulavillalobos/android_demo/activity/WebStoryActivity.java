package com.example.lulavillalobos.android_demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lulavillalobos.android_demo.R;

public class WebStoryActivity extends AppCompatActivity {

    private static final String EXTRA_STORY_URL = "EXTRA_STORY_URL";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_story);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Bundle extras = getIntent().getExtras();
        String story_url = "";
        if(extras != null)
            story_url = extras.getString(EXTRA_STORY_URL);

        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webview, String url, Bitmap favicon) {
                super.onPageStarted(webview, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(webView, request, error);
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setHorizontalScrollBarEnabled(false);

        if(story_url != null) {
            webView.loadUrl(story_url);
        } else {
            Toast.makeText(this, "No URL found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                NavUtils.navigateUpTo(this, intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
