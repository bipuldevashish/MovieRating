package com.example.assignment.util

import android.widget.ImageView
import com.bipuldevashish.movierating.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object Utils {

    fun ImageView.loadImage(uri: String?) {
        val options = RequestOptions()
            .placeholder(R.drawable.loading)
            .circleCrop()
            .error(R.drawable.attention)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }

}