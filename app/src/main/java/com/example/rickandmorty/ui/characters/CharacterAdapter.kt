package com.example.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.databinding.ItemCharacterBinding

class CharactersAdapter(
    val characters: List<Result?>
) : Adapter<CharactersAdapter.CharactersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.CharactersViewHolder, position: Int) {
       holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    inner class CharactersViewHolder(private val binding: ItemCharacterBinding) : ViewHolder(binding.root) {
        fun bind(result: Result?) {
            with(binding) {
                titleOfCharacter.text = result?.name
                statusOfCharacter.text = result?.status
                speciesOfCharacter.text = result?.species
                Glide.with(characterImage.context).load(result?.image).into(characterImage)
            }

        }
    }
}