package com.twentyfivesquares.tiny.sample.controller

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.twentyfivesquares.tiny.TinyController
import com.twentyfivesquares.tiny.sample.R
import com.twentyfivesquares.tiny.sample.adapter.MainAdapter
import kotlinx.android.synthetic.main.controller_main.view.*
import java.util.*

class MainController(context: Context, onItemClicked: (message: String)-> Unit) : TinyController(context) {

    private val vRecycler by lazy { view.main_recycler }

    init {
        val items = Arrays.asList("Item 1", "Item 2", "Item 3")
        vRecycler.layoutManager = LinearLayoutManager(context)
        vRecycler.adapter = MainAdapter(items, onItemClicked)
    }

    override fun getLayoutRes(): Int = R.layout.controller_main
}
