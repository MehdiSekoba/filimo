package com.mehdisekoba.filimo.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail.Data.Attributes.OtherCrewData
import com.mehdisekoba.filimo.databinding.ItemProductionBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
class ProductionAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    RecyclerView.Adapter<ProductionAdapter.ViewHolder>() {

    private var items = emptyList<OtherCrewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemProductionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OtherCrewData) {
            binding.apply {
                // Set title and description from PostInfo
                item.postInfo?.let {
                    titleTxt.text = it.titleFa
                }

                // Set profile name from Profile list
                item.profile?.firstOrNull()?.let { profile ->
                    infoTxt.text = profile.name
                }

               /* val bgColorResId =
                    if (bindingAdapterPosition % 2 == 0) R.color.white else R.color.Gray_x11
                root.setBackgroundColor(ContextCompat.getColor(context, bgColorResId))*/
            }
        }
    }

    fun setData(data: List<OtherCrewData>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}
