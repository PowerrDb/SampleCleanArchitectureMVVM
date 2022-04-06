package com.bestpractises.razisample.ui.movieList.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.ui.movieList.domain.MovieListUsecase
import com.bestpractises.razisample.util.AppUtility
import com.bestpractises.razisample.util.extension.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val useCase:  MovieListUsecase,
    application: Application
) : ViewModel() {
    val movieListLiveData = MutableLiveData<ResultData<Response<MovieItem>>>()

    val moviesErrorHandler: CoroutineExceptionHandler
    private var getMoviesJob: Job? = null


    init {
        moviesErrorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            movieListLiveData.postValue(
                ResultData.Failed(
                    AppUtility.getErrorMessageStr(
                        application,
                        throwable
                    )
                )
            )
      }

    }

    fun getMovieList(page: Int) {
        getMoviesJob = viewModelScope.launch(moviesErrorHandler) {
            movieListLiveData.postValue(ResultData.Loading(true))
            val resullt = useCase.getMovies(page)
            movieListLiveData.postValue(ResultData.Success(resullt))
        }
    }


}