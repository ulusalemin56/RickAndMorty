package com.example.rickandmorty.util.extension

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

fun ImageView.loadImage(url: String?) {
    val errorPlaceHolder = R.drawable.error_character_place_holder
    val progressColor = ContextCompat.getColor(this.context, R.color.white)
    val circularProgress = CircularProgressDrawable(this.context).apply {
        strokeWidth = 10f
        centerRadius = 100f
        setColorSchemeColors(progressColor)
        start()
    }

    url?.let {
        Glide.with(this.context)
            .load(url)
            .placeholder(circularProgress)
            .error(errorPlaceHolder)
            .into(this)
    } ?: kotlin.run {
        this.setImageResource(errorPlaceHolder)
    }
}