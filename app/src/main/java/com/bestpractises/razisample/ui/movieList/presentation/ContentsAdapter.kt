package com.bestpractises.razisample.ui.movieList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bestpractises.razisample.R
import com.bestpractises.razisample.databinding.AdapterItemContentsBinding
import com.bestpractises.razisample.databinding.AdapterItemMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.BaseContentDecorator
import com.bestpractises.razisample.ui.movieList.data.model.BasePizza
import com.bestpractises.razisample.ui.movieList.data.model.ContentModel
import com.bestpractises.razisample.ui.movieList.data.model.MovieResult
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadDrawableImage
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadImage
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadImageUri
import kotlin.math.roundToInt


class ContentsAdapter(val onItemClicked: (movieModel: ContentModel) -> Unit) :
    ListAdapter<ContentModel, ContentsAdapter.MovieListViewHolder>(DIFFER_CALLBACK) {

    companion object {
        val DIFFER_CALLBACK: DiffUtil.ItemCallback<ContentModel> = object :
            DiffUtil.ItemCallback<ContentModel>() {
            override fun areItemsTheSame(
                oldItem: ContentModel,
                newItem: ContentModel
            ): Boolean {
                return  oldItem.drawableId == newItem.drawableId
            }

            override fun areContentsTheSame(
                oldItem: ContentModel,
                newItem: ContentModel
            ): Boolean {
                return oldItem  == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val binding = AdapterItemContentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
       holder.onBind(currentList[position])
    }

    inner class MovieListViewHolder(
        val item: AdapterItemContentsBinding,
        val onItemClicked: (movieModel: ContentModel) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {
        fun onBind(movieModel: ContentModel) {
            item.root.setOnClickListener {
                onItemClicked(movieModel)
            }
            item.ivCover.loadDrawableImage(movieModel.drawableId)
            item.tvTitle.text = movieModel.name
          item.tvDescription.text = movieModel.price.toString()+"$"
          // item.tvScore.text = movieModel.popularity.toString()
        }
    }
}