package com.bestpractises.razisample.ui.movieList.data.model

class Hallopino (val pizza: BasePizza) : BaseContentDecorator() {


    override fun  content() :String {
        return pizza.content() + "  Hallopino "
    }

    override fun price(): Int {
         return pizza.price().plus(10)
    }

    override fun drawableId(): Int {
        return pizza.drawableId()
    }

}