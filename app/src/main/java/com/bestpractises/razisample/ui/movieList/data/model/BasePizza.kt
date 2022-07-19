package com.bestpractises.razisample.ui.movieList.data.model



abstract class BasePizza {


   open  fun content() :String{
        return "BasePizza"
    }
    abstract fun price() :Int

    open fun drawableId() : Int{
        return 0
    }
}