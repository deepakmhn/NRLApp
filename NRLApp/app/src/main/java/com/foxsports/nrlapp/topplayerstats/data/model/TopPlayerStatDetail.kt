package com.foxsports.nrlapp.topplayerstats.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TopPlayerStatDetail(
    @SerializedName("match_id")
    val matchId: String?,
    @SerializedName("team_A")
    val teamA: TeamDetail?,
    @SerializedName("team_B")
    val teamB: TeamDetail?,
    @SerializedName("stat_type")
    val statType: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(TeamDetail::class.java.classLoader),
        parcel.readParcelable(TeamDetail::class.java.classLoader),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(matchId)
        parcel.writeParcelable(teamA, flags)
        parcel.writeParcelable(teamB, flags)
        parcel.writeString(statType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopPlayerStatDetail> {
        override fun createFromParcel(parcel: Parcel): TopPlayerStatDetail {
            return TopPlayerStatDetail(parcel)
        }

        override fun newArray(size: Int): Array<TopPlayerStatDetail?> {
            return arrayOfNulls(size)
        }
    }
}
