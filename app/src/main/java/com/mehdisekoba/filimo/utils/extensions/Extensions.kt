package com.mehdisekoba.filimo.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation.AnimationListener
import android.widget.ImageView
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import com.mehdisekoba.filimo.R
import com.rubensousa.decorator.ColumnProvider
import com.rubensousa.decorator.GridMarginDecoration
import com.rubensousa.decorator.LinearMarginDecoration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun ImageView.loadImage(url: String) {
    val progressDrawable =
        CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            setColorSchemeColors(ContextCompat.getColor(context, R.color.Gold))
            start()
        }
    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(progressDrawable)
        .into(this)
}

fun View.showCustomSnackbar(
    message: String,
    actionText: String,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, message, length)
    snackbar.apply {
        setAction(actionText) {
            action?.invoke()
        }
        setActionTextColor(ContextCompat.getColor(context, R.color.white))
        setBackgroundTint(ContextCompat.getColor(context, R.color.Gold))
    }
    val layoutParams = snackbar.view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.setMargins(0, 0, 0, 100)
    snackbar.view.layoutParams = layoutParams
    snackbar.show()
}

fun RecyclerView.setupRecyclerview(
    myLayoutManager: RecyclerView.LayoutManager,
    myAdapter: RecyclerView.Adapter<*>
) {
    this.apply {
        layoutManager = myLayoutManager
        setHasFixedSize(true)
        adapter = myAdapter

    }
}

fun ShimmerFrameLayout.toggleVisibility(shouldShow: Boolean) {
    if (shouldShow) {
        startShimmer()
        isVisible = true
    } else {
        stopShimmer()
        hideShimmer()
        isVisible = false
    }
}

fun Resources.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        displayMetrics
    ).toInt()
}

fun Context.getGridHorizontalMarginDecoration() =
    GridMarginDecoration(
        horizontalMargin = this.resources.dpToPx(4),
        verticalMargin = this.resources.dpToPx(4),
        columnProvider = object : ColumnProvider {
            override fun getNumberOfColumns(): Int = 4
        },
        orientation = RecyclerView.HORIZONTAL
    )

fun Context.getListHorizontalMarginDecoration(
    @Px leftMargin: Int,
    @Px topMargin: Int,
    @Px rightMargin: Int,
    @Px bottomMargin: Int,
) = LinearMarginDecoration(
    leftMargin = resources.dpToPx(leftMargin),
    topMargin = resources.dpToPx(topMargin),
    rightMargin = resources.dpToPx(rightMargin),
    bottomMargin = resources.dpToPx(bottomMargin),
    orientation = RecyclerView.HORIZONTAL
)

fun <T : ViewBinding> Fragment.handleAnimation(binding: T) {
    val animationView = binding.root.findViewById<LottieAnimationView>(R.id.animation_lay)
    animationView?.addAnimatorListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            lifecycleScope.launch {
                delay(500)
                animationView.playAnimation()
            }
        }
    })
}

