package com.blackneko.byedeuda.ui.initPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackneko.byedeuda.data.DeudaRepository
import com.blackneko.byedeuda.data.model.Deuda
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DeudaRepository) : ViewModel() {


    private var _aggregateState = MutableStateFlow(false)
    val aggregateState: StateFlow<Boolean> = _aggregateState

    private var _listDeuda = MutableStateFlow<List<Deuda>>(listOf())
    val listDeuda: StateFlow<List<Deuda>> = _listDeuda


    fun changeAggregateState() {
        _aggregateState.value = !_aggregateState.value
    }


    fun actualizarLiveData() {

        viewModelScope.launch {
            _listDeuda.value = (repository.getAllDeudaDatabase())
        }
    }

}