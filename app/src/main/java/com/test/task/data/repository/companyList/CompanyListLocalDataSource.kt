package com.test.task.data.repository.companyList

import androidx.lifecycle.LiveData
import com.test.task.data.local.database.CompanyDatabase
import com.test.task.data.local.database.model.CompanyEntity
import javax.inject.Inject

class CompanyListLocalDataSource @Inject constructor(private val database: CompanyDatabase) {
    val companyList: LiveData<List<CompanyEntity>> = database.companyListDao().companyList()

    suspend fun insertCompaniesIntoDatabase(companies: List<CompanyEntity>) {
        if (companies.isNotEmpty()) {
            database.companyListDao().insertCompanyList(companies)
        }
    }
}