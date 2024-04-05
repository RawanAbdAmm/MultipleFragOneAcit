package com.example.muliplefragmentsoneactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muliplefragmentsoneactivity.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.muliplefragmentsoneactivity.helper.Repository
import com.example.muliplefragmentsoneactivity.helper.RetrofitClient
import kotlinx.coroutines.CoroutineScope

import retrofit2.HttpException

class ProductListFragment : Fragment() {

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        productAdapter = ProductAdapter(emptyList())
        recyclerView.adapter = productAdapter
        fetchProducts()
        return view
    }

    private fun fetchProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = Repository(RetrofitClient.apiService).getProducts()
                productAdapter.updateProducts(response)
            } catch (e: HttpException) {
                Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "An error occurred:{$e}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}