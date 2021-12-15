package com.sample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GymsTable::class,ClassesTable::class,ActivitiesTable::class], version =1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {


    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java, DatabaseName
            )
                // .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build()
    }


    abstract fun commomDao(): CommomDao
}