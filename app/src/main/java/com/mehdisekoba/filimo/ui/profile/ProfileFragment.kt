package com.mehdisekoba.filimo.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.stored.ThemeManager
import com.mehdisekoba.filimo.databinding.FragmentProfileBinding
import com.mehdisekoba.filimo.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    @Inject
    lateinit var themeManager: ThemeManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe theme preference
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                themeManager.isDarkTheme.collect { isDarkTheme ->
                    if (switchTheme.isChecked != isDarkTheme) {
                        switchTheme.isChecked = isDarkTheme
                    }
                    if (isDarkTheme) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        requireActivity().window.setBackgroundDrawableResource(R.color.Eerie_Black)

                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        requireActivity().window.setBackgroundDrawableResource(R.color.white)

                    }
                }
            }

            switchTheme.setOnCheckedChangeListener { _, isChecked ->
                viewLifecycleOwner.lifecycleScope.launch {
                    themeManager.saveTheme(isChecked)
                }
            }
        }
    }
}
