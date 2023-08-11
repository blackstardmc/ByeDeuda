package com.blackneko.byedeuda.ui.initPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackneko.byedeuda.data.DeudaRepository
import com.blackneko.byedeuda.data.model.Deuda
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class mainViewModel @Inject constructor(private val repository: DeudaRepository) :ViewModel(){
    private val _listDeuda= MutableLiveData<List<Deuda>>()
    val listDeuda:LiveData<List<Deuda>> = _listDeuda


    fun addDeuda(deuda: Deuda){
        viewModelScope.launch {
            repository.insertDeuda(deuda)
        }
        actualizarLiveData()
    }

    fun actualizarLiveData(){

        viewModelScope.launch{
            _listDeuda.postValue(repository.getAllDeudaDatabase())
        }
    }







}