package com.example.introductiontoroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val pId: Int,
    @ColumnInfo("person_name") val name: String,
    @ColumnInfo("person_age") val dateBirth: Int,
    @ColumnInfo("person_city") val city: Int
)


