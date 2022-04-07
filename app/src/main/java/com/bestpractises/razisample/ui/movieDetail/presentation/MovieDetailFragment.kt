package com.bestpractises.razisample.ui.movieDetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bestpractises.razisample.base.BaseFragment
import com.bestpractises.razisample.databinding.FragmentMovieDetailBinding
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    lateinit var movieModel: MovieResult

    override fun onViewCreatedFirstTime() {
    binding.ivBack.setOnClickListener {
        findNavController().navigateUp()
    }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivCover.loadImage(movieModel.backdropPath)
            tvTitle.text = movieModel.title
            tvDescription.text = movieModel.overview
            tvScore.text = movieModel.popularity.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        movieModel = arguments?.getSerializable("movieModel") as MovieResult
        return binding.root

    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            var movieDetailFragment = MovieDetailFragment()
            return movieDetailFragment
        }

    }


}
