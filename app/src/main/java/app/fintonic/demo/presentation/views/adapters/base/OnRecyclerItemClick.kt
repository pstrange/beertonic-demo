package app.fintonic.demo.presentation.views.adapters.base

import android.view.View

interface OnRecyclerItemClick<T> {
    fun onItemClick(view: View?, item: T?, position: Int)
}