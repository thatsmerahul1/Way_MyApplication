package com.rahul.way_myapplication.repository

import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.data.services.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun fetchProductList(): Result<ProductListDataClass> {
        return try {
            val response = apiService.getProductList()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(RuntimeException("Response error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
