package com.mehdisekoba.filimo.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.explore.ResponseExplore
import com.mehdisekoba.filimo.databinding.FragmentExploreBinding
import com.mehdisekoba.filimo.ui.explore.adapter.AdapterExplore
import com.mehdisekoba.filimo.utils.base.BaseFragment
import com.mehdisekoba.filimo.utils.extensions.handleAnimation
import com.mehdisekoba.filimo.utils.extensions.showCustomSnackbar
import com.mehdisekoba.filimo.utils.extensions.toggleVisibility
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.viewmodel.ExploreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kohii.v1.core.MemoryMode
import kohii.v1.exoplayer.Kohii
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate

    @Inject
    lateinit var explore: AdapterExplore

    private val viewModel by viewModels<ExploreViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        binding.apply {
            intentLay.isVisible = !isNetworkAvailable
            if (isNetworkAvailable) {
                viewModel.callExploreData()
                shimmerView.toggleVisibility(true)
            } else {
                handleAnimation(binding)
                shimmerView.toggleVisibility(false)
            }
        }
    }

    private fun setupObservers() {
        viewModel.exploreData.observe(viewLifecycleOwner) { response ->
            binding.apply {
                when (response) {
                    is NetworkRequest.Loading -> {
                        shimmerView.toggleVisibility(true)
                    }

                    is NetworkRequest.Success -> {
                        shimmerView.toggleVisibility(false)
                        response.data?.data?.let { data ->
                            initRecyclerView(data)
                        }
                    }

                    is NetworkRequest.Error -> {
                        shimmerView.toggleVisibility(false)

                    }
                }
            }
        }
    }

    private fun initRecyclerView(data: List<ResponseExplore.Data>) {
        val kohii = Kohii[this@ExploreFragment]
        explore.setData(data)
        binding.RcExplore.apply {
            adapter = explore
            kohii.register(this@ExploreFragment, memoryMode = MemoryMode.HIGH).addBucket(this)
            layoutManager = LinearLayoutManager(requireContext())
        }

    }


}

