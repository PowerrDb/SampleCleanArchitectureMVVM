package com.bestpractises.razisample.ui.movieList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bestpractises.razisample.R
import com.bestpractises.razisample.databinding.AdapterItemMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadImage
import kotlin.math.roundToInt


class MoviesAdapter(val onItemClicked: () -> Unit) :
    ListAdapter<MovieResult, MoviesAdapter.MovieListViewHolder>(DIFFER_CALLBACK) {

    companion object {
        val DIFFER_CALLBACK: DiffUtil.ItemCallback<MovieResult> = object :
            DiffUtil.ItemCallback<MovieResult>() {
            override fun areItemsTheSame(
                oldItem: MovieResult,
                newItem: MovieResult
            ): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieResult,
                newItem: MovieResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val binding = AdapterItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
       holder.onBind(currentList[position])
    }

    inner class MovieListViewHolder(
        val item: AdapterItemMovieBinding,
        val onItemClicked: () -> Unit
    ) : RecyclerView.ViewHolder(item.root) {
        fun onBind(movieModel: MovieResult) {
            item.ivCover.loadImage(movieModel.backdropPath)
            item.tvTitle.text = movieModel.title
            item.tvDescription.text = movieModel.overview
            item.tvScore.text = movieModel.popularity.toString()
        }
    }
}