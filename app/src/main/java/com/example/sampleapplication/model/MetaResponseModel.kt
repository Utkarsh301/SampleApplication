package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetaResponseModel() : Parcelable {
    @SerializedName("pagination")
    @Expose
    private val pagination: PaginationResponseModel? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MetaResponseModel> {
        override fun createFromParcel(parcel: Parcel): MetaResponseModel {
            return MetaResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<MetaResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}