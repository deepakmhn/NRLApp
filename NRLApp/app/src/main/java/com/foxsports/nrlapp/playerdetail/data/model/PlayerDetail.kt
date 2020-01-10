package com.foxsports.nrlapp.playerdetail.data.model

import com.google.gson.annotations.SerializedName

data class PlayerDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    @SerializedName("height_cm")
    val heightCm: Int?,
    @SerializedName("other_names")
    val otherNames: String?,
    @SerializedName("weight_kg")
    val weightKg: Int?,
    @SerializedName("last_match_id")
    val lastMatchId: String?,
    @SerializedName("career_stats")
    val careerStats: CareerStats?,
    @SerializedName("last_match_stats")
    val lastMatchStats: LastMatchStats?
)
