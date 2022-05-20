package com.example.ia_highway.bitmapHelper;

import android.graphics.Bitmap;

public class BitmapHelper {
    private Bitmap bitmap;
    private static final BitmapHelper instance = new BitmapHelper();
    
    public BitmapHelper() {
    }

    public static BitmapHelper getInstance() {
        return instance;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
