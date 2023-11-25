package com.example.rickandmorty.util.extension

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

fun ImageView.loadImage(url: String?) {
    val errorPlaceHolder = R.drawable.error_character_place_holder
    val progressColor = ContextCompat.getColor(this.context, R.color.black)
    val circularProgress = CircularProgressDrawable(this.context).apply {
        strokeWidth = 10f
        centerRadius = 80f
        setColorSchemeColors(progressColor)
        start()
    }

    if (url.isNullOrEmpty()) {
        this.setImageResource(errorPlaceHolder)
    } else {
        Glide.with(this.context)
            .load(url)
            .placeholder(circularProgress)
            .error(errorPlaceHolder)
            .into(this)
    }
}