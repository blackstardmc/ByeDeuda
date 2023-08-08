package com.blackneko.byedeuda.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackneko.byedeuda.data.dao.entity.DeudaEntity

@Dao

interface DaoDeuda{
    @Query("SELECT * FROM deuda_table ORDER BY name DESC")
    suspend fun getAllDeuda():List<DeudaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeuda(deuda:DeudaEntity)
}
