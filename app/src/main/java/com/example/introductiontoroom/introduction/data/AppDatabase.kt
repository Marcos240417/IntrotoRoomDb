package com.example.introductiontoroom.introduction.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.model.AddressDao

@Database(version = 4, entities = [PersonEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun addressDao(): AddressDao

    companion object {

        @Volatile
        private var INSTACE: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Adicionando as colunas que estavam faltando
                // Adicione as colunas que estão faltando
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_email TEXT")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_logradouro TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_identity_cpf TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_number TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_phone TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_cidade TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_bairro TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_estado TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_sexo TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_marital_status TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_nationality TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_cep TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_date_birth TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE person_table ADD COLUMN person_identity_rg TEXT NOT NULL DEFAULT ''")

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