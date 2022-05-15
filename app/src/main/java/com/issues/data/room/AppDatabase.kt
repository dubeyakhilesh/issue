package com.issues.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Issue::class, ImageTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun IssueDao(): IssueDao
    abstract fun ImageDao(): ImageDao

    companion object {
        private var dbInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "issue_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance!!
        }
    }
}