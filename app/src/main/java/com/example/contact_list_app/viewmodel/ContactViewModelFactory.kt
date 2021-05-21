package com.example.contact_list_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contact_list_app.db.UserDatabase

class ContactViewModelFactory(private val db: UserDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}