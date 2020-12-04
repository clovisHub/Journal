package com.example.journal.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

object ImagesUtils {

    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    fun setBitmapImage(view: View, url: String?, imageView: ImageView)  {
        url?.let {
            Glide.with(view)
                .asBitmap()
                .load(url)
                .centerCrop()
                .into(imageView)
        }
    }

    fun setSettingsImageUrlFitCenterMessage(view: View, url: String?, imageView: ImageView)  {
        url?.let {
            Glide.with(view)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(withCrossFade(factory))
                .into(imageView)
        }
    }
}