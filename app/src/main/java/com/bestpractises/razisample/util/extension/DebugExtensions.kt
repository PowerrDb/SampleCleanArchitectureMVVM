package com.bestpractises.razisample.util.extension

import android.util.Log
import com.bestpractises.razisample.BuildConfig

internal val debug = BuildConfig.DEBUG

internal fun Any.log(block: () -> String?) {
    if (debug)
        Log.d(this::class.java.simpleName, "${block()}")
}

internal fun Exception.log(block: () -> String?) {
    if (debug)
        Log.d(this::class.java.simpleName, "${block()}")
}