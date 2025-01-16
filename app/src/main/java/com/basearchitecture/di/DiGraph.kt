package com.basearchitecture.di

import android.content.Context
import com.basearchitecture.di.module.featureModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * Singleton for handling DI modules
 */
object DiGraph {

    /**
     * Create and start DI graph.
     *
     * @param context - [Context] application context
     */
    fun start(context: Context) {
        startKoin {
            androidContext(context)
//            if (BuildConfig.DEBUG) androidLogger(level = Level.ERROR)
            modules(diModules)
        }
    }

    private val diModules = listOf(
        *featureModules
    )
}
