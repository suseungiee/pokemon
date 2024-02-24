package com.seungsu.pokemon.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seungsu.pokemon.presentation.databinding.HolderLoadFooterBinding

class LoadAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoadViewHolder(HolderLoadFooterBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        with(holder.binding) {
            pbLoading.isVisible = loadState is LoadState.Loading
            groupContent.isVisible = loadState is LoadState.Error
            btnRetry.setOnClickListener { retry.invoke() }
        }
    }
}

class LoadViewHolder(
    val binding: HolderLoadFooterBinding
) : RecyclerView.ViewHolder(binding.root)
