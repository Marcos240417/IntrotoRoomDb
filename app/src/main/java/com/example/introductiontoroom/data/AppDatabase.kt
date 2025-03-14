package com.example.introductiontoroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.introductiontoroom.data.model.PersonEntity
import com.example.introductiontoroom.ui_compose.repositorys.response.AddressDao
import com.example.introductiontoroom.ui_compose.repositorys.response.Registrationform

@Database(version = 2, entities = [PersonEntity::class, Registrationform::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun addresDao(): AddressDao // Novo DAO para Registrationform

    companion object {

        @Volatile
        private var INSTACE: AppDatabase? = null

        // Definição da migração de versão 1 para 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Exemplo: Adicionar uma nova coluna caso necessário
                // database.execSQL("ALTER TABLE PersonEntity ADD COLUMN new_column TEXT DEFAULT '' NOT NULL")
            }
        }

        fun getDatabase(context: Context): AppDatabase {

            return INSTACE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "PersonDatabase"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    INSTACE = instance
                    return instance
            }
        }
    }
}