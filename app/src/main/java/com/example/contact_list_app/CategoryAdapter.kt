package com.example.contact_list_app

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact_list_app.databinding.CategoryListItemsBinding
import java.util.*

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bindItems(category, position)

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryViewHolder(private val binding: CategoryListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(category: Category, position: Int) {
            val randomColors = Random()
            val color = Color.argb(
                255,
                randomColors.nextInt(256),
                randomColors.nextInt(256),
                randomColors.nextInt(256)
            )
            val androidColors = binding.root.context.resources.getIntArray(R.array.androidColors)

            binding.relativeLayout.setBackgroundColor(androidColors[position])
            binding.categoryInitials.text = category.initials
            binding.categoryText.text = category.name

            binding.relativeLayout.setOnClickListener {
                val intent = Intent(binding.root.context, ContactListActivity::class.java)
                intent.putExtra("TITLE", category.name)
                binding.root.context.startActivity(intent)
            }


        }

    }
}