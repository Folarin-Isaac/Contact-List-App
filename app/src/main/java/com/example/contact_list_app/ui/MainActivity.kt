package com.example.contact_list_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contact_list_app.Category
import com.example.contact_list_app.adapter.CategoryAdapter
import com.example.contact_list_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategory(binding)

    }

    private fun setupCategory(binding: ActivityMainBinding) {
        val categories = listOf(
            Category("Family", "F"),
            Category("Business", "B"),
            Category("Friends", "F"),
            Category("Tutors", "T")

        )
        val adapter = CategoryAdapter(categories)
        binding.categoryRecyclerview.adapter = adapter
        binding.categoryRecyclerview.layoutManager = GridLayoutManager(this, 2)
        binding.categoryRecyclerview.setHasFixedSize(true)
    }


}