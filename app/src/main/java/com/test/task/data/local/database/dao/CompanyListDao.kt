package com.test.task.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.test.task.data.local.database.model.CompanyEntity

@Dao
interface CompanyListDao {

    @Query("SELECT * FROM company_list")
    fun companyList(): LiveData<List<CompanyEntity>>

    @Query("SELECT * FROM company_list WHERE id = :id")
    suspend fun companyById(id: String): CompanyEntity?

    @Query("SELECT * FROM company_list WHERE id = :id")
    fun companyLiveDataById(id: String): LiveData<CompanyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyList(list: List<CompanyEntity>)

    @Update
    suspend fun updateCompanyEntity(data: CompanyEntity): Int

    @Query("DELETE FROM company_list")
    suspend fun deleteAll()
}