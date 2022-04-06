package com.bestpractises.razisample.ui.movieList.presentation

import android.os.Bundle
import android.view.View
import com.bestpractises.razisample.base.MainBaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : MainBaseFragment() {
    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreatedFirstTime() {}


    companion object {
        fun newInstance(): MovieListFragment {
            val movieListFragment = MovieListFragment()
            return movieListFragment
        }

    }


}
