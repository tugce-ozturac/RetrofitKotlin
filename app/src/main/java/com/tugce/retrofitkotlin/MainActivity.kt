package com.tugce.retrofitkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugce.retrofitkotlin.adapter.CryptoAdapter
import com.tugce.retrofitkotlin.databinding.ActivityMainBinding
import com.tugce.retrofitkotlin.viewmodel.CryptoViewModel

class MainActivity : AppCompatActivity() {

    private val vm: CryptoViewModel by viewModels()
    private lateinit var adapter: CryptoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView setup
        adapter = CryptoAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // SwipeRefresh
        binding.swipe.setOnRefreshListener {
            vm.fetchFromInternet()
            binding.swipe.isRefreshing = false
        }

        // ViewModel observers
        vm.cryptos.observe(this, Observer { list ->
            adapter.updateList(list)
        })

        vm.error.observe(this, Observer { err ->
            err?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        })
    }
}