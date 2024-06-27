package com.maverick.deltafour.owner.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.maverick.deltafour.owner.fragment.NekoFragment
import com.maverick.deltafour.owner.fragment.ShinobuFragment
import com.maverick.deltafour.owner.fragment.WaifuFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val fragmentList: Array<Fragment>):
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    //returns fragment based on position of view pager.
    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return WaifuFragment()
            1 -> return NekoFragment()
            2 -> return ShinobuFragment()
        }
        return WaifuFragment()
    }
}