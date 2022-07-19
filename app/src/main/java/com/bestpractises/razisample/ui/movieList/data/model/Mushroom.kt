package com.bestpractises.razisample.ui.movieList.data.model

class Mushroom(val pizza: BasePizza) : BaseContentDecorator() {
    override fun content() :String {
        return pizza.content() + " Mushroom "
    }

    override fun price(): Int {
        return pizza.price().plus(5)
    }
    override fun drawableId(): Int {
        return pizza.drawableId()
    }


}