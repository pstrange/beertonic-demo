package app.fintonic.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.fintonic.demo.data.utils.Outcome
import app.fintonic.demo.domain.usecases.CountDownUseCase
import app.fintonic.demo.presentation.utils.ProcessStep
import kotlinx.coroutines.launch

class SplashViewModel(private val countDownUseCase: CountDownUseCase) : SimpleProcessViewModel() {

    private val _view = MutableLiveData<Boolean>()
    val viewData: LiveData<Boolean> get() = _view

    fun startCountDown() = viewModelScope.launch {
        _step.value = ProcessStep.Loading()
        when (val result = countDownUseCase(CountDownUseCase.Params(3000))) {
            is Outcome.Success -> {
                _step.value = ProcessStep.Finished
                _view.value = result.value
            }
            else -> Unit
        }
    }

}