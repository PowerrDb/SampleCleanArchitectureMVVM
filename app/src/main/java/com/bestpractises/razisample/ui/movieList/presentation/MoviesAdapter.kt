package com.bestpractises.razisample.ui.movieList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bestpractises.razisample.R


class MoviesAdapter(val onItemClicked: () -> Unit) :
    ListAdapter<Any, MoviesAdapter.MovieListViewHolder>(DIFFER_CALLBACK) {

    companion object {
        val DIFFER_CALLBACK: DiffUtil.ItemCallback<Any> = object :
            DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(
                oldItem: Any,
                newItem: Any
            ): Boolean {
                return /*oldItem.id == newItem.id*/false
            }

            override fun areContentsTheSame(
                oldItem: Any,
                newItem: Any
            ): Boolean {
                return/* oldItem == newItem*/false
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_movie, parent, false)
        return MovieListViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
//        holder.onBind(currentList[position])
    }

    inner class MovieListViewHolder(
        val item: View,
        val onItemClicked: () -> Unit
    ) : RecyclerView.ViewHolder(item) {
        fun onBind(movieModel: Any) {
        }
    }
}