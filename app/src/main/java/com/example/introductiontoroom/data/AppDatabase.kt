package com.example.introductiontoroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.introductiontoroom.data.model.PersonEntity

@Database(version = 1, entities = [PersonEntity::class], exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object{

        @Volatile
        private var INSTACE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTACE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                "PersonDatabase").build()
                INSTACE = instance
                return instance
            }
        }
    }
}