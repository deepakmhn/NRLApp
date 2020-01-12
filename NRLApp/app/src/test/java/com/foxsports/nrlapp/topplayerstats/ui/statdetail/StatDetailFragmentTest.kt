package com.foxsports.nrlapp.topplayerstats.ui.statdetail

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail
import com.foxsports.nrlapp.topplayerstats.ui.statdetail.StatDetailFragment.Companion.STAT_DETAIL
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class StatDetailFragmentTest {

    @Test
    fun showStatDetailFragment_checkValues() {
        val args = Bundle().apply { putParcelable(STAT_DETAIL, getStatUiModel()) }
        val statDetailFragmentScenario = launchFragmentInContainer<StatDetailFragment>(args)

        statDetailFragmentScenario.moveToState(Lifecycle.State.RESUMED)

        statDetailFragmentScenario.onFragment { fragment ->
            assertThat(fragment.binding.textStatType.text).isEqualTo("Run metres")
        }
    }

    private fun getStatUiModel() =
        StatDetailUiModel("run_metres", "Team A", "Team B", getTopPlayerList(), getTopPlayerList())

    private fun getTopPlayerList(): List<TopPlayerDetail> {
        val player1 = TopPlayerDetail(1, "Wing", "Player 1", "P1", 100, 1)
        val player2 = TopPlayerDetail(2, "Wing", "Player 2", "P2", 100, 2)
        val player3 = TopPlayerDetail(3, "Wing", "Player 3", "P3", 100, 3)
        return listOf(player1, player2, player3)
    }
}