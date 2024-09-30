package com.mehdisekoba.filimo.ui.category.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails.Included.Attributes
import com.mehdisekoba.filimo.databinding.ItemCategoriesSubBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import javax.inject.Inject


class SubCategoryAdapter @Inject constructor() :
    RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    var items = emptyList<Attributes>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoriesSubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: ItemCategoriesSubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Attributes) {
            //initViews
            binding.apply {
                txtName.text = item.movieTitle
                item.pic?.movieImgB?.let { imgPoster.loadImage(it) }

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