package com.clevertec_mobilelab_2022_alex_lukanev.task6.data.di

import com.clevertec_mobilelab_2022_alex_lukanev.task6.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}