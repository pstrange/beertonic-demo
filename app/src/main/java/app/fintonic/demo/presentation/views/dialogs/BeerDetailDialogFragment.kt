package app.fintonic.demo.presentation.views.dialogs

import android.os.Bundle
import app.fintonic.demo.R
import app.fintonic.demo.databinding.DialogFragmentBeerDetailBinding
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.presentation.viewmodel.BeerDetailViewModel
import app.fintonic.demo.presentation.views.base.BaseBottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerDetailDialogFragment : BaseBottomSheetDialogFragment<DialogFragmentBeerDetailBinding>(R.layout.dialog_fragment_beer_detail) {

    companion object {
        const val TAG = "BeerDetailDialogFragment"
        private const val ARGS_BEER_ID = "ARGS_BEER_ID"
        private const val ARGS_BEER_NAME = "ARGS_BEER_NAME"
        private const val ARGS_BEER_TAG = "ARGS_BEER_TAG"
        private const val ARGS_BEER_IMG = "ARGS_BEER_IMG"

        @JvmStatic
        fun newInstance(
            beerId: String? = null,
            name: String? = null,
            tag: String? = null,
            image: String? = null
        ): BeerDetailDialogFragment {
            val fragment = BeerDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGS_BEER_ID, beerId)
                    putString(ARGS_BEER_NAME, name)
                    putString(ARGS_BEER_TAG, tag)
                    putString(ARGS_BEER_IMG, image)
                }
            }
            return fragment
        }
    }

    private val viewModel: BeerDetailViewModel by viewModel()

    override fun setupView() {
        binding.buttonClose.setOnClickListener { dismiss() }
        binding.imageFav.setOnClickListener {
            binding.beer?.let { beer ->
                if(binding.isFav==true) viewModel.deleteBeerFromFav(beer.id)
                else viewModel.saveBeerToFav(beer)
            }
        }

        viewModel.beerFavSaveData.observe(this, ::addBeerFavObserver)
        viewModel.beerFavDeleteData.observe(this, ::deleteBeerFavObserver)
        viewModel.beerFavGetData.observe(this, ::getBeerFavObserver)
        viewModel.beerData.observe(this, ::beerObserver)

        val beerId = arguments?.getString(ARGS_BEER_ID) ?: ""
            viewModel.getBeerFav(beerId)
            viewModel.getBeerDetail(beerId)
    }

    private fun addBeerFavObserver(beer: Boolean){
        binding.isFav = true
    }

    private fun deleteBeerFavObserver(beer: Boolean){
        binding.isFav = false
    }

    private fun getBeerFavObserver(beer: BeerDto?){
        beer?.let {
            binding.isFav = true
        }
    }

    private fun beerObserver(beers: List<BeerDto>?){
        beers?.first()?.let {
            binding.beer = it
        }
    }
}