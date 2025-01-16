package com.basearchitecture.di.module

import com.basearchitecture.presentation.screens.main.MainPageViewModel
import com.basearchitecture.presentation.screens.main.mvi.MainPageStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainPageStoreModule = module {
    factory {
        MainPageStore()
    }
}
val mainPageViewModule = module {
    viewModel { MainPageViewModel(get()) }
}

val featureModules = arrayOf(
    mainPageStoreModule,
    mainPageViewModule
)
