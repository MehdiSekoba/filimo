package com.mehdisekoba.filimo.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included.Attributes
import com.mehdisekoba.filimo.databinding.ItemSimilarBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import javax.inject.Inject

class SimilarAdapter @Inject constructor() :
    RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {

    private var items = emptyList<Attributes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Attributes) {
            //initViews
            binding.apply {
                txtName.text = item.movieTitle
                item.pic?.movieImgB?.let { imgMovies.loadImage(it) }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!, item)
                    }
                }
            }
        }

    }

    private var onItemClickListener: ((Int, Attributes) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int, Attributes) -> Unit) {
        onItemClickListener = listener
    }


    fun setData(items: List<Attributes>) {
        val adapterDiffUtils = BaseDiffUtils(this.items, items)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        this.items = items
        diffUtils.dispatchUpdatesTo(this)
    }
}
