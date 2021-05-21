package com.example.contact_list_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.contact_list_app.Contact
import com.example.contact_list_app.R
import com.example.contact_list_app.adapter.ContactAdapter
import com.example.contact_list_app.databinding.ActivityContactListBinding
import com.google.android.material.textfield.TextInputEditText

class ContactListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactListBinding
    private var adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            title = it.getStringExtra("TITLE")
        }
        setUpData(binding)
    }

    private fun setUpData(binding: ActivityContactListBinding){
        binding.contactRecyclerView.adapter = adapter
        binding.contactRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextInputEditText>(R.id.edit_name)
        val phone = view.findViewById<TextInputEditText>(R.id.edit_phone_no)
        val saveButton = view.findViewById<Button>(R.id.save_button)

        phone.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveButton.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        val alertDialog = builder.create()
        saveButton.setOnClickListener {
            val contact = Contact(name.text.toString(), phone.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()

        }
        binding.fab.setOnClickListener {
            alertDialog.show()
        }

    }
}