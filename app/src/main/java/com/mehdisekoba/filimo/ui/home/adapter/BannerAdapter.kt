package com.mehdisekoba.filimo.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.AnimationUtils.lerp
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included.Attributes
import com.mehdisekoba.filimo.databinding.ItemBannerBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import javax.inject.Inject

class BannerAdapter @Inject constructor() :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    private var items = emptyList<Attributes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = if (items.size > 6) 6 else items.size

    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("RestrictedApi")
        fun bind(item: Attributes) {
            binding.apply {
                //initViews
                txtTitle.text = item.title
                linkTitle.text = item.linkText
                carouselItemContainer.setOnMaskChangedListener { maskRect ->
                    val translationX = maskRect.left
                    val alpha = lerp(1F, 0F, 0F, 80F, maskRect.left)
                    txtTitle.translationX = translationX
                    linkTitle.translationX = translationX
                    imgLogo.translationX = translationX
                    txtTitle.alpha = alpha
                    linkTitle.alpha = alpha
                    imgLogo.alpha = alpha
                }
                item.coverMobile?.firstOrNull()?.let { imageUrl ->
                    carouselImageView.loadImage(imageUrl)
                }
                item.logo?.let {
                    imgLogo.isVisible = true
                    imgLogo.loadImage(it)
                } ?: run {
                    imgLogo.isVisible = false
                }


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
