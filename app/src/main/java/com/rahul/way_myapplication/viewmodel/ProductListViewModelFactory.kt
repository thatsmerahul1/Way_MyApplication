package com.rahul.way_myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahul.way_myapplication.constants.AppConstants
import com.rahul.way_myapplication.repository.Repository

class ProductListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(repository) as T
        }
        throw IllegalArgumentException(AppConstants.TAG_UNKONWN_VIEWMODEL_CLASS)
    }
}