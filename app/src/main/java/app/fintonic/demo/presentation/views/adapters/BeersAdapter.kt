package app.fintonic.demo.presentation.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.fintonic.demo.R
import app.fintonic.demo.databinding.ItemViewBeerBinding
import app.fintonic.demo.databinding.ItemViewLoaderBinding
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.presentation.views.adapters.base.BaseAdapter
import app.fintonic.demo.presentation.views.adapters.base.BaseViewHolder

class BeersAdapter : BaseAdapter<BeerDto, ViewDataBinding>() {

    companion object{
        const val ITEM = 0
        const val LOADER = 1
    }

    var showLoader : Boolean = false
        set(value) {
            if(value && !field)
                addLoaderItem()
            else if(field && !value)
                removeLoaderItem()
            field = value
        }

    override fun getItemViewType(position: Int): Int {
        return items!![position].type
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): BaseViewHolder<BeerDto, ViewDataBinding> {
        return when (viewType){
            ITEM ->{
                BeerItemHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_view_beer, parent, false
                    )
                ) as BaseViewHolder<BeerDto, ViewDataBinding>
            }
            else ->{
                BeerItemLoaderHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_view_loader, parent, false
                    )
                ) as BaseViewHolder<BeerDto, ViewDataBinding>
            }
        }
    }

    private fun addLoaderItem(){
        items?.add(BeerDto(type = LOADER))
        notifyItemChanged(items?.size!! - 1)
    }

    private fun removeLoaderItem(){
        val item = items?.find { it.type == LOADER }
        val indexOf = items?.indexOf(item)
        indexOf?.let { if(it > 0){
            items?.removeAt(indexOf)
            notifyItemChanged(it)
        }}
    }

    class BeerItemHolder(binder: ItemViewBeerBinding) :
        BaseViewHolder<BeerDto, ItemViewBeerBinding>(binder){
        override fun onBindViewHolder(item: BeerDto) {
            super.onBindViewHolder(item)
            this.binder.beer = item
        }
    }

    class BeerItemLoaderHolder(binder: ItemViewLoaderBinding) :
        BaseViewHolder<BeerDto, ItemViewLoaderBinding>(binder){
        override fun onBindViewHolder(item: BeerDto) {
            super.onBindViewHolder(item)
            this.binder.progressBar.visibility = View.VISIBLE
        }
    }
}