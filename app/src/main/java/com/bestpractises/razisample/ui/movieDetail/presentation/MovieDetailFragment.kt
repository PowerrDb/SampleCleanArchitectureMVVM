package com.bestpractises.razisample.ui.movieDetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieDetailBinding


    override fun onViewCreatedFirstTime() {
    binding.ivBack.setOnClickListener {
        findNavController().navigateUp()
    }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            var movieDetailFragment = MovieDetailFragment()
            return movieDetailFragment
        }

    }


}
