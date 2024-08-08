package net.trinopolis.walpaper;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;

public class FullScreenWallpaper extends AppCompatActivity {

    private String originalUrl = "";
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_wallpaper);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Get the URL from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("originalUrl")) {
            originalUrl = intent.getStringExtra("originalUrl");
        } else {
            // Handle the case where originalUrl is null
            Toast.makeText(this, "No image URL provided", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no URL is provided
            return;
        }

        // Find the PhotoView and load the image with Glide
        photoView = findViewById(R.id.photoView);
        if (photoView != null) {
            Glide.with(this).load(originalUrl).into(photoView);
        }
    }

    public void SetWallpaperEvent(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        BitmapDrawable drawable = (BitmapDrawable) photoView.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = drawable.getBitmap();
            try {
                wallpaperManager.setBitmap(bitmap);
                Toast.makeText(this, "Wallpaper Set", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Error: Unable to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    public void DownloadWallpaperEvent(View view) {
        Uri uri = Uri.parse(originalUrl);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            downloadManager.enqueue(request);
            Toast.makeText(this, "Downloading Start", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Unable to start download", Toast.LENGTH_SHORT).show();
        }
    }
}
