package com.test.task.api

import com.test.task.api.model.Company
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/test_task/test.php")
    suspend fun companyList(): Response<List<Company>>

    @GET("/test_task/test.php")
    suspend fun companyById(
        @Query("id") id: String
    ): Response<List<Company>>
}