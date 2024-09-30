package com.mehdisekoba.filimo.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included.Attributes
import com.mehdisekoba.filimo.databinding.ItemSplashBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import javax.inject.Inject

class SplashAdapter @Inject constructor() : RecyclerView.Adapter<SplashAdapter.ViewHolder>() {

    private var items = emptyList<Attributes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSplashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position % items.size]
        holder.bind(item)
    }

    override fun getItemCount() =  if(items.size > 8) 8 * 3 else items.size

    inner class ViewHolder(private val binding: ItemSplashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Attributes) {
            binding.apply {
                item.pic?.movieImgB?.let { itemImage.loadImage(it) }
            }
        }
    }

    fun setData(data: List<Attributes>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}
