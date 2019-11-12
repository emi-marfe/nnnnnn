package com.afrosurv.ICGC_Hamburg;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FacebookPage extends AppCompatActivity {

    // Declare Variables
    Button contact, instagram, icgc, chat, mainPage, study;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_page);

        //Locate buttons and webView in activity_facebook_page xml
        icgc = findViewById(R.id.button4);
        contact = findViewById(R.id.button5);
        instagram = findViewById(R.id.button6);
        chat = findViewById(R.id.button8);
        mainPage = findViewById(R.id.button10);
        webView = findViewById(R.id.activity_webFacebook);
        study = findViewById(R.id.button12);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
        // Enable Javascript to run in WebView
        webView.getSettings().setJavaScriptEnabled(true);
        // Allow Zoom in/out controls
        webView.getSettings().setBuiltInZoomControls(true);
        // Zoom out the best fit screen
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        //Capture Study Button click
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacebookPage.this, StudyGermany.class);
                startActivity(intent);
            }
        });
        //Capture Contact Button click
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacebookPage.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        // Capture Study Button Click
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacebookPage.this, StudyGermany.class);
                startActivity(intent);
            }
        });
        // Capture MainPage Button Click
        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacebookPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        goToFacebookPage("172888763167206");

    }

    private void goToFacebookPage(String id) {
        try {
            webView.setWebViewClient(new WebViewClient());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + id));
            startActivity(intent);
        }
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
