package com.bestpractises.razisample.ui.movieDetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieDetailBinding
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.ui.movieList.presentation.SharedViewModel
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadImage
import com.bestpractises.razisample.util.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    lateinit var movieModel: MovieResult
    private val viewModel by activityViewModels<SharedViewModel>()
    override fun onViewCreatedFirstTime() {
    binding.ivBack.setOnClickListener {
        findNavController().navigateUp()
    }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
//        movieModel = arguments?.getSerializable("movieModel") as MovieResult
        return binding.root

    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            var movieDetailFragment = MovieDetailFragment()
            return movieDetailFragment
        }

    }


}
