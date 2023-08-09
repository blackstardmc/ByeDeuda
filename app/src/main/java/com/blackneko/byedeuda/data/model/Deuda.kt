package com.blackneko.byedeuda.data.model

import com.blackneko.byedeuda.data.dao.entity.DeudaEntity

data class Deuda(
    val name: String,
    val description: String,
    var amount: Int,
    val type: Boolean,
    val date: String
)

fun DeudaEntity.toDomain() = Deuda(name, description, amount, type, date)
