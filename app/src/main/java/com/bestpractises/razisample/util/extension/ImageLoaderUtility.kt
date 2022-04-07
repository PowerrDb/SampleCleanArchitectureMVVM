package com.bestpractises.razisample.util.extension

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import java.io.File


object ImageLoaderUtility {
    val baseUrl = "https://image.tmdb.org/t/p/w400"
    fun ImageView.loadImage(imageUrl: String) {
        Glide.with(this)
            .load(baseUrl + imageUrl)
            .centerCrop()
//            .error(R.mipmap.sovo_logo)
            .into(this)
    }

    fun ImageView.loadImageUri(imageUrl: Uri) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(this)
    }

    fun ImageView.loadImageResource(imageUrl: Drawable) {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }

    fun ImageView.loadDrawableImage(@DrawableRes resId: Int, color: Int? = null) {
        Glide.with(this)
            .load(resId)
            .into(this)

        if (color != null) {
            this.setColorFilter(
                color,
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    fun ImageView.loadIcon(@DrawableRes resId: Int, color: Int? = null) {
        Glide.with(this)
            .load(resId)
            .into(this)

        if (color != null) {
            this.setColorFilter(
                color,
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    fun ImageView.loadImageFile(resId: File) {
        Glide.with(this)
            .load(resId)
            .into(this)

    }


}