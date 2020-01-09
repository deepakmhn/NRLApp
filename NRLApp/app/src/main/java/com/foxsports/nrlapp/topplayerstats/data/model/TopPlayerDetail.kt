package com.foxsports.nrlapp.topplayerstats.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TopPlayerDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("position")
    val position: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("stat_value")
    val statValue: Int,
    @SerializedName("jumper_number")
    val jumperNumber: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(position)
        parcel.writeString(fullName)
        parcel.writeString(shortName)
        parcel.writeInt(statValue)
        parcel.writeInt(jumperNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopPlayerDetail> {
        override fun createFromParcel(parcel: Parcel): TopPlayerDetail {
            return TopPlayerDetail(parcel)
        }

        override fun newArray(size: Int): Array<TopPlayerDetail?> {
            return arrayOfNulls(size)
        }
    }
}
