package com.example.demoapi.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zipcode")
data class Zipcode(
    @PrimaryKey(autoGenerate = true)
    val dbint:Int,
    val id: Int,
    val zipcode: Int
)