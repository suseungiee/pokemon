package com.seungsu.pokemon.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.seungsu.pokemon.presentation.databinding.ProgressDialogBinding
import com.seungsu.pokemon.presentation.theme.PokemonTheme

abstract class ComposableFragment<
        I : ViewIntent,
        S : ViewState,
        E : ViewEffect> : Fragment() {

    abstract val viewModel: MVIViewModel<I, S, E>

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

    @Composable
    abstract fun RootContent()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PokemonTheme {
                    RootContent()
                }
            }
        }
    }
}
