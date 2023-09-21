package com.example.bookmarkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class ActivityWeb extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String url = getIntent().getStringExtra("URL_KEY");
        //webView.loadUrl(url);

        if (url == null || url.trim().isEmpty()) {
            Toast.makeText(this, "URL is invalid", Toast.LENGTH_SHORT).show();
        } else {
            WebView webView = findViewById(R.id.web_view);
            //webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }

    }

}