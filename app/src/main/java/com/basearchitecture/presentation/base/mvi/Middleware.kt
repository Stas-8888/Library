package com.basearchitecture.presentation.base.mvi

import kotlinx.coroutines.flow.Flow

/**
 * Base class which represent Middleware for MVI pattern.
 * Where [A] - is Action
 */
//abstract class Middleware<A> {
//    abstract suspend fun effect(action: A): List<A>?
//
//    suspend operator fun invoke(action: A): List<A>? = effect(action)
//}
//abstract class Middleware<A> {
//    abstract suspend fun effect(action: A)
//
//    suspend operator fun invoke(action: A) = effect(action)
//}

abstract class Middleware<A> {
    abstract suspend fun effect(action: A, emitAction: suspend (A) -> Unit)

    suspend operator fun invoke(action: A, emitAction: suspend (A) -> Unit) {
        effect(action, emitAction)
    }
}
