package com.bestpractises.razisample.ui.movieList.data.model

import android.security.identity.ResultData
import com.bestpractises.razisample.util.extension.ResultPizza
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PizzaModel<   T :BasePizza>(
    var result:  T

)

