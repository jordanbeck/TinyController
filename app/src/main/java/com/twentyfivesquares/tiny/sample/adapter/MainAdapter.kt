package com.twentyfivesquares.tiny.sample.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.twentyfivesquares.tiny.sample.R
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.onClick

class MainAdapter(private var items: List<String>,
                  private val onItemClicked: (message: String) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vName.text = items[position]
        holder.itemView.onClick {
            onItemClicked(items[position])
        }
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty()) 0 else items.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vName: TextView by lazy { itemView.item_name }
    }
}
