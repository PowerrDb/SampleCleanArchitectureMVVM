package com.bestpractises.razisample.util.extension

import android.security.identity.ResultData
import com.bestpractises.razisample.ui.movieList.data.model.BasePizza

sealed class Pizza<T : BasePizza> {
        abstract val Pizza  : T

}



