package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaginationResponseModel() : Parcelable {
    @SerializedName("total")
    @Expose
    private val total: Int? = null

    @SerializedName("pages")
    @Expose
    private val pages: Int? = null

    @SerializedName("page")
    @Expose
    private val page: Int? = null

    @SerializedName("limit")
    @Expose
    private val limit: Int? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PaginationResponseModel> {
        override fun createFromParcel(parcel: Parcel): PaginationResponseModel {
            return PaginationResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<PaginationResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}