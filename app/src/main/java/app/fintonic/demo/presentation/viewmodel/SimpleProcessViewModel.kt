package app.fintonic.demo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.fintonic.demo.presentation.utils.ProcessStep
import kotlinx.coroutines.cancelChildren

open class SimpleProcessViewModel: ViewModel() {

    @Suppress("PropertyName")
    protected val _step = MutableLiveData<ProcessStep?>()
    val stepData: LiveData<ProcessStep?> get() = _step

    protected fun process(onLoading: () -> Unit) {
        _step.value = ProcessStep.Loading()
        onLoading()
        _step.value = null
    }

    override fun onCleared() {
        viewModelScope.coroutineContext.cancelChildren()
    }
}