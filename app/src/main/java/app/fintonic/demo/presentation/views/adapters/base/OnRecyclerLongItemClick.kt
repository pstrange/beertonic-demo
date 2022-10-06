package app.fintonic.demo.presentation.views.adapters.base

import android.view.View

interface OnRecyclerLongItemClick<T> {
    fun onLongItemClick(view: View?, item: T?, position: Int) : Boolean
}