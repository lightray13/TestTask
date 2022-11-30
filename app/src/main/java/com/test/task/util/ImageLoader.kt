package com.test.task.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.test.task.R

object ImageLoader {
    fun loadImage(view: ImageView, url: String, placeholder: Int = R.drawable.ic_baseline_image) {
        Glide.with(view)
            .load("https://lifehack.studio/test_task/$url")
            .placeholder(placeholder)
            .error(placeholder)
            .fallback(placeholder)
            .into(view)
    }
}