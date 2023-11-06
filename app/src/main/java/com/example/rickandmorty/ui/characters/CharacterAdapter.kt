package com.example.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.model.CharacterItemUI

class CharactersAdapter(

) : PagingDataAdapter<CharacterItemUI, CharactersAdapter.CharactersViewHolder>(Comparator) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.CharactersViewHolder, position: Int) {
       getItem(position)?.let {
           holder.bind(it)
       }
    }

    inner class CharactersViewHolder(private val binding: ItemCharacterBinding) : ViewHolder(binding.root) {
        fun bind(characterItem: CharacterItemUI) {
            with(binding) {
                titleOfCharacter.text = characterItem.name
                statusOfCharacter.text = characterItem.status
                speciesOfCharacter.text = characterItem.species
                Glide.with(characterImage.context).load(characterItem.image).into(characterImage)
            }
        }
    }

    object Comparator : DiffUtil.ItemCallback<CharacterItemUI>() {
        override fun areItemsTheSame(oldItem: CharacterItemUI, newItem: CharacterItemUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterItemUI,
            newItem: CharacterItemUI
        ): Boolean {
            return oldItem == newItem
        }

    }
}