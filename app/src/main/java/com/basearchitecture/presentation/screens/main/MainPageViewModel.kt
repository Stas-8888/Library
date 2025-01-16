package com.basearchitecture.presentation.screens.main

import com.basearchitecture.presentation.base.BaseViewModel
import com.basearchitecture.presentation.screens.main.mvi.MainPageAction
import com.basearchitecture.presentation.screens.main.mvi.MainPageNews
import com.basearchitecture.presentation.screens.main.mvi.MainPageState
import com.basearchitecture.presentation.screens.main.mvi.MainPageStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * View Model for Main Page Screen
 */
class MainPageViewModel(
    mainPageStore: MainPageStore
) : BaseViewModel<MainPageState, MainPageAction, MainPageNews>() {

    override val stateFlow = MutableStateFlow(MainPageState())
    override val actionFlow = MutableSharedFlow<MainPageAction?>()
    override val newsFlow = MutableSharedFlow<MainPageNews>()

    override val store: MainPageStore = mainPageStore
}
