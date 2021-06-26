package com.example.whattowatch.ui.films

import androidx.core.view.get
import androidx.core.view.size
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

        /*if (isLoading){
            if (totalItemCount > lastTotalItemCount){
                isLoading = false;
                lastTotalItemCount = totalItemCount + 1
            }
        }*/

        if (!isLoading && mLayoutManager.findLastCompletelyVisibleItemPosition() == totalItemCount - 1) {
            onLoadMore()
            isLoading = true
        }

    }

    fun setLoaded(){
        isLoading = false
    }

    abstract fun onLoadMore()
}