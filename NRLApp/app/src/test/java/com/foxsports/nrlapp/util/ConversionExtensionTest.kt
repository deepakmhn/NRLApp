package com.foxsports.nrlapp.util

import androidx.test.runner.AndroidJUnit4
import com.foxsports.nrlapp.playerdetail.data.model.LastMatchStats
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversionExtensionTest {

    @Test
    fun checkConvertToMap_SuccessfulGetMap() {
        val lastMatchStats = LastMatchStats(
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5
        )
        val map = lastMatchStats.serializeToMap()
        Assertions.assertThat(map).isNotNull
        Assertions.assertThat(map.size).isEqualTo(66)
        Assertions.assertThat(map.get("errors")).isEqualTo(1.0)
    }

}