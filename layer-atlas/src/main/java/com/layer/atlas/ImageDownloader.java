package com.layer.atlas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Helson Taveras on 8/13/15.
 */
public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    private ImageView imageView;
    private String imageName;

    public ImageDownloader(ImageView imageView) {
        this.imageView = imageView;
    }

    protected void onPreExecute() {

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        imageName = params[0];
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpCon =
                    (HttpURLConnection) url.openConnection();
            if (httpCon.getResponseCode() != 200)
                throw new Exception("Failed to connect");
            InputStream is = httpCon.getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("Image", "Failed to load image", e);
        }
        return null;
    }

    protected void onProgressUpdate(Integer... params) {

    }

    protected void onPostExecute(Bitmap img) {
        if (imageView != null && img != null) {
            imageView.setImageBitmap(img);
            Log.d(getClass().getSimpleName(), "Downloaded image:" + imageName);
        }
    }

    protected void onCancelled() {

    }
}
