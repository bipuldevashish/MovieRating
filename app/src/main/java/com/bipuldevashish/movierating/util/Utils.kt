package com.bipuldevashish.movierating.util

import android.widget.ImageView
import com.bipuldevashish.movierating.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object Utils {

    fun ImageView.loadImage(uri: String?) {
        val options = RequestOptions()
            .placeholder(R.drawable.loading)
            .error(R.drawable.attention)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }

}