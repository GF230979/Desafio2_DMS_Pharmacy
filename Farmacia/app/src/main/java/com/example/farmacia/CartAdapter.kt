package com.example.farmacia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartProducts: List<Product>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.cartProductNameTextView)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.cartProductPriceTextView)

        fun bind(product: Product) {
            productNameTextView.text = product.name
            productPriceTextView.text = "$${product.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_product, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartProducts[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = cartProducts.size
}

