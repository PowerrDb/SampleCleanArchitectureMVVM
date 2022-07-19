package com.bestpractises.razisample.ui.movieList.presentation

import android.os.Bundle
import android.security.identity.ResultData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment() {
    private val viewModel by activityViewModels<SharedViewModel>()
    private lateinit var binding: FragmentMovieBinding
    private val mAdapter : PizzasAdapter by lazy { PizzasAdapter(::onItemClicked)}
    private val mContentAdapter : ContentsAdapter by lazy { ContentsAdapter(::onContentItemClicked)}


    private fun onItemClicked(pizzaModel: PizzaModel<out BasePizza>, i: Int) {
       viewModel.lastPizzaPosition=i
       viewModel.pizzaModel=pizzaModel

    }



    private fun onContentItemClicked(contentModel: ContentModel ) {
        when (contentModel.name) {
            "Hallopino"->{mAdapter.currentList[viewModel.lastPizzaPosition].result=Hallopino(viewModel.pizzaModel!!.result)}
            "Mushroom"->{mAdapter.currentList[viewModel.lastPizzaPosition].result=Mushroom(viewModel.pizzaModel!!.result)}
            "HotSauce"->{mAdapter.currentList[viewModel.lastPizzaPosition].result=HotSauce(viewModel.pizzaModel!!.result)}
        }
        mAdapter.notifyDataSetChanged()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.submitList(viewModel.listPizza)
        binding.rvMovies.adapter = mAdapter

        mContentAdapter.submitList(viewModel.listContents)
        binding.rvContents.adapter = mContentAdapter

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



}
