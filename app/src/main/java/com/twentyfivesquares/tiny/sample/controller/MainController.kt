package com.twentyfivesquares.tiny.sample.controller

import android.content.Context
import android.support.design.widget.TabLayout
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.twentyfivesquares.tiny.TinyController
import com.twentyfivesquares.tiny.sample.R
import com.twentyfivesquares.tiny.view.ViewPagerAdapter
import kotlinx.android.synthetic.main.controller_main.view.*

class MainController(context: Context) : TinyController(context) {

    private val vTabs by lazy { view.main_tab_layout }
    private val vPager by lazy { view.main_viewpager }

    init {
        // Initialize the ViewPager adapter
        vPager.adapter = MainAdapter(context)
        vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(vTabs))

        // Create tabs
        vTabs.addTab(vTabs.newTab().setText("Text 1"))
        vTabs.addTab(vTabs.newTab().setText("Text 2"))

        //@lewtodo make excitations function
        vTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) { vPager.currentItem = tab.position }
            override fun onTabUnselected(tab: TabLayout.Tab) { }
            override fun onTabReselected(tab: TabLayout.Tab) { }
        })
    }

    override fun getLayoutRes(): Int = R.layout.controller_main

    private inner class MainAdapter(context: Context) : ViewPagerAdapter() {
        private var controllerFirst: ItemController
        private var controllerSecond: ItemController

        init {
            controllerFirst = ItemController(context, 1, { onItemClicked(it) })
            controllerSecond = ItemController(context, 4, { onItemClicked(it) })
        }

        override fun getView(position: Int, container: ViewGroup): View? {
            when (position) {
                0 -> { return controllerFirst.view }
                1 -> { return controllerSecond.view }
                else -> { return null }
            }
        }

        override fun getCount() = 2

        fun onItemClicked(item: String) {
            Toast.makeText(context, "Clicked {$item}", Toast.LENGTH_SHORT).show()
        }
    }
}
