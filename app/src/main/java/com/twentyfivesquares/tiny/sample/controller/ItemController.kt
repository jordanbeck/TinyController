package com.twentyfivesquares.tiny.sample.controller

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.twentyfivesquares.tiny.TinyController
import com.twentyfivesquares.tiny.sample.R
import com.twentyfivesquares.tiny.sample.adapter.MainAdapter
import kotlinx.android.synthetic.main.controller_item.view.*
import java.util.*

class ItemController(context: Context, start: Int, onItemClicked: (message: String)-> Unit) : TinyController(context) {

    private val vRecycler by lazy { view.item_recycler }

    init {
        val items = Arrays.asList("Item $start A", "Item $start B", "Item $start C")
        vRecycler.layoutManager = LinearLayoutManager(context)
        vRecycler.adapter = MainAdapter(items, onItemClicked)
    }

    override fun getLayoutRes(): Int = R.layout.controller_item

}