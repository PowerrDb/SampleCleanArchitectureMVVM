package com.bestpractises.razisample.ui.movieList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestpractises.razisample.R
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.util.extension.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment() {
    private var isInitPagination: Boolean=false
    val viewModel by viewModels<MovieListViewModel>()
    var  mItems : MutableList<MovieResult> ? = mutableListOf()
    private lateinit var binding: FragmentMovieBinding
    val mAdapter = MoviesAdapter(::onItemClicked)
    var data  : ResultData.Success<MovieItem>?=null
    private fun onItemClicked(movieModel: MovieResult) {
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.movieListLiveData, ::movieListData)
        initRecyclerView()
        setListener()
        isInitPagination = false
        pagination(data)
    }
    override fun onViewCreatedFirstTime() {
        viewModel.getMovieList(1)

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
            binding.swipeRefresh.isRefreshing = false
            mItems?.clear()
            isInitPagination=false
            viewModel.getMovieList(1)
        }
    }

    private fun movieListData(result: ResultData<MovieItem>?) {
        when (result) {
            is ResultData.Success -> {
                isLoading=false
                isloading()
                binding.progressBar.gone()
                mItems?.addAll(result.data.results)
                mAdapter.submitList(ArrayList(mItems))
                pagination(result)
                data=result
            }
            is ResultData.Loading -> {
                binding.progressBar.visible()
            }
            is ResultData.Failed -> {
                binding.progressBar.gone()
                errorMessage(result.message)
                isInitPagination=false
                isLoading =false
                isloading()
                pagination(data)
            }
            is ResultData.Exception -> {
                isInitPagination=false
                isLoading =false
                isloading()
                pagination(data)
                binding.progressBar.gone()
                errorMessage(result.message)

            }
        }
    }

    private fun pagination(result: ResultData.Success<MovieItem>?) {
        result?.let {
            if (!isInitPagination) {
                isInitPagination = true
                binding.rvMovies.pagination(result.data.totalPages, callback, loadNextPage = {
                    isLoading = true
                    isloading()
                    viewModel.getMovieList(it)
                })
            }
        }

    }

    val callback : () -> Boolean = ::isloading

    private fun isloading(): Boolean {
        return isLoading
    }

    var isLoading: Boolean = false
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
