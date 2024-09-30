package com.mehdisekoba.filimo.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail.Data
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail.Data.Attributes.ActorCrewData.Profile
import com.mehdisekoba.filimo.databinding.ItemActorBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import javax.inject.Inject


class ActorAdapter @Inject constructor() :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    private var items = emptyList<Profile>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Profile) {
            //initViews
            binding.apply {
                itemImage.loadImage(item.profileImage!!)
                txtTitle.text = item.name
                //Click

            }
        }
    }
    fun setData(data: List<Profile>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}