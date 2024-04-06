package com.example.muliplefragmentsoneactivity.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.muliplefragmentsoneactivity.Models.Product
import com.example.muliplefragmentsoneactivity.R


class ProductAdapter(
    private var products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewProduct)

        fun bind(product: Product) {
            titleTextView.text = product.title
            descriptionTextView.text = product.description

            // Load image using Glide (assuming product.images is a list of image URLs)
            if (product.images.isNotEmpty()) {
                Glide.with(itemView)
                    .load(product.images[0]) // Load first image URL
                    .placeholder(R.drawable.ic_placeholder) // Placeholder image while loading
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            }

            itemView.setOnClickListener {
                onItemClick(product)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}