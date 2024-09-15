package com.example.farmacia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: List<Product>
    private val cartProducts: ArrayList<Product> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        loadProductsFromResources()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList) { selectedProduct ->
            onProductSelected(selectedProduct)
        }
        recyclerView.adapter = productAdapter

        val viewCartButton = findViewById<Button>(R.id.viewCartButton)
        viewCartButton.setOnClickListener {
            openCart()
        }

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

    }

    private fun loadProductsFromResources() {
        val productNames = resources.getStringArray(R.array.product_names)
        val productPrices = resources.getStringArray(R.array.product_prices)

        productList = productNames.mapIndexed { index, name ->
            val price = productPrices[index].toDouble()
            Product(name, price)
        }
    }

    private fun onProductSelected(product: Product) {
        cartProducts.add(product)
        Toast.makeText(this, "${product.name} agregado al carrito", Toast.LENGTH_SHORT).show()
    }

    private fun openCart() {
        if (cartProducts.isNotEmpty()) {
            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("cartProducts", cartProducts)
            startActivity(intent)
        } else {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
        }
    }
}