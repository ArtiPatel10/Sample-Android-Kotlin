package com.sample.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.databinding.ItemQymsBinding
import com.sample.helper.AppConstant
import com.sample.helper.AppMethods
import com.sample.room.GymsTable
import com.sample.ui.main.adapter.viewholder.ItemGymViewHolder
import java.util.ArrayList

class GymAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: ((Int,Boolean) -> Unit)? = null
    var arrayList: List<GymsTable> = ArrayList()

    var cellHeight= AppMethods.screenHeight *40 /100
    var cellWidth=AppMethods.screenWidth *60 /100

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            //add cell for no data available if needed.
            else -> {
                val binding = ItemQymsBinding.inflate(layoutInflater, parent, false)
                ItemGymViewHolder(parent.context, binding, onItemClick,arrayList.size,cellWidth,cellHeight)
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
        if (holder is ItemGymViewHolder) {
            holder.bind(arrayList[position])
        } /*else if (holder is TsRowDividerViewHolder) {
            holder.bind()
        } else if (holder is TsReviewsViewHolder) {
            holder.bind(arrayList[position] as Reviews)
        } else if (holder is NoItemViewHolder) {
            holder.bind()
        }*/
    }
}
