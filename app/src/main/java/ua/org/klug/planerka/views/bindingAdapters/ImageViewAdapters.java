package ua.org.klug.planerka.views.bindingAdapters;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

public class ImageViewAdapters {
    @BindingAdapter({"bind:bitmapImage", "bind:placeholder"})
    public static void setImageBitmap(ImageView imageView, Bitmap imageBitmap, int placeholder) {
        Log.d("binder", imageBitmap == null ? " is null  " : imageBitmap.toString());
        Log.d("binder", " placeholder:" + placeholder);
        if (imageBitmap == null) {
            imageView.setImageResource(placeholder);
        } else {
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @BindingAdapter({"bind:bitmapImage"})
    public static void setImageBitmap(ImageView imageView, Bitmap imageBitmap) {
        Log.d("binder", imageBitmap == null ? " is null  " : imageBitmap.toString());
        imageView.setImageBitmap(imageBitmap);
    }
}
