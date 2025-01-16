package com.basearchitecture.presentation.screens.main.mvi

import com.basearchitecture.presentation.base.mvi.News

/**
 * Class with [News] for Main Page Screen
 */
sealed class MainPageNews : News {

    object OpenErrorScreen : MainPageNews()
}
