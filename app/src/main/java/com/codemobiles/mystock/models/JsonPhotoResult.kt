package com.codemobiles.mystock.models

import android.os.Parcel
import android.os.Parcelable



data class JsonPhotoResult(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(albumId)
        parcel.writeInt(id)
        parcel.writeString(thumbnailUrl)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JsonPhotoResult> {
        override fun createFromParcel(parcel: Parcel): JsonPhotoResult {
            return JsonPhotoResult(parcel)
        }

        override fun newArray(size: Int): Array<JsonPhotoResult?> {
            return arrayOfNulls(size)
        }
    }
}