package com.bestpractises.razisample.ui.movieList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.util.extension.*
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import retrofit2.Response

@AndroidEntryPoint
class MovieListFragment : BaseFragment() {
    val viewModel by viewModels<MovieListViewModel>()
    private lateinit var binding: FragmentMovieBinding
    val mAdapter = MoviesAdapter(::onItemClicked)
    private fun onItemClicked() {


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.movieListLiveData, ::movieListData)
        initRecyclerView()
        setListener()
        viewModel.getMovieList(1)
    }
    override fun onViewCreatedFirstTime() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun setListener() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovieList(1)
        }
    }

    private fun movieListData(result: ResultData<MovieItem>?) {
        when (result) {
            is ResultData.Success -> {
                mAdapter.submitList(result.data.results)
            }
            is ResultData.Loading -> {}
            is ResultData.Failed -> {
                errorMessage(result.message)
            }
            is ResultData.Exception -> {
                errorMessage(result.message)
            }
        }
    }

    fun initRecyclerView() {
        binding.rvMovies.run {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    companion object {
        fun newInstance(): MovieListFragment {
            var movieListFragment = MovieListFragment()
            return movieListFragment
        }

    }


}
