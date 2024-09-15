package com.example.farmacia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onProductSelected: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.productPriceTextView)
        private val addButton: Button = itemView.findViewById(R.id.addButton)

        fun bind(product: Product, onProductSelected: (Product) -> Unit) {
            productNameTextView.text = product.name
            productPriceTextView.text = "$${product.price}"

            addButton.setOnClickListener {
                onProductSelected(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product, onProductSelected)
    }

    override fun getItemCount(): Int = productList.size
}
