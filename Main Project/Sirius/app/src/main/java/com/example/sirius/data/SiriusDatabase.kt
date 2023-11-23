package com.example.sirius.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sirius.data.dao.AnimalDao
import com.example.sirius.data.dao.NewsDao
import com.example.sirius.data.dao.UserDao
import com.example.sirius.model.Animal
import com.example.sirius.model.News
import com.example.sirius.model.User

@Database(entities = [Animal::class, News::class, User::class], version = 4, exportSchema = false)
abstract class SiriusDatabase: RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun newsDao(): NewsDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: SiriusDatabase? = null
        private val database_path: String = "database/Sirius.db"

        fun getDatabase(context: Context): SiriusDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    SiriusDatabase::class.java,
                    "app_database"
                )
                    // Base de datos de inicialización
                    .createFromAsset(database_path)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}