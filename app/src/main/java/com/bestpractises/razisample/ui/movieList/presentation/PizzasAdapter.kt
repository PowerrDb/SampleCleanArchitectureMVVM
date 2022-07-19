package com.bestpractises.razisample.ui.movieList.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bestpractises.razisample.databinding.AdapterItemMovieBinding
import com.bestpractises.razisample.ui.movieList.data.model.BasePizza
import com.bestpractises.razisample.ui.movieList.data.model.ContentModel
import com.bestpractises.razisample.ui.movieList.data.model.PizzaModel
import com.bestpractises.razisample.util.extension.ImageLoaderUtility.loadDrawableImage
import kotlin.reflect.KFunction2


class PizzasAdapter(val onItemClicked: (PizzaModel<out BasePizza>,position : Int) -> Unit) :
    ListAdapter<PizzaModel<BasePizza>, PizzasAdapter.MovieListViewHolder>(DIFFER_CALLBACK) {

    companion object {
        val DIFFER_CALLBACK: DiffUtil.ItemCallback<PizzaModel<BasePizza>> = object :
            DiffUtil.ItemCallback<PizzaModel<BasePizza>>() {
            override fun areItemsTheSame(
                oldItem: PizzaModel<BasePizza>,
                newItem: PizzaModel<BasePizza>
            ): Boolean {
                return  oldItem.result.price() == newItem.result.price()
            }

            override fun areContentsTheSame(
                oldItem: PizzaModel<BasePizza>,
                newItem: PizzaModel<BasePizza>
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
        val onItemClicked: (movieModel: PizzaModel<BasePizza>,position : Int) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {

        fun onBind(movieModel: PizzaModel<BasePizza>) {
            item.root.setOnClickListener {
                onItemClicked(movieModel,layoutPosition)

            }
            item.ivCover.loadDrawableImage(movieModel.result.drawableId() )
            item.tvTitle.text = movieModel.result.content()
           item.tvDescription.text = movieModel.result.price().toString()+"$"
          // item.tvScore.text = movieModel.popularity.toString()
        }
    }
}