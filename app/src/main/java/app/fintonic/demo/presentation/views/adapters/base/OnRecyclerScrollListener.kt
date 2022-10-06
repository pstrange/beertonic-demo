package app.fintonic.demo.presentation.views.adapters.base

import androidx.recyclerview.widget.RecyclerView

abstract class OnRecyclerScrollListener : RecyclerView.OnScrollListener(){

    var nextPage: Int = 2
    var isFinalPage: Boolean = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if(!isFinalPage){
            if (!recyclerView.canScrollVertically(1)) {
                loadMoreItems(nextPage)
            }
        }
    }

    fun incrementPage(){
        nextPage++
    }

    fun decrementPage(){
        nextPage--
    }

    fun resetPage(){
        nextPage = 2
        isFinalPage = false
    }

    abstract fun loadMoreItems(nextPage: Int)
}