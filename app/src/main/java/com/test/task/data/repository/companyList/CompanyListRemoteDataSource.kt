package com.test.task.data.repository.companyList

import com.test.task.api.ApiInterface
import com.test.task.api.Result
import com.test.task.api.model.Company
import com.test.task.util.Constants
import javax.inject.Inject

class CompanyListRemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun companyList(): Result<List<Company>> {
        try {
            val response = service.companyList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return Result.Error(Constants.GENERIC_ERROR)
        } catch (e: Exception) {
            return Result.Error(e.message ?: e.toString())
        }
    }
}