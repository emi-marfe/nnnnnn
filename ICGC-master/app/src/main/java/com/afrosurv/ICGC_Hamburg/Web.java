package com.afrosurv.ICGC_Hamburg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Web extends AppCompatActivity {

    // Declare Variables
    Button contact, facebook, instagram, study, icgc, chat, mainPage;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //Locate buttons in content_main
        contact = findViewById(R.id.button5);
        instagram = findViewById(R.id.button6);
        chat = findViewById(R.id.button8);
        facebook = findViewById(R.id.button7);
        mainPage = findViewById(R.id.button10);
        study = findViewById(R.id.button12);

        // Locate the WebView in activity_webView.xml
        webView = findViewById(R.id.activity_web);
        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
        // Enable Javascript to run in WebView
        webView.getSettings().setJavaScriptEnabled(true);
        // Allow Zoom in/out controls
        webView.getSettings().setBuiltInZoomControls(true);
        // Zoom out the best fit screen
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        // Load URL
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.centralgospel.com/");
        // Load Resource
        // webView.loadUrl("file://android_asset/index.html");

        //Capture Study Button click
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Web.this, StudyGermany.class);
                startActivity(intent);
            }
        });
        //Capture Contact Button click
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Web.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        // Capture FacebookPage Button Click
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Web.this, FacebookPage.class);
                startActivity(intent);
            }
        });
        // Capture MainPage Button Click
        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Web.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
