package com.m2dl.androidhac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewTagActivity extends AppCompatActivity {

    ImageView photoTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tag);

        photoTag = (ImageView) findViewById(R.id.photoTag);

        Bundle bundle = getIntent().getExtras();
        String receive = bundle.getString("photo");
        String[] split = receive.split(" ");

        Picasso.with(photoTag.getContext()).load(split[0]).into(photoTag);
    }
}
