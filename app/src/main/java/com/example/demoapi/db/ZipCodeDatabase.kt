package com.example.demoapi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demoapi.DataModel.Zipcode


@Database(entities = [Zipcode::class], version = 2)
abstract class  ZipCodeDatabase:RoomDatabase() {

  abstract fun zipcodeDao():ZipcodeDao

}