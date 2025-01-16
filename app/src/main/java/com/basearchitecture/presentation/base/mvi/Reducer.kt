package com.basearchitecture.presentation.base.mvi

/**
 * Base interface which represent Reducer for MVI pattern.
 * Where [S] - is State, [A] - Action, [N] - News
 */
interface Reducer<S : State, A : Action, N : News> {

    suspend fun reduce(state: S, action: A): Pair<S?, N?>

    suspend operator fun invoke(state: S, action: A) = reduce(state, action)
}
