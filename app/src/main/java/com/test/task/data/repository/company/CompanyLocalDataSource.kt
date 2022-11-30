package com.test.task.data.repository.company

import com.test.task.data.local.database.CompanyDatabase
import com.test.task.data.local.database.model.CompanyEntity
import javax.inject.Inject

class CompanyLocalDataSource @Inject constructor(private val database: CompanyDatabase) {
    fun companyById(id: String) = database.companyListDao().companyLiveDataById(id)

    suspend fun updateCompanyIntoDatabase(company: CompanyEntity) {
        database.companyListDao().updateCompanyEntity(company)
    }
}