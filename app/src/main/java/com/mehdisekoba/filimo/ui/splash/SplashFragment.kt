package com.mehdisekoba.filimo.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mehdisekoba.filimo.BuildConfig
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.home.ResponseHome
import com.mehdisekoba.filimo.databinding.FragmentSplashBinding
import com.mehdisekoba.filimo.utils.MoviesCategories
import com.mehdisekoba.filimo.utils.base.BaseFragment
import com.mehdisekoba.filimo.utils.extensions.setupRecyclerview
import com.mehdisekoba.filimo.utils.extensions.toggleVisibility
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.utils.view.layoutmanager.AutoScrollHorizontalGridLayoutManager
import com.mehdisekoba.filimo.viewmodel.HomeViewModel
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    @Inject
    lateinit var splashAdapter: SplashAdapter

    private var isPermission = false
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setupUI()
        }
        getPermission()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        binding.apply {
            txtVersion.text = "${getString(R.string.app_version)} : ${BuildConfig.VERSION_NAME}"
            if (isNetworkAvailable) {
                onlineLay.isVisible = true
                offlineLay.isVisible = false
                onlineTxtVersion.text = "${getString(R.string.app_version)} : ${BuildConfig.VERSION_NAME}"

                onlineImgLogo.animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.anticlockwise)
                loadSplashData()
            } else {
                onlineLay.isVisible = false
                offlineLay.isVisible = true
                shimmerView.toggleVisibility(false)
                imgLogo.animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.anticlockwise)
            }

            lifecycleScope.launch {
                delay(5000)
                navigateToNextScreen()
            }
        }
    }

    private fun loadSplashData() {
        binding.apply {
            viewModel.getMoviesData(getRandomCategory())
                .observe(viewLifecycleOwner) { response ->
                    when (response) {
                        is NetworkRequest.Loading -> shimmerView.toggleVisibility(true)

                        is NetworkRequest.Success -> {
                            shimmerView.toggleVisibility(false)
                            response.data?.included?.let { included ->
                                if (included.isNotEmpty()) {
                                    initSplashRecyclers(included.mapNotNull { it.attributes })
                                }
                            }
                        }

                        is NetworkRequest.Error -> shimmerView.toggleVisibility(false)

                    }
                }
        }
    }

    private fun initSplashRecyclers(attributes: List<ResponseHome.Included.Attributes>) {
        splashAdapter.setData(attributes)
        binding.rcOnline.setupRecyclerview(
            AutoScrollHorizontalGridLayoutManager(
                requireContext(),
                2500f
            ), splashAdapter
        )
        binding.rcOnline.startScrolling()
    }

    private fun navigateToNextScreen() {
        findNavController().popBackStack(R.id.splashFragment, true)
        if (isPermission) {
            val direction = SplashFragmentDirections.actionSplashToHome()
            findNavController().navigate(direction)
        }
    }

    private fun getPermission() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_AUDIO
            )
        } else {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }

        PermissionX.init(this)
            .permissions(permissions)
            .explainReasonBeforeRequest()
            .request { allGranted, _, _ ->
                if (allGranted) {
                    isPermission = true
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.deniedPermission),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
    private fun getRandomCategory(): MoviesCategories {
        val categories = MoviesCategories.entries.filter { it != MoviesCategories.ONLINE_SCREENING }
        return categories.random()
    }


}
