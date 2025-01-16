package com.basearchitecture.presentation.screens.main.mvi

import com.basearchitecture.presentation.base.mvi.Action

/**
 * Class with [Action] for Main Page Screen
 */
sealed class MainPageAction : Action {
    // Actions

    object OnScreenStarted : MainPageAction()

    data class Stopped(val d: String) : MainPageAction()
    data class Count(val ds: String) : MainPageAction()
}
