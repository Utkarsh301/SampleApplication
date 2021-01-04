package com.example.sampleapplication.service

import com.example.sampleapplication.model.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface UserService {

    @GET("users")
    fun getUsers() : Observable<ResponseModel>
}