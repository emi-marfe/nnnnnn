package com.afrosurv.ICGC_Hamburg;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

import static com.afrosurv.ICGC_Hamburg.PictureContent.deleteSavedImages;
import static com.afrosurv.ICGC_Hamburg.PictureContent.downloadRandomImage;
import static com.afrosurv.ICGC_Hamburg.PictureContent.loadSavedImages;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    // Declare Variables
    Button contact, facebook, instagram, icgc, chat, study;

    private MainActivity context;
    private DownloadManager downloadManager;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private BroadcastReceiver onComplete;
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        if (recyclerViewAdapter == null) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
            recyclerView = (RecyclerView) currentFragment.getView();
            recyclerViewAdapter = ((RecyclerView) currentFragment.getView()).getAdapter();
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);
        }

        progressBar = findViewById(R.id.indeterminateBar);

       final FloatingActionButton famb = findViewById(R.id.fab);
        famb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);
                        famb.show();
                        downloadRandomImage(downloadManager, context);
                    }
                });
            }
        });


        onComplete = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String filePath = "";
                DownloadManager.Query q = new DownloadManager.Query();
                q.setFilterById(intent.getExtras().getLong(DownloadManager.EXTRA_DOWNLOAD_ID));
                Cursor c = downloadManager.query(q);

                if (c.moveToFirst()) {
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        String downloadFileLocalUri = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                        filePath = Uri.parse(downloadFileLocalUri).getPath();
                    }
                }
                c.close();
                PictureContent.loadImage(new File(filePath));
                recyclerViewAdapter.notifyItemInserted(0);
                progressBar.setVisibility(View.GONE);
                famb.show();

            }
        };
        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        //Locate buttons in content_main
        icgc = findViewById(R.id.button4);
        contact = findViewById(R.id.button5);
        instagram = findViewById(R.id.button6);
        chat = findViewById(R.id.button8);
        facebook = findViewById(R.id.button7);
        study = findViewById(R.id.button12);

        //Capture Instagram Button Click
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/icgc.hamburg/?igshid=1tzr5qek36kly");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/icgc.hamburg/?igshid=1tzr5qek36kly")));
                }

            }
        });
        //Capture icgc Button click
        icgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open icgc webView
                Intent intent = new Intent(MainActivity.this, Web.class);
                startActivity(intent);
            }
        });
        //Capture contact Button Click
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open contact view
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        //Capture Study Button Click
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open contact view
                Intent intent = new Intent(MainActivity.this, StudyGermany.class);
                startActivity(intent);
            }
        });
        //Capture Facebook Button Click
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Facebook Page
                Intent intent = new Intent(MainActivity.this, FacebookPage.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            deleteSavedImages(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
            recyclerViewAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(onComplete);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadSavedImages(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(PictureItem item) {
        // This is where you'd handle clicking an item in the list
    }

}


