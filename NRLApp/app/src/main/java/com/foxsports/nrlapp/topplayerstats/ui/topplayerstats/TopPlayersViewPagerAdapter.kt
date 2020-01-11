package com.foxsports.nrlapp.topplayerstats.ui.topplayerstats

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.foxsports.nrlapp.topplayerstats.ui.statdetail.StatDetailFragment
import com.foxsports.nrlapp.topplayerstats.ui.statdetail.StatDetailUiModel


class TopPlayersViewPagerAdapter(fragmentManager: FragmentManager,var statDetailList: List<StatDetailUiModel>) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = StatDetailFragment.createFragment(statDetailList[position])

    override fun getCount() = statDetailList.size
}