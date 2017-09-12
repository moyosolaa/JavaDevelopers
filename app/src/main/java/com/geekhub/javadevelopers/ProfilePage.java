package com.geekhub.javadevelopers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ProfilePage extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView user_image;
    TextView username, profileLink;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileLink = (TextView) findViewById(R.id.profileLink);
        username = (TextView) findViewById(R.id.username);
        user_image = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.user_image);


        Intent intent = getIntent();
        String mUsername = intent.getStringExtra("username");
        String mPhoto = intent.getStringExtra("avatar");
        String profileUrl = intent.getStringExtra("profileLink");

        username.setText(mUsername);
        Picasso.with(this).load(mPhoto).fit().placeholder(R.drawable.mask).into(user_image);

        profileLink.setText(profileUrl);

        String title = getIntent().getStringExtra("username");
        setTitle(title + "");

        profileLink = (TextView) findViewById(R.id.profileLink);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //onclick listener for a textview
        profileLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String profileLinkUrl = getIntent().getStringExtra("profileLink");
                Intent openBrowser = new Intent(Intent.ACTION_VIEW);
                openBrowser.setData(Uri.parse(profileLinkUrl));
                startActivity(openBrowser);
            }
        });

        //onclick listener for FAB search
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getIntent().getStringExtra("username");
                String profileLinkUrl = getIntent().getStringExtra("profileLink");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, checkout this awesome developer @" + username + ", " + profileLinkUrl);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}