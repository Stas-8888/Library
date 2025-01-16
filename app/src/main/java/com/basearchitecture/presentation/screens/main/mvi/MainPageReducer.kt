package com.basearchitecture.presentation.screens.main.mvi

import com.basearchitecture.presentation.base.mvi.Reducer

/**
 * Reducer for handling Main Page Screen UI
 * @param labelHelper [LabelHelper] helper for getting translations.
 */
class MainPageReducer(
) : Reducer<MainPageState, MainPageAction, MainPageNews> {

    override suspend fun reduce(
        state: MainPageState,
        action: MainPageAction
    ): Pair<MainPageState?, MainPageNews?> {
        var reducedState: MainPageState? = null
        var reducedNews: MainPageNews? = null
        when (action) {
            is MainPageAction.Stopped -> {
                reducedState = state.copy(
                    toolbarTitle = action.d
                )
            }

            is MainPageAction.Count -> {
                reducedState = state.copy(
                    title = action.ds
                )
            }

            else -> {}
        }

        return reducedState to reducedNews
    }
}
