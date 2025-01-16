package com.basearchitecture.presentation.screens.main.mvi

import com.basearchitecture.presentation.base.mvi.State

/**
 * Class with [State] for Main Page Screen
 */
data class MainPageState(
    val toolbarTitle: String = "",
    val title: String = ""
) : State
