package com.bestpractises.razisample.util.extension

import android.security.identity.ResultData
import com.bestpractises.razisample.ui.movieList.data.model.BasePizza

sealed class ResultPizza<T> {
        abstract val Pizza  : T

}



