package com.sample.ui.main.adapter.viewholder

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.R
import com.sample.databinding.ItemQymsBinding
import com.sample.helper.rxjava.autoDispose
import com.sample.helper.rxjava.throttleClicks
import com.sample.room.GymsTable
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ItemGymViewHolder(
    val context: Context,
    val binding: ItemQymsBinding,
    private val onItemClick: ((Int,Boolean) -> Unit)?,
    dataSize: Int,
    cellWidth: Int,
    cellHeight: Int
) : RecyclerView.ViewHolder(binding.root) {

    init {
        val lp: RecyclerView.LayoutParams = binding.cvMain.layoutParams as RecyclerView.LayoutParams

        when (adapterPosition) {
            0 -> {
                lp.setMargins(
                    context.resources.getDimensionPixelOffset(R.dimen.dp30),
                    context.resources.getDimensionPixelOffset(R.dimen.dp0),
                    context.resources.getDimensionPixelOffset(R.dimen.dp0),
                    context.resources.getDimensionPixelOffset(R.dimen.dp5)
                )
            }
            dataSize - 1 -> {
                lp.setMargins(
                    context.resources.getDimensionPixelOffset(R.dimen.dp0),
                    context.resources.getDimensionPixelOffset(R.dimen.dp0),
                    context.resources.getDimensionPixelOffset(R.dimen.dp30),
                    context.resources.getDimensionPixelOffset(R.dimen.dp5)
                )
            }
            else -> {
                lp.setMargins(
                    context.resources.getDimensionPixelOffset(R.dimen.dp10).toInt(),
                    context.resources.getDimensionPixelOffset(R.dimen.dp0).toInt(),
                    context.resources.getDimensionPixelOffset(R.dimen.dp10).toInt(),
                    context.resources.getDimensionPixelOffset(R.dimen.dp0).toInt()
                )
            }
        }
        lp.height = cellHeight
        lp.width = cellWidth
        binding.cvMain.layoutParams = lp

    }

    @SuppressLint("SetTextI18n")
    fun bind(gym: GymsTable) {


        binding.tvGymName.text = gym.title
        binding.tvGymTime.text = gym.date?.trim()
        binding.tvGymPrice.text = gym.price.toString()
        binding.tvReviewValue.text = gym.rating.toString()
        binding.ivFavourite.isActivated = gym.favorite

        Glide.with(context)
            .load(Uri.parse("file:///android_asset/map.png"))
            .into(binding.ivMap);

        val assessName = gym.title?.replace(" ","_",false)?.lowercase(Locale.getDefault())

        Glide.with(context)
            .load(Uri.parse("file:///android_asset/$assessName.png"))
            .error(R.drawable.shape_top_left_right_corner)
            .into(binding.ivGym);

        binding.ivFavourite.throttleClicks().subscribe({
            onItemClick?.invoke(gym
                .id,!gym.favorite)
        }, {}).autoDispose(compositeDisposable = CompositeDisposable())

    }
}