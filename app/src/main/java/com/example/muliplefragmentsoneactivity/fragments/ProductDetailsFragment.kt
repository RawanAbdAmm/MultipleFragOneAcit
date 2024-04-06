package com.example.muliplefragmentsoneactivity.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.muliplefragmentsoneactivity.R
import com.example.muliplefragmentsoneactivity.Models.Product
import com.example.muliplefragmentsoneactivity.Models.SharedViewModel

class ProductDetailFragment : Fragment() {


    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        sharedViewModel.selectedProduct.observe(viewLifecycleOwner, Observer { product ->
            displayProductDetails(product)
        })
    }


    private fun displayProductDetails(product: Product) {
        view?.findViewById<TextView>(R.id.textViewTitle)?.text = product.title
        view?.findViewById<TextView>(R.id.textViewDescription)?.text = product.description
    }
}