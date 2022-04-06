package com.bestpractises.razisample.util.extension

import android.view.View

fun View.visible() {
    if (visibility == View.GONE || visibility == View.INVISIBLE)
        visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility == View.GONE || visibility == View.VISIBLE)
        visibility = View.INVISIBLE
}

fun View.gone() {
    if (visibility == View.VISIBLE || visibility == View.INVISIBLE)
        visibility = View.GONE
}