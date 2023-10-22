package com.rahul.way_myapplication.data.services

import com.rahul.way_myapplication.annotations.AuthenticationAnnotation
import com.rahul.way_myapplication.data.model.ProductListDataClass
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @AuthenticationAnnotation("ApiService")
    @GET("products.v1.json")
    suspend fun getProductList(): Response<ProductListDataClass>

}
