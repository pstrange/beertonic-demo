package app.fintonic.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.usecases.DbDeleteBeerUseCase
import app.fintonic.demo.domain.usecases.DbGetBeerUseCase
import app.fintonic.demo.domain.usecases.DbSaveBeerUseCase
import app.fintonic.demo.domain.usecases.GetBeerDetailUseCase
import app.fintonic.demo.presentation.utils.ProcessStep
import kotlinx.coroutines.launch

class BeerDetailViewModel(
    private val getBeerDetailUseCase: GetBeerDetailUseCase,
    private val dbSaveBeerUseCase: DbSaveBeerUseCase,
    private val dbGetBeerUseCase: DbGetBeerUseCase,
    private val dbDeleteBeerUseCase: DbDeleteBeerUseCase
) : SimpleProcessViewModel() {

    private val _beer = MutableLiveData<List<BeerDto>>()
    val beerData: LiveData<List<BeerDto>> get() = _beer

    fun getBeerDetail(beerId: String) = viewModelScope.launch {
        _step.value = ProcessStep.Loading()
        when (val result = getBeerDetailUseCase(GetBeerDetailUseCase.Params(beerId))) {
            is Outcome.Error ->
                _step.value = ProcessStep.Error(result.message)
            is Outcome.Success -> {
                _step.value = ProcessStep.Finished
                _beer.value = result.value
            }
            else -> Unit
        }
    }

    private val _beerFavSave = MutableLiveData<Boolean>()
    val beerFavSaveData: LiveData<Boolean> get() = _beerFavSave

    fun saveBeerToFav(beer: BeerDto) = viewModelScope.launch {
        when (val result = dbSaveBeerUseCase(beer)) {
            is Outcome.Success -> {
                _beerFavSave.value = result.value
            }
            else -> Unit
        }
    }

    private val _beerFavDelete = MutableLiveData<Boolean>()
    val beerFavDeleteData: LiveData<Boolean> get() = _beerFavDelete

    fun deleteBeerFromFav(beerId: String) = viewModelScope.launch {
        when (val result = dbDeleteBeerUseCase(beerId)) {
            is Outcome.Success -> {
                _beerFavDelete.value = result.value
            }
            else -> Unit
        }
    }

    private val _beerFavGet = MutableLiveData<BeerDto>()
    val beerFavGetData: LiveData<BeerDto> get() = _beerFavGet

    fun getBeerFav(beerId: String) = viewModelScope.launch {
        when (val result = dbGetBeerUseCase(beerId)) {
            is Outcome.Success -> {
                _beerFavGet.value = result.value
            }
            else -> Unit
        }
    }

}