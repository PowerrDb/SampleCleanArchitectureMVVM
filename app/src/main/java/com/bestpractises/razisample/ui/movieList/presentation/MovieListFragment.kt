package com.bestpractises.razisample.ui.movieList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bestpractises.razisample.R
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.util.extension.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment() {
    private val viewModel by viewModels<MovieListViewModel>()
    private var mItems: MutableList<MovieResult>? = mutableListOf()
    private lateinit var binding: FragmentMovieBinding
    private val mAdapter : MoviesAdapter by lazy { MoviesAdapter(::onItemClicked)}
    private var data: MovieItem? = null
    private var page: Int = 1
    private var totalPages: Int? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var listener: RecyclerView.OnScrollListener? = null
    private var isLoading: Boolean = false
    private val doOnNextPage: (nextPage: Int) -> Unit = ::nexPage

    private fun onItemClicked(movieModel: MovieResult) {
        val bundle = bundleOf("movieModel" to movieModel)
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.movieListLiveData, ::movieListData)
        initRecyclerView()
        setListener()
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
        binding.clError.gone()
        binding.btnRetry.setOnClickListener {
            isLoading=true
            viewModel.getMovieList(1)
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            mItems?.clear()
            viewModel.getMovieList(1)
        }
    }

    private fun movieListData(result: ResultData<MovieItem>?) {
        when (result) {
            is ResultData.Success -> {
                isLoading = false
                binding.progressBar.gone()
                mItems?.addAll(result.data.results)
                mAdapter.submitList(ArrayList(mItems))
                totalPages = result.data.totalPages
                page = result.data.page
                page += 1
                binding.clError.gone()
            }
            is ResultData.Loading -> {
                binding.progressBar.visible()
                binding.clError.gone()
            }
            is ResultData.Failed  -> {
                isLoading = false
                showRetry(result.message)

            }
            is ResultData.Exception -> {
                isLoading = false
                showRetry(result.message)
            }
        }
    }

    private fun showRetry(message: String) {
        binding.progressBar.gone()
        if (mItems.isNullOrEmpty()) {
            binding.clError.visible()
            binding.tvTitleError.text = message
        } else errorMessage(message)
    }


    private fun nexPage(nextPage: Int) {
        isLoading = true
        viewModel.getMovieList(nextPage)
    }

    fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.run {
            adapter = mAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager

        }
        listener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager!!.childCount
                val totalItemCount = linearLayoutManager!!.itemCount-10
                val firstVisibleItemPosition = linearLayoutManager!!.findFirstVisibleItemPosition()
                if (totalPages != null) {
                    if (!isLoading && totalPages!! > page) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                            doOnNextPage(page)
                        }
                    }
                }
            }
        }
        binding.rvMovies.addOnScrollListener(listener!!)
    }

}
