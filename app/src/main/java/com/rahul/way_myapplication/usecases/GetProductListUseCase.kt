package com.rahul.way_myapplication.usecases

import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.repository.Repository

class GetProductListUseCase (private val repository: Repository) {
    suspend fun execute(): Result<ProductListDataClass> {
        return repository.fetchProductList()
    }
}
