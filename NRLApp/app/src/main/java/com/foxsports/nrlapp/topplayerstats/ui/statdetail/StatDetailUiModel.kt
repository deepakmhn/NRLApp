package com.foxsports.nrlapp.topplayerstats.ui.statdetail

import android.os.Parcel
import android.os.Parcelable
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail

data class StatDetailUiModel(
    val statType: String,
    val teamAName: String,
    val teamBName: String,
    val teamATopPlayers: List<TopPlayerDetail>,
    val teamBTopPlayers: List<TopPlayerDetail>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(TopPlayerDetail),
        parcel.createTypedArrayList(TopPlayerDetail)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(statType)
        parcel.writeString(teamAName)
        parcel.writeString(teamBName)
        parcel.writeTypedList(teamATopPlayers)
        parcel.writeTypedList(teamBTopPlayers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatDetailUiModel> {
        override fun createFromParcel(parcel: Parcel): StatDetailUiModel {
            return StatDetailUiModel(parcel)
        }

        override fun newArray(size: Int): Array<StatDetailUiModel?> {
            return arrayOfNulls(size)
        }
    }
}