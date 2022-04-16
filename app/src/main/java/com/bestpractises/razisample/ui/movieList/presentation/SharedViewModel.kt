package com.bestpractises.razisample.ui.movieList.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestpractises.razisample.R
import com.bestpractises.razisample.network.ErrorModel
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.ui.movieList.domain.MovieListUsecase
import com.bestpractises.razisample.util.AppUtility
import com.bestpractises.razisample.util.extension.ResultData
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
class SharedViewModel @Inject constructor(
    val useCase:  MovieListUsecase,
    val application: Application
) : ViewModel() {
    val movieListLiveData = MutableLiveData<ResultData<MovieItem>>()
    val movieDetailLiveData = MutableLiveData<MovieResult>()

    val moviesErrorHandler: CoroutineExceptionHandler
    private var getMoviesJob: Job? = null


    init {
        moviesErrorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            movieListLiveData.postValue(
                ResultData.Exception(
                    AppUtility.getErrorMessageStr(
                        application,
                        throwable
                    )
                )
            )
      }

    }

    fun getMovieList(page: Int) {
        getMoviesJob = viewModelScope.launch(IO + moviesErrorHandler) {
            movieListLiveData.postValue(ResultData.Loading(true))
            val result = useCase.getMovies(page)
            when {
                result.isSuccessful -> {
                    movieListLiveData.postValue(ResultData.Success(result.body()!!))
                }
                result.code() ==401 || result.code()==404  -> {
                    val error =
                        Gson().fromJson(result.errorBody()?.charStream(), ErrorModel::class.java)
                    movieListLiveData.postValue(ResultData.Failed(error.status_message))
                }
                else -> {
                    movieListLiveData.postValue(ResultData.Failed(application.getString(
                        R.string.app_name)))
                }

            }
        }
    }


}