package com.example.muliplefragmentsoneactivity.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

    class SharedViewModel : ViewModel() {

        private val _selectedProduct = MutableLiveData<Product>()

        val selectedProduct: LiveData<Product>
            get() = _selectedProduct

        fun selectProduct(product: Product) {
            _selectedProduct.value = product
        }
    }
