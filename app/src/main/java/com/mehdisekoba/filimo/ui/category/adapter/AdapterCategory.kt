package com.mehdisekoba.filimo.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.mehdisekoba.filimo.data.model.category.ResponseCategory.Data
import com.mehdisekoba.filimo.databinding.ItemCategoryBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import com.mehdisekoba.filimo.utils.other.TicketEdgeTreatment
import javax.inject.Inject

class AdapterCategory @Inject constructor() : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {
    private var items = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            //initViews
            binding.apply {
                //load data
                item.attributes?.let {
                    imgPoster.loadImage(it.img!!)
                    //rounded poster
                    val shapePathModel =
                        ShapeAppearanceModel
                            .Builder()
                            .setAllCorners(CornerFamily.ROUNDED, 36f)
                            .setRightEdge(TicketEdgeTreatment(36f))
                            .setLeftEdge(TicketEdgeTreatment(36f))
                            .build()
                    imgPoster.shapeAppearanceModel = shapePathModel
                }
                //click
                root.setOnClickListener {
                    onItemClickListener?.let { item.attributes?.titleEn?.let { it1 -> it(it1) } }
                }
            }
        }

    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Data>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}