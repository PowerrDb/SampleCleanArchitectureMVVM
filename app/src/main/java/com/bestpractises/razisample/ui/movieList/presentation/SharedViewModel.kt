package com.bestpractises.razisample.ui.movieList.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestpractises.razisample.R
import com.bestpractises.razisample.network.ErrorModel
import com.bestpractises.razisample.ui.movieList.data.model.*
import com.bestpractises.razisample.ui.movieList.domain.MovieListUsecase
import com.bestpractises.razisample.util.AppUtility
import com.bestpractises.razisample.util.extension.ResultPizza
import com.google.gson.Gson
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    val listPizza = mutableListOf<PizzaModel<BasePizza>>(PizzaModel( MargaritaPizza()),PizzaModel(PeperoniPizza()),PizzaModel((SteakPizza())))
    val listContents = arrayListOf(ContentModel(10,"Hallopino",R.drawable.halopino),ContentModel(5,"Mushroom",R.drawable.mushrom),ContentModel(5,"HotSauce",R.drawable.sauce))
    var lastPizzaPosition :Int =0
     var pizzaModel: PizzaModel<out BasePizza>?=listPizza[0]






}