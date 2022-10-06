package app.fintonic.demo.presentation.views.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import app.fintonic.demo.R
import app.fintonic.demo.databinding.FragmentFavBinding
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.presentation.viewmodel.FavoritesViewModel
import app.fintonic.demo.presentation.views.adapters.BeersAdapter
import app.fintonic.demo.presentation.views.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavFragment : BaseFragment<FragmentFavBinding>(R.layout.fragment_fav) {

    private val viewModel: FavoritesViewModel by viewModel()

    override fun setupView() {
        binding.adapter = BeersAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = binding.adapter

        viewModel.beerListFavData.observe(this, ::beersObserver)
        viewModel.getFavBeers()
    }

    private fun beersObserver(beers: List<BeerDto>){
        binding.isEmpty = beers.isEmpty()
        binding.adapter?.items = ArrayList(beers)
    }
}