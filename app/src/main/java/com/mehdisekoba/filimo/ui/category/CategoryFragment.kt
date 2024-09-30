package com.mehdisekoba.filimo.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.category.ResponseCategory
import com.mehdisekoba.filimo.databinding.FragmentCategoryBinding
import com.mehdisekoba.filimo.ui.category.adapter.AdapterCategory
import com.mehdisekoba.filimo.utils.base.BaseFragment
import com.mehdisekoba.filimo.utils.extensions.handleAnimation
import com.mehdisekoba.filimo.utils.extensions.setupRecyclerview
import com.mehdisekoba.filimo.utils.extensions.showCustomSnackbar
import com.mehdisekoba.filimo.utils.extensions.toggleVisibility
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCategoryBinding
        get() = FragmentCategoryBinding::inflate

    @Inject
    lateinit var category: AdapterCategory

    //other
    private val viewModel by viewModels<CategoryViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check internet
        binding.apply {
            //hide search
            toolbar.imgSearch.isVisible = false
            intentLay.isVisible = !isNetworkAvailable
            lifecycleScope.launch {
                if (isNetworkAvailable) {
                    loadCategory()
                    shimmerView.toggleVisibility(true)
                } else {
                    handleAnimation(binding)
                    shimmerView.toggleVisibility(false)
                }
            }
        }

    }

    private fun loadCategory() {
        binding.apply {
            viewModel.categoryData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        shimmerView.toggleVisibility(true)
                        RcCategory.isVisible = false
                    }

                    is NetworkRequest.Success -> {
                        shimmerView.toggleVisibility(false)
                        RcCategory.isVisible = true
                        response.data?.data?.let { data ->
                            initRecyclerview(data)

                        }
                    }

                    is NetworkRequest.Error -> {
                        shimmerView.toggleVisibility(false)
                        RcCategory.isVisible = false
                    }

                }
            }
        }
    }

    private fun initRecyclerview(data: List<ResponseCategory.Data>) {
        binding.apply {
            category.setData(data)
            RcCategory.setupRecyclerview(
                LinearLayoutManager(requireContext()),
                category
            )
        }
        //click
        category.setOnItemClickListener {
            val direction = CategoryFragmentDirections.actionToDetailCategories(it)
            findNavController().navigate(direction)
        }
    }

}