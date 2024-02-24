package com.seungsu.pokemon.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.seungsu.pokemon.presentation.databinding.ProgressDialogBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class MVIFragment<BINDING: ViewBinding, I: ViewIntent, S: ViewState, E: ViewEffect> (
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> BINDING
): Fragment() {

    abstract val viewModel: MVIViewModel<I, S, E>

    private var _binding: BINDING? = null
    protected val binding: BINDING
        get() = _binding!!

    private val loadingDialog: AppCompatDialog by lazy {
        ProgressDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false)
            .run {
                AppCompatDialog(requireContext())
                    .apply {
                        setCancelable(false)
                        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        setContentView(this@run.root)
                    }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initCollect()
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    open fun initView() {}

    protected fun setIntent(intent: I) {
        viewModel.dispatch(intent)
    }

    abstract fun processState(state: S)

    abstract fun processEffect(effect: E)

    open fun processToastEffect(message: String) {}

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { processState(it) }
                }

                launch {
                    viewModel.effect.collect { processEffect(it) }
                }

                launch {
                    viewModel.toastEffect.collect { processToastEffect(it) }
                }

                launch {
                    viewModel.loadingEffect.collectLatest { isLoading ->
                        if (isLoading) {
                            showLoadingDialog()
                        } else {
                            hideLoadingDialog()
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingDialog() {
        if (loadingDialog.isShowing.not()) {
            loadingDialog.show()
        }
    }

    private fun hideLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
