package com.gekaradchenko.game.taptapproject.di

import com.gekaradchenko.game.taptapproject.timer.GameTimer
import com.gekaradchenko.game.taptapproject.timer.GameTimerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class GameTimerModule {

    @Provides
    fun provideGameTimer(): GameTimer {
        return GameTimerImpl()
    }
}