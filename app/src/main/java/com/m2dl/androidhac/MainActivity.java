package com.m2dl.androidhac;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, AsyncResponcePhotos {
    private static final int MY_PERMISSIONS_REQUEST = 0;
    User user;
    List<Photo> photos;

    private TextView tv1;
    private Button btnPhoto;
    private ImageView ivPhoto;
    private AsyncTask dbInstance;
    private GoogleMap gMap;
    private Map<String, Photo> mapPhotos;

    private Uri imageUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photos = new ArrayList<>();
        mapPhotos = new HashMap<String, Photo>();

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        tv1 = (TextView) findViewById(R.id.textView);

        if (b != null) {
            String psuedo = (String) b.get("user");
            user = new User(psuedo);
            tv1.setText("Bonjour " + user.getPseudo() + "!\nMerci de votre comportement éco responsable. :) ");
        }

        btnPhoto = (Button) findViewById(R.id.btnPhoto);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        GetAllTagsAsync gata = new GetAllTagsAsync(PhotoRequestServiceDB.getAllTags());
        gata.delegate = this;
        gata.execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }

    public void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + "_" + timeStamp + "_";

        File photo = new File(Environment.getExternalStorageDirectory(), imageFileName + JPEG_FILE_SUFFIX);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        Photo photoTemp = new Photo(imageUri.toString());
        photoTemp.setCoordonnees(new LatLng(latitude, longitude));
        Log.i("Message", photoTemp.getCoordonnees().toString());
        System.out.println(photoTemp.getNom());
        photos.add(photoTemp);
        System.out.println(photos.size());

        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode : " + requestCode + " resultCode : " + resultCode);

        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Intent myIntent = new Intent(this, TagsActivity.class);
                    myIntent.putExtra("imageUri", imageUri);

                    startActivity(myIntent);

                    this.finish();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        switch (id) {
            case R.id.photo:
                Intent intent = new Intent(this, PhotoIntentActivity.class);
                startActivity(intent);
                break;

            case R.id.imv_plan:
                launchPlanActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchMapsActivity(View view) {
        /*Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);*/

        dbInstance = new CreateTables();
        dbInstance.execute();

    }

    public void launchPlanActivity() {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng ups = new LatLng(43.5605378, 1.4686919);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ups, 13));

        gMap = map;
    }

    public void permissions() {
        String permissions[] = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        ActivityCompat.requestPermissions(this, permissions, 3);
    }

    @Override
    public void processFinish(List<Photo> output) {

        boolean equal = false;
        equal = output.size() == photos.size();

        photos = output;
        final Activity act = this;

        if (!equal && gMap != null) {
            for (Photo p : photos) {
                float color = p.getTag() == null ? BitmapDescriptorFactory.HUE_YELLOW : p.getTag().getColor();

                Marker currentMArker = gMap.addMarker(new MarkerOptions()
                        .title(p.getNom())
                        .position(p.getCoordonnees())
                        .icon(BitmapDescriptorFactory.defaultMarker(color)));

                mapPhotos.put(p.getNom(), p);
            }
        }

        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {

                Photo p = mapPhotos.get(arg0.getTitle());

                Intent intent = new Intent(act, ViewTagActivity.class);
                String send = p.getUrl() + " " + p.getTag().toString();
                intent.putExtra("photo", send);
                startActivity(intent);

                return true;
            }

        });
    }
}
