package com.bestpractises.razisample.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData


fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (it: T) -> Unit) {
    if (!liveData.hasObservers())
        liveData.observe(this, { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.removeObserver(liveData: LiveData<T>) {
    liveData.removeObservers(this)
}


