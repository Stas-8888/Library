package com.basearchitecture.presentation.base.mvi

import kotlinx.coroutines.flow.SharedFlow

/**
 * Base interface which represent Observer.
 * Where [A] - Action.
 */
interface Observer<A : Action> {

    val observerFlow: SharedFlow<A>

    /**
     * Function for subscribe to global observers.
     * Called when lifecycleOwner is STARTED.
     */
    suspend fun subscribeOnGlobal()

    /**
     * Function for unsubscribe from global observers.
     * Called when lifecycleOwner is STOPPED.
     */
    fun unsubscribeOnGlobal() {}
}
