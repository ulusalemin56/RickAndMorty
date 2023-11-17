package com.example.rickandmorty.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.util.enums.CharacterTypeEnum
import com.example.rickandmorty.util.extension.lifecycleScopeLaunchForFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(
            ::insertCharacterToFavorites,
            ::deleteCharacterFromFavorites
        )
    }
    private var characterTypeEnum = CharacterTypeEnum.ALL
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        initUI()
        initCollect()
        return binding.root
    }

    private fun initUI() {
        with(binding) {
            searchBarEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.filterRadioGroup.visibility = View.VISIBLE
                }
            }

            filterRadioGroup.setOnCheckedChangeListener { _, checkedID ->
                when (checkedID) {
                    R.id.allRadioButton -> {
                        characterTypeEnum = CharacterTypeEnum.ALL
                    }

                    R.id.aliveRadioButton -> {
                        characterTypeEnum = CharacterTypeEnum.ALIVE
                    }

                    R.id.deadRadioButton -> {
                        characterTypeEnum = CharacterTypeEnum.DEAD
                    }

                    R.id.unknownRadioButton -> {
                        characterTypeEnum = CharacterTypeEnum.UNKNOWN
                    }
                }

                val query = searchBarEditText.text.toString()
                getSearchData(query, characterTypeEnum.status)
            }

            searchBarEditText.addTextChangedListener { editable ->
                editable?.let {
                    getSearchData(it.toString(), characterTypeEnum.status)
                }
            }
        }
    }

    private fun getSearchData(query: String, status: String?) {
        if (query.isNotBlank()) {
            viewModel.getAllCharacters(query, status)
        } else {
            viewModel.getAllCharacters(status = status)
        }
    }

    private fun insertCharacterToFavorites(character: CharacterItemUI) {
        viewModel.insertCharacterToFavorites(character)
    }

    private fun deleteCharacterFromFavorites(character: CharacterItemUI) {
        viewModel.deleteCharacterFromFavorites(character)
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScopeLaunchForFragment {
            with(binding) {
                viewModel.allCharacters.collect { pagingData ->
                    charactersRecyclerView.adapter = charactersAdapter
                    charactersAdapter.submitData(lifecycle, pagingData)
                }
            }
        }
    }
}