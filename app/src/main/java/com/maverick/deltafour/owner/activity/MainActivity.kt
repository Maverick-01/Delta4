package com.maverick.deltafour.owner.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.maverick.deltafour.R
import com.maverick.deltafour.databinding.ActivityMainBinding
import com.maverick.deltafour.observer.FeedViewModel
import com.maverick.deltafour.owner.adapter.ViewPagerAdapter
import com.maverick.deltafour.owner.fragment.NekoFragment
import com.maverick.deltafour.owner.fragment.ShinobuFragment
import com.maverick.deltafour.owner.fragment.WaifuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: FeedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
//        setSupportActionBar(binding.toolbar)
        val fragmentList = arrayOf(WaifuFragment(),NekoFragment(),ShinobuFragment())

        //adapter initialised for view pager.
        binding.viewpager.adapter = ViewPagerAdapter(fragmentActivity = this, fragmentList = fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Waifu"
                1 -> "Neko"
                2 -> "Shinobu"
                else -> "Waifu"
            }
        }.attach()
    }
}