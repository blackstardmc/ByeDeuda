package com.blackneko.byedeuda.ui.initPage

import androidx.lifecycle.ViewModel
import com.blackneko.byedeuda.data.DeudaRepository
import com.blackneko.byedeuda.data.model.Deuda
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



class mainViewModel @Inject constructor(private val deudaRepository: DeudaRepository) {

    suspend fun addDeuda(d:Deuda){
        deudaRepository.insertDeuda(d)
    }
}