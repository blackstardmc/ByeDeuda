package com.blackneko.byedeuda.data

import com.blackneko.byedeuda.data.dao.DaoDeuda
import com.blackneko.byedeuda.data.dao.entity.DeudaEntity
import com.blackneko.byedeuda.data.dao.entity.toDatabase
import com.blackneko.byedeuda.data.model.Deuda
import com.blackneko.byedeuda.data.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class DeudaRepository @Inject constructor(private val deudaDao: DaoDeuda) {
    suspend fun getAllDeudaDatabase(): List<Deuda> {
        val response: List<DeudaEntity> =deudaDao.getAllDeuda()
        return response.map { it.toDomain() }
    }

    suspend fun insertDeuda(deuda:Deuda){
        deudaDao.insertDeuda(deuda.toDatabase())
    }


}
