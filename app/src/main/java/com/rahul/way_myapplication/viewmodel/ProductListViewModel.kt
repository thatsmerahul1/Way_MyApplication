package com.rahul.way_myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.repository.Repository
import com.rahul.way_myapplication.usecases.GetProductListUseCase
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: Repository) : ViewModel() {
    private val _productList = MutableLiveData<Result<ProductListDataClass>>()
    val productList: LiveData<Result<ProductListDataClass>> = _productList

    fun getProductList() {
        viewModelScope.launch {
            _productList.value = GetProductListUseCase(repository).execute()
        }
    }

    companion object {
        const val TAG = "ProductListViewModel"
    }
}