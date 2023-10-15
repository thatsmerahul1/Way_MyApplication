package com.rahul.way_myapplication

import com.rahul.way_myapplication.viewmodel.ProductListViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.data.services.ApiService
import com.rahul.way_myapplication.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val repository = Repository(apiService)
//        viewModel = ProductListViewModel()
    }

    @Test
    fun `getProductList_updates_productList`() = runBlockingTest {
        // Given
        val expectedProductList = ProductListDataClass()
        Mockito.`when`(apiService.getProductList()).thenReturn(expectedProductList)

        // When
//        viewModel.getProductList()

        // Then
        assert(viewModel.productList.value == expectedProductList)
    }
}

