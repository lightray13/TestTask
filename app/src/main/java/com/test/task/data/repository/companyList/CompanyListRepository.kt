package com.test.task.data.repository.companyList

import javax.inject.Inject
import com.test.task.api.Result
import com.test.task.data.local.database.model.CompanyEntity
import com.test.task.util.Constants

class CompanyListRepository @Inject constructor(
    private val companyListRemoteDataSource: CompanyListRemoteDataSource,
    private val companyListLocalDataSource: CompanyListLocalDataSource
) {
    val companyList = companyListLocalDataSource.companyList

    suspend fun loadCompanyList() {
        when (val result = companyListRemoteDataSource.companyList()) {
            is Result.Success -> {
                val companyList = result.data.let {
                    it.filter { item -> item.id.isNullOrEmpty().not() }
                        .map { company ->
                            CompanyEntity(
                                company.id ?: "",
                                company.name,
                                company.img,
                                company.description,
                                company.lat,
                                company.lon,
                                company.www,
                                company.phone
                            )
                        }
                }
                companyListLocalDataSource.insertCompaniesIntoDatabase(companyList)
                Result.Success(true)
            }
            else -> Result.Error(Constants.GENERIC_ERROR)
        }
    }
}