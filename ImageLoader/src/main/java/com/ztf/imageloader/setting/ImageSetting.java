package com.ztf.imageloader.setting;

import android.widget.ImageView;

public class ImageSetting {
    /**
     * 图片URL
     */
    protected String mUrl;
    /**
     * View
     */
    protected ImageView mView;
    /**
     * 占位图
     */
    protected int mPlaceholder;

    public String getmUrl() {
        return mUrl;
    }

    public ImageView getmView() {
        return mView;
    }

    public int getmPlaceholder() {
        return mPlaceholder;
    }
}
