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

    @BindingAdapter("android:onViewAttachedToWindow")
    public static void setListener(View view, ViewBindingAdapter.OnViewAttachedToWindow attached) {
        setListener(view, null, attached);
    }

    @BindingAdapter("android:onViewDetachedFromWindow")
    public static void setListener(View view, ViewBindingAdapter.OnViewDetachedFromWindow detached) {
        setListener(view, detached, null);
    }

    @BindingAdapter({"android:onViewDetachedFromWindow", "android:onViewAttachedToWindow"})
    public static void setListener(View view, final ViewBindingAdapter.OnViewDetachedFromWindow detach,
                                   final ViewBindingAdapter.OnViewAttachedToWindow attach) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            final View.OnAttachStateChangeListener newListener;
            if (detach == null && attach == null) {
                newListener = null;
            } else {
                newListener = new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                        if (attach != null) {
                            attach.onViewAttachedToWindow(v);
                        }
                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        if (detach != null) {
                            detach.onViewDetachedFromWindow(v);
                        }
                    }
                };
            }
            final View.OnAttachStateChangeListener oldListener = ListenerUtil.trackListener(view,
                    newListener, R.id.onAttachStateChangeListener);
            if (oldListener != null) {
                view.removeOnAttachStateChangeListener(oldListener);
            }
            if (newListener != null) {
                view.addOnAttachStateChangeListener(newListener);
            }
        }
    }
}
