package com.example.activities

import android.os.Parcel
import android.os.Parcelable

data class Person(
    val name: String,
    val surname: String,
    val birthday: String,
    val country: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(surname)
        dest.writeString(birthday)
        dest.writeString(country)
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(
                name = parcel.readString()!!,
                surname = parcel.readString()!!,
                birthday = parcel.readString()!!,
                country = parcel.readString()!!,
            )
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}