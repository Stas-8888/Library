package com.basearchitecture.presentation.screens.main

import android.os.Bundle
import android.view.View
import com.basearchitecture.databinding.FragmentMainBinding
import com.basearchitecture.presentation.base.BaseFragment
import com.basearchitecture.presentation.screens.main.mvi.MainPageAction
import com.basearchitecture.presentation.screens.main.mvi.MainPageNews
import com.basearchitecture.presentation.screens.main.mvi.MainPageState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<MainPageViewModel, FragmentMainBinding, MainPageState, MainPageNews>(
        FragmentMainBinding::inflate
    ) {

    override val viewModel by viewModel<MainPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            bind(viewLifecycleOwner, this@MainFragment)
            obtainAction(
                MainPageAction.OnScreenStarted
            )
        }
    }

    override fun renderState(state: MainPageState) {
        binding?.text?.text = state.title + state.toolbarTitle
    }

    override fun renderNews(new: MainPageNews) {
        TODO("Not yet implemented")
    }
}
