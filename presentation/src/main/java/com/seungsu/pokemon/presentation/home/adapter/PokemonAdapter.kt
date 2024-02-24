package com.seungsu.pokemon.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.seungsu.pokemon.presentation.databinding.HolderPokemonItemBinding
import com.seungsu.pokemon.presentation.model.Pokemon

class PokemonAdapter(
    private val onItemClickListener: (Int) -> Unit
): PagingDataAdapter<Pokemon, PokemonViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        return PokemonViewHolder(
            HolderPokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(
                oldItem: Pokemon,
                newItem: Pokemon
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Pokemon,
                newItem: Pokemon
            ): Boolean = oldItem == newItem
        }
    }
}

class PokemonViewHolder(
    private val binding: HolderPokemonItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Pokemon) {
        with(binding) {
            ivPokemon.load(item.imageUrl)
            tvPokemonName.text = item.name
            tvPokemonDetail.apply {
                text = item.detail
                isVisible = item.detail.isNotEmpty()
            }
            root.setOnClickListener { onItemClickListener(item.id) }
        }
    }
}
