package com.example.rickandmorty.ui.detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.FragmentDetailBinding
import com.example.rickandmorty.util.constants.Resource
import com.example.rickandmorty.util.extension.lifecycleScopeLaunch
import com.example.rickandmorty.util.extension.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getItemCharacter(args.id)
        initCollect()
    }

    private fun initCollect() {
        with(binding) {
            viewLifecycleOwner.lifecycleScopeLaunch {
                viewModel.itemCharacter.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            val data = resource.data
                            with(data) {
                                titleOfCharacter.text = name
                                characterImage.loadImage(image)
                                statusOfCharacter.text = status
                                speciesOfCharacter.text = species
                                genderOfCharacter.text = gender
                                locationOfCharacter.text = locationName
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