package com.sample.ui.main.adapter.viewholder

import android.annotation.SuppressLint
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sample.databinding.ItemActivityBinding
import com.sample.helper.rxjava.autoDispose
import com.sample.helper.rxjava.throttleClicks
import com.sample.room.ActivitiesTable
import io.reactivex.disposables.CompositeDisposable

class ItemActivityViewHolder(
    val context: Context,
    val binding: ItemActivityBinding,
    private val onItemClick: ((Int, Boolean) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    init {
        val lp: ConstraintLayout.LayoutParams =
            binding.clIcon.layoutParams as ConstraintLayout.LayoutParams

        when (adapterPosition) {
            0 -> {
                lp.setMargins(
                   20,
                  0,
                    10,
                  0
                )
            }
            else ->{
                lp.setMargins(
                    10,
                    0,
                    20,
                    0
                )

            }
        }

        binding.clIcon.layoutParams = lp
    }

    @SuppressLint("SetTextI18n")
    fun bind(activityData: ActivitiesTable) {
        activityData.resourse.let { binding.ivIcon.setImageResource(it) }
        binding.ivIcon.isActivated = activityData.selected
        binding.ivIcon.throttleClicks().subscribe {
            onItemClick?.invoke(activityData.resourse, !binding.ivIcon.isActivated)
        }.autoDispose(CompositeDisposable())

    }
}