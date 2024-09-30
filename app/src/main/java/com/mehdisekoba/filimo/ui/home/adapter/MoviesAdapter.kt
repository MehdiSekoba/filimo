package com.mehdisekoba.filimo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included.Attributes
import com.mehdisekoba.filimo.databinding.ItemMoviesBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var items = emptyList<Attributes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: Attributes) {
            //initViews
            binding.apply {
                txtName.text = item.movieTitle
                item.pic?.movieImgB?.let { imgPoster.loadImage(it) }
                val categoriesList = item.categories?.map { it.title } ?: emptyList()
                txtGenre.text = categoriesList.joinToString(" - ")
                txtManufacture.text=item.proYear
                //
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


    fun setData(data: List<Attributes>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}
