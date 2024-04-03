package com.yusuferkamozyer.graduation.presentation.program.model

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable

class AppItem(val appName: String, val appIcon: Drawable, val appPackage: String?, var isSelected:Boolean):Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        TODO("appIcon"),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun toString(): String {
        return appName
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(appName)
        parcel.writeString(appPackage)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppItem> {
        override fun createFromParcel(parcel: Parcel): AppItem {
            return AppItem(parcel)
        }

        override fun newArray(size: Int): Array<AppItem?> {
            return arrayOfNulls(size)
        }
    }
}