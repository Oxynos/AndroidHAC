package com.m2dl.androidhac;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.Url;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by veoth on 22/01/16.
 */
public class PhotoStorage extends AsyncTask<Object, Object, String> {

    Map config = new HashMap();
    Cloudinary cloudinary;
    public AsyncResponse delegate = null;

    public PhotoStorage() {
        cloudinary = new Cloudinary("cloudinary://865819392471167:ZpLRgpVP0B2cwtPi2UxD_-xgud4@hbzvf90yn");
    }

    @Override
    protected String doInBackground(Object[] params) {
        URL url;
        Map result = null;

        try {

            /*url = new URL("https://www.google.fr/logos/doodles/2016/wilbur-scovilles-151st-birthday-6275288709201920-res.png");
            InputStream input = url.openStream();
            Bitmap b = BitmapFactory.decodeStream(input);*/


            /*File imageF = File.createTempFile("TEST", ".jpg", getAlbumDir());
            OutputStream os;
            os = new FileOutputStream(imageF);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close()*/;

            InputStream is = (InputStream)params[0];
            String id = (String)params[1];

            result = cloudinary.uploader().upload(is, ObjectUtils.asMap(
                    "public_id", id,
                    "transformation", new Transformation().crop("limit").width(30).height(40),
                    "sslmode", "require"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String)result.get("url");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.processFinish(result);
    }

    protected Bitmap dlPhoto() {

        File toUpload = new File("https://www.google.fr/logos/doodles/2016/wilbur-scovilles-151st-birthday-6275288709201920-res.png");

        /*try {
            Map result = cloudinary.uploader().upload(toUpload, ObjectUtils.asMap(
                    "public_id", "sample_id",
                    "sslmode", "require",
                    "transformation", new Transformation().crop("limit").width(40).height(40),
                    "eager", Arrays.asList(
                            new Transformation().width(200).height(200)
                                    .crop("thumb").gravity("face").radius(20)
                                    .effect("sepia"),
                            new Transformation().width(100).height(150)
                                    .crop("fit").fetchFormat("png")
                    ),
                    "tags", "special, for_homvepage"));
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
        String u = cloudinary.url()
                .transformation(new Transformation().width(100).height(150).crop("fill"))
                .imageTag("sample.jpg");
        URL url;
        Bitmap b = null;
        try {
            url = new URL("https://www.google.fr/logos/doodles/2016/wilbur-scovilles-151st-birthday-6275288709201920-res.png");
            InputStream input = url.openStream();
            // Decode Bitmap
            b = BitmapFactory.decodeStream(input);
            //iv.setImageBitmap(b);
            Map result = cloudinary.uploader().upload(b, ObjectUtils.asMap(
                    "public_id", "sample_id",
                    "sslmode", "require",
                    "transformation", new Transformation().crop("limit").width(40).height(40),
                    "eager", Arrays.asList(
                            new Transformation().width(200).height(200)
                                    .crop("thumb").gravity("face").radius(20)
                                    .effect("sepia"),
                            new Transformation().width(100).height(150)
                                    .crop("fit").fetchFormat("png")
                    ),
                    "tags", "special, for_homepage"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return b;
    }*/
        return null;
    }

}