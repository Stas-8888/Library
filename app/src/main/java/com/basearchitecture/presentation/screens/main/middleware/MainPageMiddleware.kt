package com.basearchitecture.presentation.screens.main.middleware

import com.basearchitecture.presentation.base.mvi.Middleware
import com.basearchitecture.presentation.screens.main.mvi.MainPageAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Middleware for Main Page screen.
 */
//class MainPageMiddleware : Middleware<MainPageAction>() {
//
//    override suspend fun effect(action: MainPageAction): List<MainPageAction>? {
//        return when (action) {
//            is MainPageAction.OnScreenStarted -> {
//                val listAction = listOf(
//                    MainPageAction.Stopped("Stoped"),
//                    MainPageAction.Count("Count")
//                )
//                return listAction
//            }
//
//            else -> null
//        }
//    }
//}
//
//class MainPageMiddleware : Middleware<MainPageAction>() {
//
//    override suspend fun effect(action: MainPageAction) {
//        when (action) {
//            is MainPageAction.OnScreenStarted -> {
//                MainPageAction.Stopped("Stopped")
//                MainPageAction.Count("Count")
//            }
//            else -> {}
//        }
//    }
//}

class MainPageMiddleware : Middleware<MainPageAction>() {
    override suspend fun effect(action: MainPageAction, emitAction: suspend (MainPageAction) -> Unit) {
        when (action) {
            is MainPageAction.OnScreenStarted -> {
                emitAction(MainPageAction.Stopped("Stopped"))
                emitAction(MainPageAction.Count("Count"))
            }
            else -> {}
        }
    }
}

