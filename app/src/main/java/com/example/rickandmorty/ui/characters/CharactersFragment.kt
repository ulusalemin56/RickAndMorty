package com.example.rickandmorty.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.util.extension.lifecycleScopeLaunchForFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter: CharactersAdapter by lazy { CharactersAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        whenFocusBehaviorOfSearchBox()
        initCollect()
        return binding.root
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScopeLaunchForFragment {
            with(binding) {
                viewModel.allCharacters.collect { pagingData ->
                    charactersRecyclerView.adapter = charactersAdapter
                    charactersAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun whenFocusBehaviorOfSearchBox() {
        binding.searchBarEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.filterRadioGroup.visibility = View.VISIBLE
            }
        }
    }

}