package com.foxsports.nrlapp.playerdetail.data.model

import com.google.gson.annotations.SerializedName

data class LastMatchStats(
    @SerializedName("errors")
    val errors: Int?,
    @SerializedName("goals")
    val goals: Int?,
    @SerializedName("intercepted")
    val intercepted: Int?,
    @SerializedName("intercepts")
    val intercepts: Int?,
    @SerializedName("kicks")
    val kicks: Int?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("possessions")
    val possessions: Int?,
    @SerializedName("runs")
    val runs: Int?,
    @SerializedName("tackles")
    val tackles: Int?,
    @SerializedName("tries")
    val tries: Int?,
    @SerializedName("mins_played")
    val minsPlayed: Int?,
    @SerializedName("attacking_kicks")
    val attackingKicks: Int?,
    @SerializedName("bombs_caught")
    val bombsCaught: Int?,
    @SerializedName("bombs_dropped")
    val bombsDropped: Int?,
    @SerializedName("charged_down")
    val chargedDown: Int?,
    @SerializedName("charges_down")
    val chargesDown: Int?,
    @SerializedName("drop_outs")
    val dropOuts: Int?,
    @SerializedName("dummy_half_runs")
    val dummyHalfRuns: Int?,
    @SerializedName("effective_offloads")
    val effectiveOffloads: Int?,
    @SerializedName("fantasy_points")
    val fantasyPoints: Int?,
    @SerializedName("field_goal_attempts")
    val fieldGoalAttempts: Int?,
    @SerializedName("field_goal_misses")
    val fieldGoalMisses: Int?,
    @SerializedName("field_goals")
    val fieldGoals: Int?,
    @SerializedName("forced_drop_outs")
    val forcedDropOuts: Int?,
    @SerializedName("general_play_pass")
    val generalPlayPass: Int?,
    @SerializedName("goal_misses")
    val goalMisses: Int?,
    @SerializedName("ineffective_tackles")
    val ineffectiveTackles: Int?,
    @SerializedName("in_goal_escapes")
    val inGoalEscapes: Int?,
    @SerializedName("interchanges_off")
    val interchangesOff: Int?,
    @SerializedName("interchanges_on")
    val interchangesOn: Int?,
    @SerializedName("kick_errors")
    val kickErrors: Int?,
    @SerializedName("kick_metres")
    val kickMetres: Int?,
    @SerializedName("kick_return_metres")
    val kickReturnMetres: Int?,
    @SerializedName("kick_returns")
    val kickReturns: Int?,
    @SerializedName("kicks_4020")
    val kicks4020: Int?,
    @SerializedName("kicks_dead")
    val kicksDead: Int?,
    @SerializedName("last_touch_try_assists")
    val lastTouchTryAssists: Int?,
    @SerializedName("line_break_assists")
    val lineBreakAssists: Int?,
    @SerializedName("line_break_causes")
    val lineBreakCauses: Int?,
    @SerializedName("line_breaks")
    val lineBreaks: Int?,
    @SerializedName("line_engagements")
    val lineEngagements: Int?,
    @SerializedName("long_kicks")
    val longKicks: Int?,
    @SerializedName("missed_tackles")
    val missedTackles: Int?,
    @SerializedName("off_loads")
    val offLoads: Int?,
    @SerializedName("one_pass_runs")
    val onePassRuns: Int?,
    @SerializedName("penalties_conceded")
    val penaltiesConceded: Int?,
    @SerializedName("play_the_balls")
    val playTheBalls: Int?,
    @SerializedName("run_metres")
    val runMetres: Int?,
    @SerializedName("runs_7less_meters")
    val runs7lessMeters: Int?,
    @SerializedName("runs_8plus_meters")
    val runs8plusMeters: Int?,
    @SerializedName("send_offs")
    val sendOffs: Int?,
    @SerializedName("sin_bins")
    val sinBins: Int?,
    @SerializedName("steals_one_on_one")
    val stealsOneOnOne: Int?,
    @SerializedName("stolen_one_on_one")
    val stolenOneOnOne: Int?,
    @SerializedName("tackle_busts")
    val tackleBusts: Int?,
    @SerializedName("tackled_opp20")
    val tackledOpp20: Int?,
    @SerializedName("tackle_opp_half")
    val tackleOppHalf: Int?,
    @SerializedName("tackles_one_on_one")
    val tacklesOneOnOne: Int?,
    @SerializedName("try_assists")
    val tryAssists: Int?,
    @SerializedName("try_causes")
    val tryCauses: Int?,
    @SerializedName("try_contribution_percentage")
    val tryContributionPercentage: Int?,
    @SerializedName("try_contributions")
    val tryContributions: Int?,
    @SerializedName("try_involvements")
    val tryInvolvements: Int?,
    @SerializedName("twenty_metre_restarts")
    val twentyMetreRestarts: Int?,
    @SerializedName("weighted_kicks")
    val weightedKicks: Int?,
    @SerializedName("post_contact_metres")
    val postContactMetres: Int?
)