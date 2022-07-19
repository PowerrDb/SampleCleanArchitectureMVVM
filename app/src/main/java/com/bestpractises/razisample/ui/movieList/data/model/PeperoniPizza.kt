package com.bestpractises.razisample.ui.movieList.data.model

import com.bestpractises.razisample.R

class PeperoniPizza : BasePizza() {
    override fun content(): String {
        return "Peperoni"
    }

    override fun price(): Int {
        return 90
    }

    override fun drawableId(): Int {
        return R.drawable.peproni
    }
}