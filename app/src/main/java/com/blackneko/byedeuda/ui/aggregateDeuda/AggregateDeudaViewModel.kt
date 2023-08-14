package com.blackneko.byedeuda.ui.aggregateDeuda

import androidx.core.text.isDigitsOnly
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
class AggregateDeudaViewModel @Inject constructor(private val repository: DeudaRepository) :
    ViewModel() {
    private val constantDate: String = "SIN FECHA"

    private var _isCorrect = MutableStateFlow(false)
    val isCorrect: StateFlow<Boolean> = _isCorrect

    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private var _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private var _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount

    private var _date = MutableStateFlow(constantDate)
    val date: StateFlow<String> = _date

    private var _switchState = MutableStateFlow(false)
    val switchState: StateFlow<Boolean> = _switchState

    fun setDate(mDay: Int, mMonth: Int, mYear: Int) {
        _date.value = "$mDay/${mMonth + 1}/$mYear"
        stateCorrect()
    }

    fun setName(name: String) {
        _name.value = name
        stateCorrect()
    }


    fun setDescription(description: String) {
        _description.value = description
        stateCorrect()
    }

    fun setAmount(amount: String) {
        _amount.value = amount
        stateCorrect()
    }

    fun switchChangeState() {
        _switchState.value = !switchState.value
    }

    fun addDeuda() {
        viewModelScope.launch {
            if (_isCorrect.value) {
                repository.insertDeuda(
                    Deuda(
                        _name.value,
                        _description.value,
                        _amount.value.toInt(),
                        _switchState.value,
                        _date.value
                    )
                )
                clearData()
            }
        }

    }

    fun stateCorrect() {
        _isCorrect.value =
            !name.value.isEmpty() && !description.value.isEmpty() && amount.value.isDigitsOnly() && date.value != constantDate
    }

    fun clearData() {
        _name.value = ""
        _description.value = ""
        _amount.value = ""
        _date.value = constantDate
        _isCorrect.value = false
    }
}