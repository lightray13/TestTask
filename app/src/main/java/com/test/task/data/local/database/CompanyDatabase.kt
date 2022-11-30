package com.test.task.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.task.data.local.database.dao.CompanyListDao
import com.test.task.data.local.database.model.CompanyEntity

@Database(entities = [CompanyEntity::class], version = 1, exportSchema = false)
abstract class CompanyDatabase: RoomDatabase() {
    abstract fun companyListDao(): CompanyListDao

    companion object {
        fun buildDatabase(context: Context): CompanyDatabase {
            return Room.databaseBuilder(context, CompanyDatabase::class.java, "Companies").build()
        }
    }
}