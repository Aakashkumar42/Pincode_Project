package com.example.demoapi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.demoapi.DataModel.Zipcode

@Dao
interface ZipcodeDao {

    @Query("select * from zipcode")
    suspend fun getzipcode():List<Zipcode>

    @Insert()
    suspend fun insertZipCode(zipcode: List<Zipcode>)

}