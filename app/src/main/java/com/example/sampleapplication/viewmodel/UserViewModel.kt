package com.example.sampleapplication.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sampleapplication.model.ResponseModel
import com.example.sampleapplication.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository = UserRepository(application)

    fun getUsers(activity: Activity) : LiveData<ResponseModel> {
        return userRepository.getUsers(activity = activity)
    }

}