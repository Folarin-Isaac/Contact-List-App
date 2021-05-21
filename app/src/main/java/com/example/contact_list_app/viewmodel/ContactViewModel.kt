package com.example.contact_list_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact_list_app.db.User
import com.example.contact_list_app.db.UserDatabase
import kotlinx.coroutines.launch

class ContactViewModel(val db: UserDatabase): ViewModel() {
    fun getUser(email:String): LiveData<User>
    {
        return db.userDao().findUser(email)
    }

    fun addUser(user: User)
    {
        viewModelScope.launch{
            val id = db.userDao().addUser(user)
            Log.i("wrong","id = ${id.toString()}")
        }
    }
}