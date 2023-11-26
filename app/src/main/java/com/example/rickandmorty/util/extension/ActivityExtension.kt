package com.example.rickandmorty.util.extension

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun Activity.showMotionToast(title : String, description : String, motionStyle : MotionToastStyle) {
    MotionToast.darkColorToast(
        this,
        title,
        description,
        motionStyle,
        MotionToast.GRAVITY_CENTER,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}