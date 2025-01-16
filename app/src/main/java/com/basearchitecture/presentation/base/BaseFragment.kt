package com.basearchitecture.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.basearchitecture.presentation.base.mvi.MviView
import com.basearchitecture.presentation.base.mvi.News
import com.basearchitecture.presentation.base.mvi.State

/**
 * Base class for all Fragments.
 *
 * @param bindingInflater - Inflater for getting binding
 */
abstract class BaseFragment<VM : ViewModel, Binding : ViewBinding, S : State, N : News>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> Binding
) : Fragment(), MviView<S, N> {

    protected abstract val viewModel: VM
    private var _binding: Binding? = null
    protected val binding get() = _binding

    /**
     * Set [Boolean] for show or hide modal dialog with progress bar.
     */
    protected var showModalProgress: Boolean = false
        set(value) {
            if (value == field) return else field = value
//            if (value) showLoader() else hideLoader()
        }

    /**
     * Colorize navigation bar color.
     * Set theme to status/navigation bar.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
//        with(requireActivity()) {
//            setStatusBarTheme()
//            setNavigationBarTheme()
//            colorizeNavigationBarColors(R.color.grey_100)
//        }
    }

    /**
     * Set binding to Fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding?.root
    }

    /**
     * Clear binding from Fragment.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Show / hide loader according to [isLoading].
     * @param isLoading [Boolean] need show loader or hide.
     */
    protected fun renderLoader(isLoading: Boolean) {
        showModalProgress = isLoading
    }
}
