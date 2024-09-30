package com.mehdisekoba.filimo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.home.ResponseHome
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included.Attributes
import com.mehdisekoba.filimo.databinding.FragmentHomeBinding
import com.mehdisekoba.filimo.ui.home.adapter.BannerAdapter
import com.mehdisekoba.filimo.ui.home.adapter.MoviesAdapter
import com.mehdisekoba.filimo.utils.DELAY_TIME
import com.mehdisekoba.filimo.utils.HEADER_SLIDERS
import com.mehdisekoba.filimo.utils.MoviesCategories
import com.mehdisekoba.filimo.utils.REPEAT_TIME
import com.mehdisekoba.filimo.utils.base.BaseFragment
import com.mehdisekoba.filimo.utils.extensions.handleAnimation
import com.mehdisekoba.filimo.utils.extensions.setupRecyclerview
import com.mehdisekoba.filimo.utils.extensions.toggleVisibility
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.utils.other.ScrollStateHolder
import com.mehdisekoba.filimo.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    @Inject
    lateinit var banner: BannerAdapter

    @Inject
    lateinit var specialAdapter: MoviesAdapter

    @Inject
    lateinit var trendingAdapter: MoviesAdapter

    @Inject
    lateinit var newAdapter: MoviesAdapter

    @Inject
    lateinit var filimoAdapter: MoviesAdapter

    @Inject
    lateinit var onlineAdapter: MoviesAdapter

    @Inject
    lateinit var iranianSeriesAdapter: MoviesAdapter

    @Inject
    lateinit var romanticAdapter: MoviesAdapter

    //other
    private lateinit var scrollStateHolder: ScrollStateHolder
    private var autoScrollIndex = 0

    private val viewModel by viewModels<HomeViewModel>()
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::scrollStateHolder.isInitialized)
            scrollStateHolder.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViews
        binding.apply {
            intentLay.isVisible = !isNetworkAvailable
            if (isNetworkAvailable) {
                loadBanner()
                loadMoviesData()
               shimmerView.toggleVisibility(true)
            } else {
                handleAnimation(binding)
                shimmerView.toggleVisibility(false)

            }
            //recreate animation
            layAnimation.txtRetry.setOnClickListener {
                requireActivity().recreate()
            }
            //save
            scrollStateHolder = ScrollStateHolder(savedInstanceState)
        }
    }


    //Banner
    private fun loadBanner() {
        binding.apply {
            viewModel.moviesHomeData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        shimmerView.toggleVisibility(true)
                    }

                    is NetworkRequest.Success -> {
                        shimmerView.toggleVisibility(false)

                        val headersliderIds = response.data?.data?.flatMap { dataItem ->
                            dataItem.relationships?.headersliders?.data?.mapNotNull { it.id }
                                ?: emptyList()
                        } ?: emptyList()
                        val headersliderItems = response.data?.included?.filter { includedItem ->
                            includedItem.type == HEADER_SLIDERS && headersliderIds.contains(
                                includedItem.id
                            )
                        }?.mapNotNull { it.attributes } ?: emptyList()
                        initBannerRecycler(headersliderItems)
                    }

                    is NetworkRequest.Error -> {
                        shimmerView.toggleVisibility(false)

                    }
                }
            }
        }
    }

    private fun initBannerRecycler(headersliderItems: List<Attributes>) {
        banner.setData(headersliderItems)
        binding.apply {
            rcBanner.setupRecyclerview(CarouselLayoutManager(HeroCarouselStrategy()), banner)
            autoScrollPopular(banner)
        }


    }

    private fun autoScrollPopular(adapter: BannerAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                repeat(REPEAT_TIME) {
                    delay(DELAY_TIME)
                    val itemCount = adapter.itemCount
                    if (autoScrollIndex < itemCount) {
                        autoScrollIndex += 1
                    } else {
                        autoScrollIndex = 0
                    }
                    binding.rcBanner.smoothScrollToPosition(autoScrollIndex)
                }
            }
        }
    }

    //special
    private fun loadMoviesData() {
        binding.apply {
            //special
            if (specialLay.parent != null) {
                val specialInflate = specialLay.inflate()
                viewModel.getMoviesData(MoviesCategories.SPECIAL).observe(viewLifecycleOwner) {
                    handleMoviesRequest(
                        it,
                        specialInflate.findViewById(R.id.special_list),
                        specialAdapter,
                        specialInflate.findViewById(R.id.special_title),
                        specialInflate.findViewById(R.id.show_special_title),
                        specialInflate.findViewById(R.id.shimmer_view)
                    )
                }
            }
            //trending
            if (hottestLay.parent != null) {
                val hottestLay = hottestLay.inflate()
                viewModel.getMoviesData(MoviesCategories.HOTTEST).observe(viewLifecycleOwner) {
                    handleMoviesRequest(
                        it,
                        hottestLay.findViewById(R.id.hottest_list),
                        trendingAdapter,
                        hottestLay.findViewById(R.id.hottest_title),
                        hottestLay.findViewById(R.id.show_hottest_title),
                        hottestLay.findViewById(R.id.shimmer_view)
                    )
                }
            }
            //new
            if (newLay.parent != null) {
                val newInflate = newLay.inflate()
                viewModel.getMoviesData(MoviesCategories.NEW_ONES).observe(viewLifecycleOwner) {
                    handleMoviesRequest(
                        it,
                        newInflate.findViewById(R.id.new_list),
                        newAdapter,
                        newInflate.findViewById(R.id.new_title),
                        newInflate.findViewById(R.id.show_new_title),
                        newInflate.findViewById(R.id.shimmer_view)
                    )
                }
            }
            //filimoLay
            if (filimoLay.parent != null) {
                val filimoInflate = filimoLay.inflate()
                viewModel.getMoviesData(MoviesCategories.FILMO_EXCLUSIVE)
                    .observe(viewLifecycleOwner) {
                        handleMoviesRequest(
                            it,
                            filimoInflate.findViewById(R.id.filimo_list),
                            filimoAdapter,
                            filimoInflate.findViewById(R.id.filimo_title),
                            filimoInflate.findViewById(R.id.show_filimo_title),
                            filimoInflate.findViewById(R.id.shimmer_view)
                        )
                    }
            }
            //Online screening
            if (onlineLay.parent != null) {
                val onlineInflate = onlineLay.inflate()
                viewModel.getMoviesData(MoviesCategories.ONLINE_SCREENING)
                    .observe(viewLifecycleOwner) {
                        handleMoviesRequest(
                            it,
                            onlineInflate.findViewById(R.id.online_list),
                            onlineAdapter,
                            onlineInflate.findViewById(R.id.online_title),
                            onlineInflate.findViewById(R.id.show_online_title),
                            onlineInflate.findViewById(R.id.shimmer_view)
                        )
                    }
            }
            //history_iranian_series
            if (iranianLay.parent != null) {
                val iranInflate = iranianLay.inflate()
                viewModel.getMoviesData(MoviesCategories.IRANIAN_SERIES)
                    .observe(viewLifecycleOwner) {
                        handleMoviesRequest(
                            it,
                            iranInflate.findViewById(R.id.iran_list),
                            iranianSeriesAdapter,
                            iranInflate.findViewById(R.id.iran_title),
                            iranInflate.findViewById(R.id.show_iran_title),
                            iranInflate.findViewById(R.id.shimmer_view)
                        )
                    }
            }
            //Romantic
            if (romanticLay.parent != null) {
                val romanticInflate = romanticLay.inflate()
                viewModel.getMoviesData(MoviesCategories.ROMANTIC_SPECIAL)
                    .observe(viewLifecycleOwner) {
                        handleMoviesRequest(
                            it,
                            romanticInflate.findViewById(R.id.romantic_list),
                            romanticAdapter,
                            romanticInflate.findViewById(R.id.romantic_title),
                            romanticInflate.findViewById(R.id.show_romantic_title),
                            romanticInflate.findViewById(R.id.shimmer_view)
                        )
                    }
            }

        }
    }

    //general
    private fun handleMoviesRequest(
        request: NetworkRequest<ResponseHome>,
        recyclerView: RecyclerView,
        adapter: MoviesAdapter,
        title: TextView,
        description: TextView,
        shimmer: ShimmerFrameLayout
    ) {
        when (request) {
            is NetworkRequest.Loading -> {
                shimmer.toggleVisibility(true)
                toggleViewVisibility(title = title, description = description, false)
            }

            is NetworkRequest.Success -> {
                shimmer.toggleVisibility(false)
                toggleViewVisibility(title = title, description = description, true)

                request.data?.let { data ->
                    if (data.included!!.isNotEmpty()) {
                        initMoviesRecyclers(
                            data.included.mapNotNull { it.attributes },
                            recyclerView,
                            adapter
                        )

                    }

                }

            }

            is NetworkRequest.Error -> {
                shimmer.toggleVisibility(false)
                toggleViewVisibility(title = title, description = description, false)
            }
        }
    }

    private fun initMoviesRecyclers(
        mapNotNull: List<Attributes>,
        recyclerView: RecyclerView,
        adapter: MoviesAdapter
    ) {
        adapter.setData(mapNotNull)
        recyclerView.setupRecyclerview(
            LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            ), adapter
        )
        //click
        adapter.setOnItemClickListener { itemId, attributes ->
            val direction = HomeFragmentDirections.actionToDetail(itemId).setData(attributes)
            findNavController().navigate(direction)
        }

    }

    private fun toggleViewVisibility(
        title: TextView,
        description: TextView,
        isShownLoading: Boolean
    ) {
        title.visibility = if (isShownLoading) View.VISIBLE else View.GONE
        description.visibility = if (isShownLoading) View.VISIBLE else View.GONE
    }


}