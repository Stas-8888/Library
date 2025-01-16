package com.basearchitecture.presentation.screens.main.mvi

import com.basearchitecture.presentation.base.mvi.Store
import com.basearchitecture.presentation.screens.main.middleware.MainPageMiddleware

/**
 * Store for Main Page Screen. Contains all [middlewares] and [reducer].
 */
open class MainPageStore(
) : Store<MainPageState, MainPageAction, MainPageNews>() {

    init {
        middlewares = listOf(
            MainPageMiddleware()
        )
        reducer = MainPageReducer()
    }
}
