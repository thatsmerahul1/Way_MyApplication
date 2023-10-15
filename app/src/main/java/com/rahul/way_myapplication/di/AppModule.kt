//package com.rahul.way_myapplication.di
//
//import com.rahul.way_myapplication.data.services.ApiClient
//import com.rahul.way_myapplication.repository.Repository
//import com.rahul.way_myapplication.viewmodel.ProductListViewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.dsl.module
//
//val appModule = module {
//    single { ApiClient.apiService }  // Provides ApiService
//    single { Repository(get()) }  // Provides Repository with ApiService
//    viewModel { ProductListViewModel(get()) }  // Provides ProductListViewModel with Repository
//}