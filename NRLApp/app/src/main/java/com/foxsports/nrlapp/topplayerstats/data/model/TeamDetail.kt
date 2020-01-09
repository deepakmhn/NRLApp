package com.foxsports.nrlapp.topplayerstats.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TeamDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("top_players")
    val topPlayers: List<TopPlayerDetail>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(TopPlayerDetail)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(code)
        parcel.writeString(shortName)
        parcel.writeTypedList(topPlayers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamDetail> {
        override fun createFromParcel(parcel: Parcel): TeamDetail {
            return TeamDetail(parcel)
        }

        override fun newArray(size: Int): Array<TeamDetail?> {
            return arrayOfNulls(size)
        }
    }
}