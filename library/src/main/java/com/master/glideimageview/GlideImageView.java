package com.master.glideimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Developed By Pankaj Sharma
 * https://github.com/pankaj89/
 *
 * GlideImageView used to show progress bar while loading image from server.
 * 
 */
public class GlideImageView extends AppCompatImageView implements RequestListener<String, GlideDrawable> {

    ProgressBar progressBar;
    private boolean showProgressBar;

    private int errorRes;


    private int placeHolderRes;

    public GlideImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GlideImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GlideImageView);
        if (typedArray.hasValue(R.styleable.GlideImageView_show_progress)) {
            showProgressBar = typedArray.getBoolean(R.styleable.GlideImageView_show_progress, true);
            progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmall);
//            progressBar.setVisibility(GONE);
        }

        if (typedArray.hasValue(R.styleable.GlideImageView_error_res)) {
            errorRes = typedArray.getResourceId(R.styleable.GlideImageView_error_res, 0);
        }
        if (typedArray.hasValue(R.styleable.GlideImageView_placeholder_res)) {
            placeHolderRes = typedArray.getResourceId(R.styleable.GlideImageView_placeholder_res, 0);
        }
    }

    public void setErrorRes(int errorRes) {
        this.errorRes = errorRes;
    }

    public void setPlaceHolderRes(int placeHolderRes) {
        this.placeHolderRes = placeHolderRes;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (showProgressBar && progressBar.getParent() == null) {
            ViewParent viewGroupParent = getParent();
            if (viewGroupParent != null && viewGroupParent instanceof ViewGroup) {

                ViewGroup parent = (ViewGroup) viewGroupParent;
                if (!(parent instanceof FrameLayout)) {

                    FrameLayout frameLayout = new FrameLayout(getContext());
                    int position = parent.indexOfChild(this);

                    parent.removeView(this);
                    frameLayout.addView(this);

                    progressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
                    frameLayout.addView(progressBar);

                    parent.addView(frameLayout, position);
                } else {
                    int position = parent.indexOfChild(this);
                    parent.addView(progressBar, position + 1);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) progressBar.getLayoutParams();
                    layoutParams.gravity = Gravity.CENTER;
                }
            }
        }
    }

    public void loadImageUrl(String stringUrl) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(stringUrl).placeholder(placeHolderRes).error(errorRes).listener(this).dontAnimate().into(this);
    }


    @Override
    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
        if (progressBar != null)
            progressBar.setVisibility(GONE);
        return false;
    }

    @Override
    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
        if (progressBar != null)
            progressBar.setVisibility(GONE);
        return false;
    }

    public void hideProgress() {
        if (progressBar != null)
            progressBar.setVisibility(GONE);
    }

    public void showProgress() {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
    }
}
