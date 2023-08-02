package com.gekaradchenko.game.taptapproject.util

import android.content.Context
import android.util.DisplayMetrics

private fun getDisplayMetrics(context: Context): DisplayMetrics = context.resources.displayMetrics

fun Context.getDisplayRatio() = getDisplayMetrics(this).let {
    it.widthPixels.toFloat() / it.heightPixels.toFloat()
}

