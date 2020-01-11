package com.foxsports.nrlapp.playerdetail.ui

/**
 * Player detail model for the UI
 */
data class PlayerDetailUiModel(
    val success: Boolean,
    val playerId: Int,
    val playerName: String,
    val position: String,
    val lastMatchStats: List<StatUiModel>
)