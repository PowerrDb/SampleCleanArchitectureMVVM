package com.bestpractises.razisample.ui.movieList.data.model

import com.bestpractises.razisample.R

class MargaritaPizza : BasePizza() {
    override fun content(): String {
        return "Margarita"
    }

    override fun price(): Int {
        return 100
    }
    override fun drawableId(): Int {
        return R.drawable.margarita
    }
}