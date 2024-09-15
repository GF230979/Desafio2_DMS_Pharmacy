package com.example.farmacia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private var cartProducts: ArrayList<Product> = arrayListOf()
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        totalTextView = findViewById(R.id.totalTextView)

        cartProducts = intent.getParcelableArrayListExtra("cartProducts") ?: arrayListOf()

        setupRecyclerView()

        calculateTotal()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(cartProducts)
        recyclerView.adapter = cartAdapter
    }

    private fun calculateTotal() {
        var total = 0.0
        for (product in cartProducts) {
            total += product.price
        }

        totalTextView.text = "Total a pagar: $${String.format("%.2f", total)}"
    }
}