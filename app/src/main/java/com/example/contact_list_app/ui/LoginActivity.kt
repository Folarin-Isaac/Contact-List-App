package com.example.contact_list_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.contact_list_app.R
import com.example.contact_list_app.databinding.ActivityLoginBinding
import com.example.contact_list_app.db.User
import com.example.contact_list_app.db.UserDatabase
import com.example.contact_list_app.viewmodel.ContactViewModel
import com.example.contact_list_app.viewmodel.ContactViewModelFactory

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val isLogin: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var viewModel: ContactViewModel
    private lateinit var viewModelFactory: ContactViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeValues()

        isLogin.observe(this,
            Observer<Boolean> {
                if (it) {
                    binding.switchAction.text = getString(R.string.new_user)
                    binding.materialButton.text = getString(R.string.login)
                } else {
                    binding.switchAction.text = getString(R.string.existing_user)
                    binding.materialButton.text = getString(R.string.register)
                }
            })


        setClickListeners()

    }

    fun setClickListeners() {
        binding.switchAction.setOnClickListener {
            isLogin.postValue(!isLogin.value!!)
        }
        binding.materialButton.setOnClickListener {
            loginRegisterProcess()
        }

        binding.emailEditText.setOnClickListener {
            binding.emailInputLayout.error = null
        }


        binding.passwordEditText.setOnClickListener {
            binding.passwordInputLayout2.error = null
        }
    }


    fun initializeValues() {
        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, getString(R.string.database_name)
        ).build()

        viewModelFactory = ContactViewModelFactory(db)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ContactViewModel::class.java)

        isLogin.postValue(true)
    }


    fun loginRegisterProcess() {
        //checks if email and password are intact
        val emailInput = binding.emailEditText.text.toString()
        val passwordInput = binding.passwordEditText.text.toString()

        if (isLogin.value!!) {
            try {
                viewModel.getUser(emailInput).observe(this, Observer {
                    val condition1 = binding.emailEditText.text.toString().equals(it?.emailAddress)
                    val condition2 = binding.passwordEditText.text.toString().equals(it?.password)

                    if (condition1 && condition2) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        binding.emailInputLayout.error = ""
                        binding.passwordInputLayout2.error =
                            getString(R.string.password_error_message)
                        Log.i("wrong", "email = ${it?.emailAddress}, password = ${it?.password}")
                    }
                })


            } catch (e: Error) {
                Log.i("error", e.message.toString())
            }
        } else {
            viewModel.addUser(User(emailInput, passwordInput))

        }
    }
}