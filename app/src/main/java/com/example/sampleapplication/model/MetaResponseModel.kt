package com.example.sampleapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetaResponseModel(
    @SerializedName("pagination")
    @Expose val pagination: PaginationResponseModel?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(PaginationResponseModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(pagination, flags)
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