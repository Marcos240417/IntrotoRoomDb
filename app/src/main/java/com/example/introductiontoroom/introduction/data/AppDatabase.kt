package com.example.introductiontoroom.introduction.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.introductiontoroom.introduction.data.model.PersonEntity

@Database(version = 5, entities = [PersonEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Recupera informações sobre as colunas existentes na tabela
                val cursor = db.query("PRAGMA table_info(person_table)")
                val existingColumns = mutableListOf<String>()
                while (cursor.moveToNext()) {
                    val columnName = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    existingColumns.add(columnName)
                }
                cursor.close()

                // Adiciona a coluna apenas se ela não existir
                if (!existingColumns.contains("person_email")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_email TEXT")
                }
                if (!existingColumns.contains("person_logradouro")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_logradouro TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_identity_cpf")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_identity_cpf TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_number")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_number TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_phone")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_phone TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_cidade")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_cidade TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_bairro")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_bairro TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_estado")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_estado TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_sexo")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_sexo TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_marital_status")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_marital_status TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_nationality")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_nationality TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_cep")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_cep TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_date_birth")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_date_birth TEXT NOT NULL DEFAULT ''")
                }
                if (!existingColumns.contains("person_identity_rg")) {
                    db.execSQL("ALTER TABLE person_table ADD COLUMN person_identity_rg TEXT NOT NULL DEFAULT ''")
                }
            }
        }


        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "PersonDatabase"
                )
                    .addMigrations(MIGRATION_4_5)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
