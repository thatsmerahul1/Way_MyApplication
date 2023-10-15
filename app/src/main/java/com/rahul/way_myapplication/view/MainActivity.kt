package com.rahul.way_myapplication.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.way_myapplication.R
import com.rahul.way_myapplication.adapter.ProductListAdapter
import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.data.services.ApiClient
import com.rahul.way_myapplication.databinding.ActivityMainBinding
import com.rahul.way_myapplication.repository.Repository
import com.rahul.way_myapplication.viewmodel.ProductListViewModel
import com.rahul.way_myapplication.viewmodel.ProductListViewModelFactory

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var productListViewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productListViewModel = initializeViewModel()

        productListAdapter = ProductListAdapter(this, ProductListDataClass())
        initRecyclerView()

        observeProductList()

        binding.swipeRefreshLayout.setOnRefreshListener {
            onPullToRefresh()
        }
    }

    private fun initializeViewModel(): ProductListViewModel {
        return ViewModelProvider(
            this,
            ProductListViewModelFactory(Repository(ApiClient.apiService))
        )[ProductListViewModel::class.java]
    }

    private fun observeProductList() {
        productListViewModel.productList.observe(this) { result ->
            handleProductListResult(result)
        }
    }

    private fun handleProductListResult(result: Result<ProductListDataClass>) {
        result.onSuccess { productListData ->
            productListAdapter.refreshRecyclerView(this, productListData)
            hideLoader()
        }.onFailure { exception ->
            exception.message?.let { showToast(it) } ?: showToast(getString(R.string.unknown_error))
            hideLoader()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = productListAdapter
    }

    private fun onPullToRefresh() {
        hideRecyclerView()
        showLoader()
        productListViewModel.getProductList()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun hideRecyclerView() {
        binding.recyclerView.visibility = View.GONE
    }

    private fun hideLoader() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun showLoader() {
        binding.progressBar.visibility = View.VISIBLE
    }
}