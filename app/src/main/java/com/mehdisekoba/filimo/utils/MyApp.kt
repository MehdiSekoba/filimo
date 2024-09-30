package com.mehdisekoba.filimo.utils

import android.app.Application
import android.os.Build
import com.mehdisekoba.filimo.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //timber
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        //Calligraphy
        ViewPump.init(
                ViewPump.builder().addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/is_bold.ttf")
                            .build()
                    )
                ).build()
        )
    }
}