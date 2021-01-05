package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponseModel(

    @SerializedName("id")
    @Expose var id: Int,

    @SerializedName("name")
    @Expose val name: String?,

    @SerializedName("email")
    @Expose val email: String?,

    @SerializedName("gender")
    @Expose val gender: String?,

    @SerializedName("status")
    @Expose val status: String?,

    @SerializedName("created_at")
    @Expose val createdAt: String?,

    @SerializedName("updated_at")
    @Expose val updatedAt: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(gender)
        parcel.writeString(status)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
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
