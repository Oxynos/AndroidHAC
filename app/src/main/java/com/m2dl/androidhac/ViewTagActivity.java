package com.m2dl.androidhac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewTagActivity extends AppCompatActivity {

    ImageView photoTag;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tag);

        photoTag = (ImageView) findViewById(R.id.photoTag);
        tv = (TextView) findViewById(R.id.typeTag);

        Bundle bundle = getIntent().getExtras();
        String receive = bundle.getString("photo");
        String[] split = receive.split(" ");

        Picasso.with(photoTag.getContext()).load(split[0]).into(photoTag);
        tv.setText(split[1]);
    }
}
