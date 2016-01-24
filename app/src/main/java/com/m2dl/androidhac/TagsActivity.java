package com.m2dl.androidhac;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TagsActivity extends AppCompatActivity {

    private static final String BITMAP_STORAGE_KEY = "viewbitmap";
    private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
    private ImageView mImageView;
    private Bitmap mImageBitmap;
    private TextView tv1;
    private Button button;

    private Uri imageUri;
    private Tag tag;

    private String mCurrentPhotoPath;
    private DatabaseTask databaseTask;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv1 = (TextView) findViewById(R.id.textView2);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageBitmap = null;
        button = (Button) findViewById(R.id.btnValider);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            imageUri = (Uri) b.get("imageUri");
            System.out.println("Test" + imageUri);
            tv1.setText(imageUri.toString());
        }

        setPic();
    }

    // Some lifecycle callbacks so that the image can survive orientation change
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
        outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
        mImageView.setImageBitmap(mImageBitmap);
        mImageView.setVisibility(
                savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ?
                        ImageView.VISIBLE : ImageView.INVISIBLE
        );
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rBtDegradation:
                if (checked) {
                    tag = Tag.DEGRADATION;
                    button.setText("Enregistrer Dégradation");
                }
                    break;
            case R.id.rBtFuite:
                if (checked) {
                    tag = Tag.FUITEDEAU;
                    button.setText("Enregistrer Fuite");

                }
                break;
            case R.id.rBtRecyclage:
                if (checked) {
                    tag = Tag.RECYCLAGE;
                    button.setText("Enregistrer Zone Recyclage");
                }

                    break;
        }


    }

    private void setPic() {
        Uri selectedImage = imageUri;
        getContentResolver().notifyChange(selectedImage, null);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ContentResolver cr = getContentResolver();
        Bitmap bitmap;
        try {
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);

            mImageView.setImageBitmap(bitmap);
            mImageView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                    .show();
            Log.e("Camera", e.toString());
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void storePic(View view) {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        //TODO : Avoir une URL d'image
        Photo photo = new Photo("",imageUri.getPath(), new LatLng(latitude, longitude), tag);

        databaseTask = new DatabaseTask(PhotoRequestServiceDB.createPhoto(photo));
        databaseTask.execute();
    }
}
