package com.example.pestotodoapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pestotodoapplication.data.models.ToDoData

/**
 * author lgarg on 4/23/24.
 */
@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}