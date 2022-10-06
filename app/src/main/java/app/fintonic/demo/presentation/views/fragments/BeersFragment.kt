package app.fintonic.demo.presentation.views.fragments

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.fintonic.demo.R
import app.fintonic.demo.databinding.FragmentBeersBinding
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.presentation.utils.ProcessStep
import app.fintonic.demo.presentation.utils.convertOffsetToPercent
import app.fintonic.demo.presentation.utils.toDp
import app.fintonic.demo.presentation.viewmodel.BeersViewModel
import app.fintonic.demo.presentation.views.adapters.BeersAdapter
import app.fintonic.demo.presentation.views.adapters.base.OnRecyclerItemClick
import app.fintonic.demo.presentation.views.adapters.base.OnRecyclerScrollListener
import app.fintonic.demo.presentation.views.base.BaseFragment
import app.fintonic.demo.presentation.views.dialogs.BeerDetailDialogFragment
import com.google.android.material.appbar.AppBarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeersFragment : BaseFragment<FragmentBeersBinding>(R.layout.fragment_beers),
    OnRecyclerItemClick<BeerDto>, AppBarLayout.OnOffsetChangedListener {

    private val viewModel: BeersViewModel by viewModel()
    private var scrollRange = -1
    private var collapsedPercent : Float = -1f

    override fun setupView() {
        viewModel.stepData.observe(this, ::stepObserver)
        viewModel.nextData.observe(this, ::nextObserver)
        viewModel.beersData.observe(this, ::beersObserver)

        binding.adapter = BeersAdapter()
        binding.adapter?.onRecyclerItemClick = this

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = binding.adapter
        binding.recyclerView.addOnScrollListener(onScrollListener)

        binding.appBarLayout.addOnOffsetChangedListener(this)

        viewModel.getBeersList("1")
    }

    override fun onItemClick(view: View?, item: BeerDto?, position: Int) {
        val fragment = BeerDetailDialogFragment.newInstance(beerId = item?.id)
        fragment.show(childFragmentManager, BeerDetailDialogFragment.TAG)
    }

    override fun onOffsetChanged(appBar: AppBarLayout?, verticalOffset: Int) {
        val actualPercent = convertOffsetToPercent(verticalOffset, 0)
        if(collapsedPercent != actualPercent){
            collapsedPercent = actualPercent
            binding.isCollapsed = changeAlphaValOfTitle(verticalOffset) > 0
        }
    }

    private fun changeAlphaValOfTitle(verticalOffset: Int) : Float{
        val skipValue = (binding.collapsingToolbarLayout.height -
                binding.viewTitle.measuredHeight) - 30.toDp
        val alphaVal = convertOffsetToPercent(verticalOffset, skipValue)
        binding.textTitleAutoscroll.alpha = alphaVal
        return alphaVal
    }

    private fun convertOffsetToPercent(verticalOffset: Int, skipValue: Int) : Float{
        if (scrollRange == -1)
            scrollRange = binding.appBarLayout.totalScrollRange
        return verticalOffset.convertOffsetToPercent(scrollRange, verticalOffset, skipValue)
    }

    private fun stepObserver(step: ProcessStep?){
        when (step) {
            is ProcessStep.Loading -> binding.layoutLoader.visibility = View.VISIBLE
            else -> binding.layoutLoader.visibility = View.GONE
        }
    }

    private fun nextObserver(step: ProcessStep?){
        when (step) {
            is ProcessStep.Loading -> binding.adapter?.showLoader = true
            else -> binding.adapter?.showLoader = false
        }
    }

    private fun beersObserver(beers: List<BeerDto>?){
        beers?.let {
            if(binding.adapter?.items.isNullOrEmpty())
                binding.adapter?.items = ArrayList(it)
            else
                binding.adapter?.items?.addAll(ArrayList(it))

            viewModel.cleanData()
        }
    }

    private val onScrollListener = object : OnRecyclerScrollListener(){
        override fun loadMoreItems(nextPage: Int) {
            if(binding.adapter?.showLoader == false){
                binding.adapter?.showLoader = true
                viewModel.getNextBeersList(nextPage.toString())
                incrementPage()
            }
        }
    }
}