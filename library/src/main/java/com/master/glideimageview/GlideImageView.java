package com.master.glideimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
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

import java.io.File;
import java.net.URL;

/**
 * Developed By Pankaj Sharma
 * https://github.com/pankaj89/
 * <p>
 * GlideImageView used to show progress bar while loading image from server.
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
//            int style = typedArray.getResourceId(R.styleable.GlideImageView_progress_bar_style, android.R.attr.progressBarStyleSmall);
            progressBar = new ProgressBar(getContext(), attrs, android.R.attr.progressBarStyleSmall);
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
               /* if (!(parent instanceof FrameLayout)) {

                    FrameLayout frameLayout = new FrameLayout(getContext());
                    int position = parent.indexOfChild(this);

                    parent.removeView(this);
                    frameLayout.addView(this);

                    progressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
                    frameLayout.addView(progressBar);

                    parent.addView(frameLayout, position);
                } else {*/
                if (!(parent instanceof FrameLayout) || (getLayoutParams().width != FrameLayout.LayoutParams.MATCH_PARENT)
                        ||
                        (getLayoutParams().height != FrameLayout.LayoutParams.MATCH_PARENT)) {

                    FrameLayout frameLayout = new FrameLayout(getContext());
                    int position = parent.indexOfChild(this);

                    parent.removeView(this);
                    frameLayout.addView(this);

                    progressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
                    frameLayout.addView(progressBar);
                    frameLayout.setLayoutParams(getLayoutParams());
                    parent.addView(frameLayout, position);

                } else {
                    int position = parent.indexOfChild(this);
                    parent.addView(progressBar, position + 1);
                    ViewGroup.LayoutParams layoutParams = progressBar.getLayoutParams();
                    layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                    layoutParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
                    if (layoutParams instanceof FrameLayout.LayoutParams)
                        ((FrameLayout.LayoutParams) layoutParams).gravity = Gravity.CENTER;
                }
//                }
            }
        }
    }

    public void loadImageUrl(String stringUrl) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(stringUrl).placeholder(placeHolderRes).error(errorRes).listener(this).dontAnimate().into(this);
    }

    public void load(String string) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(string).placeholder(placeHolderRes).error(errorRes).listener(this).dontAnimate().into(this);
    }

    public void load(Uri uri) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(uri).placeholder(placeHolderRes).error(errorRes).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                hideProgress();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                return false;
            }
        }).dontAnimate().into(this);
    }

    public void load(File file) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(file).placeholder(placeHolderRes).error(errorRes).listener(new RequestListener<File, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, File model, Target<GlideDrawable> target, boolean isFirstResource) {
                hideProgress();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, File model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                return false;
            }
        }).dontAnimate().into(this);
    }

    public void load(Integer resourceId) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(resourceId).placeholder(placeHolderRes).error(errorRes).listener(new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                hideProgress();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                return false;
            }
        }).dontAnimate().into(this);
    }

    public void load(URL url) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(url).placeholder(placeHolderRes).error(errorRes).listener(new RequestListener<URL, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, URL model, Target<GlideDrawable> target, boolean isFirstResource) {
                hideProgress();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, URL model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                return false;
            }
        }).dontAnimate().into(this);
    }

    public void load(byte[] model, final String id) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(model, id).placeholder(placeHolderRes).error(errorRes).listener(new RequestListener<byte[], GlideDrawable>() {
            @Override
            public boolean onException(Exception e, byte[] model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, byte[] model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).dontAnimate().into(this);
    }

    public void load(byte[] model) {
        if (progressBar != null)
            progressBar.setVisibility(VISIBLE);
        Glide.with(getContext()).load(model).placeholder(placeHolderRes).error(errorRes).listener(new RequestListener<byte[], GlideDrawable>() {
            @Override
            public boolean onException(Exception e, byte[] model, Target<GlideDrawable> target, boolean isFirstResource) {
                hideProgress();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, byte[] model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                return false;
            }
        }).dontAnimate().into(this);
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
