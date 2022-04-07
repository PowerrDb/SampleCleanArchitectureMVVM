package com.bestpractises.razisample.util.extension

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.pagination(totalPages : Int?,isloading :()->Boolean,loadNextPage : (page : Int)->Unit) {
    var page = 1


    addOnScrollListener(object  : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager!!.childCount
            val totalItemCount = layoutManager!!.itemCount
            val firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (totalPages != null) {
                if (!isloading()  &&  totalPages>page) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        page +=1
                        loadNextPage(page)
                    }
                }
            }
        }
    })

}


