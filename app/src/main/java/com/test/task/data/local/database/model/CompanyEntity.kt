package com.test.task.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_list")
data class CompanyEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val img: String?,
    val description: String?,
    val lat: Double?,
    val lon: Double?,
    val www: String?,
    val phone: String?
)