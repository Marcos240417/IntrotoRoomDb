package com.example.introductiontoroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val pId: Int,
    @ColumnInfo("person_name") val name: String,
    @ColumnInfo("person_date_birth") val dateBirth:String,
    @ColumnInfo("person_nsus") val nsus: String
)


