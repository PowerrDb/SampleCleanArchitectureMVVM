package com.bestpractises.razisample.ui.movieList.data.model

import com.bestpractises.razisample.R

class SteakPizza : BasePizza() {
    override fun content(): String {
        return "Steak"
    }

    override fun price(): Int {
        return 150
    }

    override fun drawableId(): Int {
        return R.drawable.steack
    }

}