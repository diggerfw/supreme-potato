package ua.org.klug.planerka.views.bindingAdapters;

import android.databinding.BindingAdapter;
import android.databinding.adapters.ListenerUtil;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import ua.org.klug.planerka.R;

public class ViewAdapters {
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
