package com.mehdisekoba.filimo.utils.di

import android.content.Context
import com.mehdisekoba.filimo.utils.other.ScrollStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import kohii.v1.exoplayer.Kohii
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {
    @Provides
    @Singleton
    fun provideKohiiPlayer(@ApplicationContext context: Context): Kohii {
        return Kohii.Builder(context).build()
    }
    @Provides
    @ActivityRetainedScoped
    fun provideScrollStateHolder(): ScrollStateHolder {
        return ScrollStateHolder()
    }
}