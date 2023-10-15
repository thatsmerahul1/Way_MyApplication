package com.rahul.way_myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.way_myapplication.adapter.ProductListAdapter
import com.rahul.way_myapplication.data.model.ProductListDataClass
import com.rahul.way_myapplication.data.services.ApiClient
import com.rahul.way_myapplication.databinding.ActivityMainBinding
import com.rahul.way_myapplication.repository.Repository
import com.rahul.way_myapplication.viewmodel.ProductListViewModel
import com.rahul.way_myapplication.viewmodel.ProductListViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productListAdapter: ProductListAdapter
//    private val productListViewModel: ProductListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productListViewModel = ViewModelProvider(
            this,
            ProductListViewModelFactory(Repository(ApiClient.apiService))
        ).get(ProductListViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productListAdapter = ProductListAdapter(applicationContext, ProductListDataClass())

        initRecyclerView()
        productListViewModel.getProductList()
        productListViewModel.productList.observe(this) {result ->

            result.onSuccess {
                val productListData = result.getOrNull()
                productListData?.let {
                    productListAdapter.refreshRecyclerView(applicationContext, it)
                    hideLoader(binding)
                } ?: run {
                    showToast("Received null product list")
                }
            }
            result.onFailure {
                val exception = result.exceptionOrNull()
                exception?.let {
                    it.message?.let { it1 -> showToast(it1) }
                } ?: run {
                    showToast("Unknown error")
                }
                hideLoader(binding)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            onPullToRefresh(productListViewModel)
        }
    }

    override fun onNetworkStatusChanged(isNetworkAvailable: Boolean) {
        super.onNetworkStatusChanged(isNetworkAvailable)
        Log.e("MainActivity", "Status: $isNetworkAvailable")
        hideLoader(binding)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }
    }

    private fun onPullToRefresh(productListViewModel: ProductListViewModel) {
        hideRecyclerView()
        showLoader(binding)
        productListViewModel.getProductList()
        binding.swipeRefreshLayout.isRefreshing = false
    }
    private fun hideRecyclerView() {
        binding.recyclerView.visibility = View.GONE
    }
}
private fun MainActivity.hideLoader(binding: ActivityMainBinding) {
    lifecycleScope.launch {
        delay(1000)
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}
private fun MainActivity.showLoader(binding: ActivityMainBinding) {
    binding.progressBar.visibility = View.VISIBLE
}