package com.mehdisekoba.filimo.ui.category.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails
import com.mehdisekoba.filimo.databinding.FragmentDetailsCategoryBinding
import com.mehdisekoba.filimo.ui.category.details.adapter.CategoryAdapter
import com.mehdisekoba.filimo.ui.home.HomeFragmentDirections
import com.mehdisekoba.filimo.utils.base.BaseFragment
import com.mehdisekoba.filimo.utils.extensions.setupRecyclerview
import com.mehdisekoba.filimo.utils.extensions.toggleVisibility
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsCategoryFragment : BaseFragment<FragmentDetailsCategoryBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentDetailsCategoryBinding
        get() = FragmentDetailsCategoryBinding::inflate

    @Inject
    lateinit var category: CategoryAdapter

    //other
    private val args by navArgs<DetailsCategoryFragmentArgs>()
    private var categoryTag = "0"
    private val viewModel by viewModels<CategoryViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //args
        args.let {
            categoryTag = it.tagId
        }
        if (isNetworkAvailable) {
            //Call api
            if (categoryTag.isNotEmpty()) {
                viewModel.callDetailsCategoryApi(categoryTag)
                loadDetailsCategory()
            }
            binding.imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun loadDetailsCategory() {
        binding.apply {
            viewModel.detailsCategoryData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        shimmerView.toggleVisibility(true)
                        imgBack.isVisible = false
                    }

                    is NetworkRequest.Success -> {
                        shimmerView.toggleVisibility(false)
                        imgBack.isVisible = true
                        response.data?.let { data ->
                            txtTitle.text = data.meta?.title
                            if (data.included!!.isNotEmpty() && data.data!!.isNotEmpty()) {
                                initRecyclerview(data.included, data.data)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        imgBack.isVisible = false
                        shimmerView.toggleVisibility(false)

                    }
                }
            }
        }
    }

    private fun initRecyclerview(
        included: List<ResponseCategoryDetails.Included>,
        data: List<ResponseCategoryDetails.Data>
    ) {
        val includedAttributes = included.mapNotNull { it.attributes }
        category.setData(data, includedAttributes)

        binding.categoryList.setupRecyclerview(LinearLayoutManager(requireContext()), category)

    }


}




