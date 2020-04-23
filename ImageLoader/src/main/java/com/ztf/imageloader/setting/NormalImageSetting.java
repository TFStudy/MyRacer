package com.ztf.imageloader.setting;

import android.widget.ImageView;

public class NormalImageSetting extends ImageSetting {
    /**
     * 支持圆角图片 该值为 圆角的大小值
     */
    private int imgRadius;

    public NormalImageSetting(Builder builder) {
        this.mView = builder.imgView;
        this.mPlaceholder = builder.placeHolder;
        this.mUrl = builder.imgUrl;
        this.imgRadius = builder.imgRadius;
    }

    public int getImgRadius() {
        return imgRadius;
    }

    public void setImgRadius(int imgRadius) {
        this.imgRadius = imgRadius;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String imgUrl;
        private ImageView imgView;
        private int placeHolder;
        private int imgRadius;

        private Builder() {
        }

        public Builder url(String _url) {
            imgUrl = _url;
            return this;
        }

        public Builder imageView(ImageView _imageView) {
            imgView = _imageView;
            return this;
        }

        public Builder placeHolder(int _placeHolder) {
            placeHolder = _placeHolder;
            return this;
        }

        public Builder imageRadius(int _radius) {
            this.imgRadius = _radius;
            return this;

        }

        public NormalImageSetting build() {
            return new NormalImageSetting(this);
        }

    }
}

