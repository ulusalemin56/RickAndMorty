package com.example.rickandmorty.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.util.constants.Resource
import com.example.rickandmorty.util.extension.getColoreEx
import com.example.rickandmorty.util.extension.getDrawableEx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        whenFocusBehaviorOfSearchBox()
        collectViewModel()
        return binding.root
    }

    private fun collectViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewModel) {
                    allCharacters.collect{resource ->
                        when(resource) {
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                resource.data.results?.let {
                                    val adapter = CharactersAdapter(it)
                                    binding.charactersRecyclerView.adapter = adapter
                                }
                            }
                            is Resource.Error -> {

                            }
                        }
                    }
                }
            }
        }
    }

    private fun whenFocusBehaviorOfSearchBox() {
        binding.searchBarEditText.setOnFocusChangeListener { view, hasFocus ->
            val searchView = (view as EditText)
            if (hasFocus) {
                setBehaviourIconOfSearchBox(
                    searchView,
                    R.color.focus_search_icon
                )
                setBehaviourColorHintTextOfSearchBox(
                    searchView,
                    R.color.focus_search_tint_text
                )
            } else {
                setBehaviourIconOfSearchBox(
                    searchView,
                    R.color.not_focus_search_icon
                )
                setBehaviourColorHintTextOfSearchBox(
                    searchView,
                    R.color.not_focus_search_tint_text
                )
            }
        }
    }

    private fun setBehaviourIconOfSearchBox(searchView: EditText, color: Int) {
        val drawableSearchIcon =
            requireActivity().applicationContext.getDrawableEx(R.drawable.search_icon)
        drawableSearchIcon?.setTint(
            requireActivity().applicationContext.getColoreEx(color)
        )
        searchView.setCompoundDrawablesRelativeWithIntrinsicBounds(
            drawableSearchIcon, null, null, null,
        )
    }

    private fun setBehaviourColorHintTextOfSearchBox(searchView: EditText, color: Int) {
        searchView.setHintTextColor(requireActivity().applicationContext.getColoreEx(color))
    }
}