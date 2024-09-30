package com.mehdisekoba.filimo.ui.category.details.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails.Data
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails.Data.Attributes.Tracker
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails.Included.Attributes
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included
import com.mehdisekoba.filimo.databinding.ItemCategoriesBinding
import com.mehdisekoba.filimo.utils.NOT_SPECIFIED
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.setupRecyclerview
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class CategoryAdapter @Inject constructor(@ApplicationContext val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var items = emptyList<Data>()
    private var dataMovies: List<Attributes>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Data) {
            binding.apply {
                // Extract movie IDs and filter movies
                val tagIds = item.relationships?.movies?.data?.map { it.id.toString() } ?: emptyList()
                val filteredMovies = dataMovies?.filter { movie -> movie.movieId.toString() in tagIds } ?: emptyList()
                val hasFilteredMovies = filteredMovies.isNotEmpty()
                itemTitle.visibility = if (hasFilteredMovies) View.VISIBLE else View.GONE
                showTitle.visibility = if (hasFilteredMovies) View.VISIBLE else View.GONE
                itemSubCatsList.visibility = if (hasFilteredMovies) View.VISIBLE else View.GONE

                if (hasFilteredMovies) {
                    item.attributes?.tracker?.let { tracker ->
                        itemTitle.text = tracker.title
                    }
                    subCategoriesList(filteredMovies, binding)
                }
            }
        }
    }
    private fun subCategoriesList(list: List<Attributes>, binding: ItemCategoriesBinding) {
        val subCategoryAdapter = SubCategoryAdapter()
        subCategoryAdapter.setData(list)
        binding.itemSubCatsList.setupRecyclerview(
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false),
            subCategoryAdapter
        )


    }

    fun setData(data: List<Data>, movies: List<Attributes>) {
        val filteredData = data.filter { it.attributes!!.tracker?.title != NOT_SPECIFIED }
        val adapterDiffUtils = BaseDiffUtils(items, filteredData)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = filteredData
        dataMovies = movies
        diffUtils.dispatchUpdatesTo(this)
    }
}