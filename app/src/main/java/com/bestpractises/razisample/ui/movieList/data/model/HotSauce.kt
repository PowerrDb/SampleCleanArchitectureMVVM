package com.bestpractises.razisample.ui.movieList.data.model

class HotSauce(val pizza: BasePizza): BaseContentDecorator() {
    override fun content() :String {
        return pizza.content() + " HotSauce "
    }

    override fun price(): Int {
         return pizza.price().plus(5)
    }

    override fun drawableId(): Int {
        return pizza.drawableId()
    }

}