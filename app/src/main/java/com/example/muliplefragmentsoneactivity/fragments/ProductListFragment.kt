package com.example.muliplefragmentsoneactivity.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muliplefragmentsoneactivity.Models.SharedViewModel
import com.example.muliplefragmentsoneactivity.R
import com.example.muliplefragmentsoneactivity.helper.Repository
import com.example.muliplefragmentsoneactivity.helper.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProductListFragment : Fragment() {

    private lateinit var productAdapter: ProductAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        productAdapter = ProductAdapter(emptyList()) { product ->
            sharedViewModel.selectProduct(product)
            findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment)
        }
        recyclerView.adapter = productAdapter

        fetchProducts()
    }

    private fun fetchProducts() {
        lifecycleScope.launch {
            try {
                val response = Repository(RetrofitClient.apiService).getProducts()
                productAdapter.updateProducts(response)
            } catch (e: HttpException) {
                Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "An error occurred: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}
