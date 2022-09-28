package com.example.demoapi.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideZipcodeDao(appDatabase:ZipCodeDatabase):ZipcodeDao{
        return appDatabase.zipcodeDao()
    }

    @Provides
    @Singleton
    fun provideZipcodeDatabase(@ApplicationContext context: Context):ZipCodeDatabase{
        return Room.databaseBuilder(
            context,
            ZipCodeDatabase::class.java,
            "ZipcodeDb"
        ).build()
    }

}