package com.blackneko.byedeuda.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackneko.byedeuda.data.model.Deuda

@Entity(tableName = "deuda_table")
data class DeudaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "amount") val amount: Int,
    //True(Deuda) False(Prestamo)
    @ColumnInfo(name = "type") val type: Boolean,
    @ColumnInfo(name="date") val date:String
)

fun Deuda.toDatabase() =
    DeudaEntity(
        name = name,
        type = type,
        description = description,
        amount = amount,
        date = date
    )