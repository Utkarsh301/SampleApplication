package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaginationResponseModel(
    @SerializedName("total")
    @Expose val total: Int?,

    @SerializedName("pages")
    @Expose val pages: Int?,

    @SerializedName("page")
    @Expose val page: Int?,

    @SerializedName("limit")
    @Expose val limit: Int?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(total)
        parcel.writeValue(pages)
        parcel.writeValue(page)
        parcel.writeValue(limit)
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