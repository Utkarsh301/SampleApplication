package com.example.sampleapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapplication.R
import com.example.sampleapplication.model.ResponseModel
import com.example.sampleapplication.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this@MainActivity)[UserViewModel :: class.java]
        getUsers();
    }

    private fun getUsers() {
        var getUsersLiveData = userViewModel.getUsers(this@MainActivity)
        getUsersLiveData.observe(this@MainActivity,
            {

            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}