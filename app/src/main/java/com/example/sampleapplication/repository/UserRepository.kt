package com.example.sampleapplication.repository

import android.app.Activity
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.sampleapplication.model.ResponseModel
import com.example.sampleapplication.service.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UserRepository(var application: Application) {

    var getUserMutableLiveData = MutableLiveData<ResponseModel>()
    var getUserResponseModel: ResponseModel? = null
    var compositeDisposable = CompositeDisposable()

    fun getUsers(activity: Activity): MutableLiveData<ResponseModel> {

        val userService = RetrofitInstance.userService
        val getUserObservable = userService.getUsers()
        compositeDisposable.add(
            getUserObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseModel>() {
                    override fun onNext(t: ResponseModel) {
                        getUserResponseModel = t
                    }

                    override fun onError(e: Throwable) {
                        getUserMutableLiveData.postValue(null)
                        when (e) {
                            is UnknownHostException -> {
                                Toast.makeText(
                                    activity,
                                    "No Internet Connection, Please check your connectivity",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            is SocketTimeoutException -> {
                                Toast.makeText(
                                    activity,
                                    "Request Timed Out. Please check your internet connection",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            is HttpException -> {
                                Toast.makeText(
                                    activity,
                                    "Internal Server Error. Please try later",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(activity, "Something went wrong",
                                Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onComplete() {
                        getUserMutableLiveData.postValue(getUserResponseModel)
                    }

                })
        )
        return getUserMutableLiveData
    }

}