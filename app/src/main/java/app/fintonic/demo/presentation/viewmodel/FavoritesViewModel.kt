package app.fintonic.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.usecases.DbGetBeersListUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val dbGetBeersListUseCase: DbGetBeersListUseCase
) : SimpleProcessViewModel() {

    private val _beerListFav = MutableLiveData<List<BeerDto>>()
    val beerListFavData: LiveData<List<BeerDto>> get() = _beerListFav

    fun getFavBeers() = viewModelScope.launch {
        when (val result = dbGetBeersListUseCase()) {
            is Outcome.Success -> {
                _beerListFav.value = result.value
            }
            else -> Unit
        }
    }
}