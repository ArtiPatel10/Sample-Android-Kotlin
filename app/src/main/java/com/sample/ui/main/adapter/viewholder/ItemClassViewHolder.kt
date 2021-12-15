package com.sample.ui.main.adapter.viewholder

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.databinding.ItemClassBinding
import com.sample.helper.rxjava.autoDispose
import com.sample.helper.rxjava.throttleClicks
import com.sample.room.ClassesTable
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ItemClassViewHolder(
    val context: Context,
    val binding: ItemClassBinding,
    private val onItemClick: ((Int,Boolean) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    init {

    }

    @SuppressLint("SetTextI18n")
    fun bind(classInfo: ClassesTable) {

        binding.tvClassName.text = classInfo.title
        binding.tvTime.text = classInfo.time?.trim()
        binding.tvGymPrice.text = classInfo.price.toString()
        binding.ivFavourite.isActivated = classInfo.favorite

        val assessName: String? = classInfo.title?.replace(" ","_",false)?.lowercase(Locale.getDefault())
        Glide.with(context)
            .load(Uri.parse("file:///android_asset/$assessName.png"))
            .into(binding.ivClassImage);

        binding.ivFavourite.throttleClicks().subscribe({
            onItemClick?.invoke(classInfo
                .localId,!classInfo.favorite)
        }, {}).autoDispose(compositeDisposable = CompositeDisposable())

    }
}