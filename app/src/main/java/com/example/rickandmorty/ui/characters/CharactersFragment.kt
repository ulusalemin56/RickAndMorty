package com.example.rickandmorty.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.util.enums.CharacterType
import com.example.rickandmorty.util.extension.lifecycleScopeLaunch
import com.example.rickandmorty.util.extension.showMotionToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import okio.IOException
import www.sanju.motiontoast.MotionToastStyle

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
    private var characterType = CharacterType.ALL
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initCollect()
    }

    private fun initUI() {
        with(binding) {
            searchBarEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.filterRadioGroup.isVisible = true
                }
            }

            filterRadioGroup.setOnCheckedChangeListener { _, checkedID ->
                when (checkedID) {
                    R.id.allRadioButton -> {
                        characterType = CharacterType.ALL
                    }

                    R.id.aliveRadioButton -> {
                        characterType = CharacterType.ALIVE
                    }

                    R.id.deadRadioButton -> {
                        characterType = CharacterType.DEAD
                    }

                    R.id.unknownRadioButton -> {
                        characterType = CharacterType.UNKNOWN
                    }
                }

                val query = searchBarEditText.text.toString()
                getSearchData(query, characterType.status)
            }

            searchBarEditText.addTextChangedListener { editable ->
                editable?.let {
                    getSearchData(it.toString(), characterType.status)
                }
            }
        }
    }

    private fun getSearchData(query: String, status: String?) {
        viewModel.getCharacters(query, status)
    }

    private fun insertCharacterToFavorites(character: CharacterItemUI) {
        viewModel.insertCharacterToFavorites(character)
    }

    private fun deleteCharacterFromFavorites(character: CharacterItemUI) {
        viewModel.deleteCharacterFromFavorites(character)
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScopeLaunch {
            with(binding) {
                viewModel.allCharacters.collectLatest { pagingData ->
                    charactersRecyclerView.adapter = charactersAdapter
                    charactersAdapter.submitData(lifecycle, pagingData)
                    observeStateData()
                }
            }
        }
    }

    private suspend fun observeStateData() {
        charactersAdapter.loadStateFlow.collectLatest { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {

                }

                is LoadState.NotLoading -> {

                }

                is LoadState.Error -> {
                    val err = (loadState.refresh as LoadState.Error).error
                    if (err is IOException) {
                        val title = resources.getString(R.string.connection_error)
                        val description = resources.getString(R.string.internet_error)
                        requireActivity().showMotionToast(
                            title,
                            description,
                            motionStyle = MotionToastStyle.ERROR
                        )
                    } else {
                        val title = resources.getString(R.string.error)
                        val description = err.localizedMessage ?: "Error"
                        requireActivity().showMotionToast(
                            title,
                            description,
                            motionStyle = MotionToastStyle.ERROR
                        )
                    }
                }
            }
        }
    }
}