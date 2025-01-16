package com.basearchitecture.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.basearchitecture.presentation.base.mvi.Action
import com.basearchitecture.presentation.base.mvi.MviView
import com.basearchitecture.presentation.base.mvi.News
import com.basearchitecture.presentation.base.mvi.State
import com.basearchitecture.presentation.base.mvi.Store
import com.basearchitecture.presentation.utils.extension.launchWhenStarted
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Base class which represent ViewModel according to MVI pattern.
 * Also handle Observer case.
 * Where [S] - is State, [A] - Action, [N] - News.
 */
abstract class BaseViewModel<S : State, A : Action, N : News> : ViewModel() {

    abstract val stateFlow: MutableStateFlow<S>
    abstract val newsFlow: MutableSharedFlow<N>
    abstract val actionFlow: MutableSharedFlow<A?>
    abstract val store: Store<S, A, N>

    /**
     * Emit new [A] action(s) to [actionFlow] using ViewModel scope.
     * @param action - [A].
     */
    fun obtainAction(action: A) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    /**
     * Emit new [S] state to [stateFlow] in Scope [viewModelScope].
     * @param state - [S].
     */
    fun obtainState(state: S) {
        viewModelScope.launch {
            stateFlow.emit(state)
        }
    }

    /**
     * Bind flows to [LifecycleOwner].
     * @param lifecycleOwner [LifecycleOwner] LifecycleOwner.CoroutineScope tied to a Lifecycle.
     * @param mviView [MviView] View in MVI pattern.
     */
    fun bind(lifecycleOwner: LifecycleOwner, mviView: MviView<S, N>) {
        stateFlow
            .onEach(mviView::renderState)
            .launchWhenStarted(lifecycleOwner)

        newsFlow
            .onEach(mviView::renderNews)
            .launchWhenStarted(lifecycleOwner)

        with(lifecycleOwner.lifecycleScope) {
            launch {
                actionFlow.collect { action ->
                    action?.let {
                        store.middlewares.forEach { middleware ->
                            launch(Dispatchers.Default) {
                                middleware(it) { emittedAction ->
                                    actionFlow.emit(emittedAction)
                                }
                            }
                        }
                        val reduced = store.reducer(stateFlow.value, it)
                        reduced.first?.let { state ->
                            stateFlow.value = state
                        }
                        reduced.second?.let { news ->
                            newsFlow.emit(news)
                        }
                    }
                }
            }
            store.observer?.let { observers ->
                observers.forEach {
                    launch {
                        it.observerFlow.collect { action ->
                            actionFlow.emit(action)
                        }
                    }
                }
            }
            store.observer?.forEach { observer ->
                launch { observer.subscribeOnGlobal() }
                lifecycleOwner.lifecycle.addObserver(
                    observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_DESTROY) launch { observer.unsubscribeOnGlobal() }
                    }
                )
            }
        }
    }
}
