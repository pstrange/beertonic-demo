package app.fintonic.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.domain.models.response.BeerDto
import app.fintonic.demo.domain.usecases.GetBeersUseCase
import app.fintonic.demo.presentation.utils.ProcessStep
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BeersViewModel(
    private val getBeersUseCase: GetBeersUseCase) : SimpleProcessViewModel() {

    private val _next = MutableLiveData<ProcessStep?>()
    val nextData: LiveData<ProcessStep?> get() = _next

    private val _beers = MutableLiveData<List<BeerDto>?>()
    val beersData: LiveData<List<BeerDto>?> get() = _beers

    fun getBeersList(page: String) : Job {
        _beers.value = null
        return getBeersPagedList(page, false)
    }

    fun getNextBeersList(page: String) = getBeersPagedList(page, true)

    private fun getBeersPagedList(page: String, isNext: Boolean) = viewModelScope.launch {
        if(isNext)
            _next.value = ProcessStep.Loading()
        else _step.value = ProcessStep.Loading()
        when (val result = getBeersUseCase(GetBeersUseCase.Params(page))) {
            is Outcome.Error -> {
                if(isNext)
                    _next.value = ProcessStep.Error(result.message)
                else _step.value = ProcessStep.Error(result.message)
            }
            is Outcome.Success -> {
                if(isNext)
                    _next.value = ProcessStep.Finished
                else _step.value = ProcessStep.Finished
                _beers.value = result.value
            }
            else -> Unit
        }
    }

    fun cleanData(){
        _beers.value = null
    }
}