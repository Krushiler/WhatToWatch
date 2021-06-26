package com.example.whattowatch.ui.films

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class LoadScrollListener : RecyclerView.OnScrollListener {
    var isLoading: Boolean = false
    var totalItemCount: Int = 0
    var lastTotalItemCount: Int = 0
    var mLayoutManager: LinearLayoutManager

    constructor(layoutManager:LinearLayoutManager) {
        this.mLayoutManager = layoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        totalItemCount = mLayoutManager.itemCount

        if (isLoading){
            if (totalItemCount > lastTotalItemCount){
                isLoading = false;
                lastTotalItemCount = totalItemCount
            }
        }

        if (!isLoading && mLayoutManager.findLastCompletelyVisibleItemPosition() == totalItemCount - 1) {
            onLoadMore()
            isLoading = true
        }

    }

    abstract fun onLoadMore()
}