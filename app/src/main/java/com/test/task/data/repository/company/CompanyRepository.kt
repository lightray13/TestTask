package com.test.task.data.repository.company

import javax.inject.Inject
import com.test.task.api.Result
import com.test.task.data.local.database.model.CompanyEntity
import com.test.task.util.Constants

class CompanyRepository @Inject constructor(
    private val companyRemoteDataSource: CompanyRemoteDataSource,
    private val companyLocalDataSource: CompanyLocalDataSource
){
    fun companyById(id: String) = companyLocalDataSource.companyById(id)

    suspend fun loadCompanyById(id: String) {
        when(val result = companyRemoteDataSource.companyById(id)) {
            is Result.Success -> {
                val companyData = result.data.let {
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
                companyLocalDataSource.updateCompanyIntoDatabase(companyData[0])
                Result.Success(true)
            }
            else -> Result.Error(Constants.GENERIC_ERROR)
        }
    }
}