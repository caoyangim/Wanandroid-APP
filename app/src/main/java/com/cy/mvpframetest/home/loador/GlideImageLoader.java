package com.cy.mvpframetest.home.loador;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.mvpframetest.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(30,20,30,20);
        Glide.with(context).load((String) path)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(16)
                        ))
                .into(imageView);
    }
}
