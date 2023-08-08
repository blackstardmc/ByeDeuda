package com.blackneko.byedeuda.data.dao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blackneko.byedeuda.data.dao.DaoDeuda
import com.blackneko.byedeuda.data.dao.entity.DeudaEntity

@Database(entities = [DeudaEntity::class], version = 1)
abstract class DeudaDatabase:RoomDatabase(){
    abstract fun getDaoDeuda():DaoDeuda
}