package com.basearchitecture.presentation.base.mvi

/**
 * Base interface which represent View for MVI pattern.
 * Where [S] - is State, [N] - News
 */
interface MviView<S : State, N : News> {

    /**
     * Handle incoming [S] state
     */
    fun renderState(state: S)

    /**
     * Handle incoming [N] new
     */
    fun renderNews(new: N)
}
