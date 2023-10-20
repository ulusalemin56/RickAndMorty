package com.example.rickandmorty.util.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getDrawableEx(@DrawableRes icon: Int): Drawable? {
    return ContextCompat.getDrawable(this, icon)
}

fun Context.getColoreEx(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}
