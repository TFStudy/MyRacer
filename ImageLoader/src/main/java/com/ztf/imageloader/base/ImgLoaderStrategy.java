package com.ztf.imageloader.base;

import android.content.Context;

import com.ztf.imageloader.setting.ImageSetting;

public interface ImgLoaderStrategy<Setting extends ImageSetting> {
    void loadImage(Context context,Setting setting);
}
