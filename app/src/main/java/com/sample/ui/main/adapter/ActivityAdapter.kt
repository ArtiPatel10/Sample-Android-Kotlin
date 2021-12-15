package com.sample.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.databinding.ItemActivityBinding
import com.sample.helper.AppConstant
import com.sample.room.ActivitiesTable
import com.sample.ui.main.adapter.viewholder.ItemActivityViewHolder

class ActivityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: ((Int, Boolean) -> Unit)? = null
    var arrayList: List<ActivitiesTable> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            //add cell for no data available if needed.
            else -> {
                val binding = ItemActivityBinding.inflate(layoutInflater, parent, false)
                ItemActivityViewHolder(parent.context, binding, onItemClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (arrayList.isEmpty()) {
            AppConstant.RecyclerViewType.NoData
        } else {
            /* if (arrayList[position] is Boolean) {
                 AppConstant.RecyclerViewType.Divider
             } else if (arrayList[position] is Reviews) {
                 AppConstant.RecyclerViewType.ReviewData
             } else {*/
            AppConstant.RecyclerViewType.Item
//            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemActivityViewHolder) {
            holder.bind(arrayList[position])
        } /*else if (holder is NoItemViewHolder) {
            holder.bind()
        }*/
    }
}
