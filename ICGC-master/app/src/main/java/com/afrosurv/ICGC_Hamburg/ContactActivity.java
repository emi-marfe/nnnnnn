package com.afrosurv.ICGC_Hamburg;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    //Declare variable
    Button whatsapp, call, clickMe, icgc, mainPage, instagram, chat, facebook, study;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Locate the buttons in activity_contact.xml
        icgc = findViewById(R.id.button4);
        mainPage = findViewById(R.id.button10);
        instagram = findViewById(R.id.button6);
        chat = findViewById(R.id.button8);
        facebook = findViewById(R.id.button7);
        whatsapp = findViewById(R.id.button);
        call = findViewById(R.id.button3);
        clickMe = findViewById(R.id.button2);
        study = findViewById(R.id.button12);


        //Capture Instagram Button Click
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/-U/xxx");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/xxx")));
                }
            }
        });

        //Capture send Whatsapp Message Button click
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String text = "Send your Message to the Pastor";
                    String toNumber = "4917623684143";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Capture Click Me webView Button click
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, Web.class);
                startActivity(intent);
            }
        });

        //Capture Call Us Button click
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +4917624719930"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
        //Capture ICGC INT. Button click
        icgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, Web.class);
                startActivity(intent);

            }
        });
        //Capture mainPage Button click
        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        //Capture Facebook Button click
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, FacebookPage.class);
                startActivity(intent);

            }
        });
        //Capture Study Button click
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, StudyGermany.class);
                startActivity(intent);

            }
        });


    }
}
