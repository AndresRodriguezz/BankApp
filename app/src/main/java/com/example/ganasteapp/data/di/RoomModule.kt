package com.example.ganasteapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.ganasteapp.data.database.BankDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val BANK_DATABASE_NAME = "bank_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BankDataBase::class.java, BANK_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBankDao(db: BankDataBase) = db.getBankDao()
}
