package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponseModel() : Parcelable {

    @SerializedName("id")
    @Expose
    private val id: Int? = null

    @SerializedName("name")
    @Expose
    private val name: String? = null

    @SerializedName("email")
    @Expose
    private val email: String? = null

    @SerializedName("gender")
    @Expose
    private val gender: String? = null

    @SerializedName("status")
    @Expose
    private val status: String? = null

    @SerializedName("created_at")
    @Expose
    private val createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    private val updatedAt: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserResponseModel> {
        override fun createFromParcel(parcel: Parcel): UserResponseModel {
            return UserResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<UserResponseModel?> {
            return arrayOfNulls(size)
        }
    }

}
