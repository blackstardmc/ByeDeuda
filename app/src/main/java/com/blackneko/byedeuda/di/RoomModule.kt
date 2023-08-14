package com.blackneko.byedeuda.di

import android.content.Context
import androidx.room.Room
import com.blackneko.byedeuda.data.dao.db.DeudaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DEUDA_DATABASE_NAME = "deuda_database"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context):DeudaDatabase {
        return  Room.databaseBuilder(
            context,
            DeudaDatabase::class.java, DEUDA_DATABASE_NAME
        ).build()
    }
    @Singleton
    @Provides
    fun providesDeudaDao(db: DeudaDatabase) = db.getDaoDeuda()



}