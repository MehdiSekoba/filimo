package com.mehdisekoba.filimo.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.stored.ThemeManager
import com.mehdisekoba.filimo.databinding.ActivityMainBinding
import com.mehdisekoba.filimo.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {
    //binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var themeManager: ThemeManager

    //other
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomNav) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.updatePadding(
                left = bars.left,
                top = bars.top,
                right = bars.right,
                bottom = bars.bottom,
            )
            WindowInsetsCompat.CONSUMED
        }
        //only Rtl
        window.apply {
            decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        //init
        navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        // Bottom nav menu
        binding.bottomNav.apply {
            setupWithNavController(navHost.navController)
            // Disable double click on items
            setOnNavigationItemReselectedListener {}
        }
        // Gone bottom menu
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.apply {
                when (destination.id) {
                    R.id.homeFragment -> bottomNav.isVisible = true
                    R.id.categoryFragment -> bottomNav.isVisible = true
                    R.id.exploreFragment -> bottomNav.isVisible = true
                    R.id.profileFragment -> bottomNav.isVisible = true
                    else -> bottomNav.isVisible = false
                }
            }
        }
        //set theme
        lifecycleScope.launch {
            themeManager.isDarkTheme.collect { isDarkTheme ->
                if (isDarkTheme) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    window.setBackgroundDrawableResource(R.color.Eerie_Black)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    window.setBackgroundDrawableResource(R.color.white)

                }
            }
        }
    }

    override fun onNavigateUp(): Boolean =
        navHost.navController.navigateUp() || super.onNavigateUp()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}