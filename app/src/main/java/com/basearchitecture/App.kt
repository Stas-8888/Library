package com.basearchitecture

import android.app.Application
import com.basearchitecture.di.DiGraph

/**
 * Application class. Used to initialize [DiGraph].
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DiGraph.start(this)
    }
}
