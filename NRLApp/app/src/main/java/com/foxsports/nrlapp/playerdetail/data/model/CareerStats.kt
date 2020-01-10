package com.foxsports.nrlapp.playerdetail.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CareerStats(
    @SerializedName("games")
    val games: Int?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("tries")
    val tries: Int?,
    @SerializedName("win_percentage")
    val winPercentage: Float?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Float::class.java.classLoader) as? Float
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(games)
        parcel.writeValue(points)
        parcel.writeValue(tries)
        parcel.writeValue(winPercentage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CareerStats> {
        override fun createFromParcel(parcel: Parcel): CareerStats {
            return CareerStats(parcel)
        }

        override fun newArray(size: Int): Array<CareerStats?> {
            return arrayOfNulls(size)
        }
    }
}