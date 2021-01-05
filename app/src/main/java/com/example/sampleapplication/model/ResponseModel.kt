package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseModel(
    @SerializedName("code")
    @Expose val code: Int?,

    @SerializedName("meta")
    @Expose val meta: MetaResponseModel?,

    @SerializedName("data")
    @Expose val data: List<UserResponseModel>? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(MetaResponseModel::class.java.classLoader),
        parcel.createTypedArrayList(UserResponseModel)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(code)
        parcel.writeParcelable(meta, flags)
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseModel> {
        override fun createFromParcel(parcel: Parcel): ResponseModel {
            return ResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<ResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}