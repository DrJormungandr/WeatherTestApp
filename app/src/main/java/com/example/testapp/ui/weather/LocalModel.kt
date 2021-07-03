package com.example.testapp.ui.weather

import android.os.Parcel
import android.os.Parcelable

data class LocalModel(
    val dateTime: Int,
    val sunrise: Int,
    val sunset: Int,
    val temperature: Double,
    val dayOfWeek: String,
    val formattedDate: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()?: "",
        parcel.readString()?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dateTime)
        parcel.writeInt(sunrise)
        parcel.writeInt(sunset)
        parcel.writeDouble(temperature)
        parcel.writeString(dayOfWeek)
        parcel.writeString(formattedDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocalModel> {
        override fun createFromParcel(parcel: Parcel): LocalModel {
            return LocalModel(parcel)
        }

        override fun newArray(size: Int): Array<LocalModel?> {
            return arrayOfNulls(size)
        }
    }
}
