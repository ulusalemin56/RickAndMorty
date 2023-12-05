package com.example.rickandmorty.ui.detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailBinding
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.util.constants.Resource
import com.example.rickandmorty.util.extension.lifecycleScopeLaunch
import com.example.rickandmorty.util.extension.loadImage
import com.example.rickandmorty.util.extension.showMotionToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import www.sanju.motiontoast.MotionToastStyle
import okio.IOException

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
        viewLifecycleOwner.lifecycleScopeLaunch {
            viewModel.itemCharacter.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        statusVisibleOfLoading()
                    }

                    is Resource.Success -> {
                        delay(300)
                        statusVisibleOfSuccess()
                        val data = resource.data
                        bindingDataToUI(data)
                    }

                    is Resource.Error -> {
                        val err = resource.throwable
                        logicOfError(err)
                    }
                }
            }
        }
    }

    private fun statusVisibleOfLoading() {
        with(binding) {
            charactersContainerShimmer.isVisible = true
            charactersContainerShimmer.startShimmer()
            titleOfCharacter.isVisible = false
            characterImageCardView.isVisible = false
            subTitleLinearlayout.isVisible = false
        }
    }

    private fun statusVisibleOfSuccess() {
        with(binding) {
            charactersContainerShimmer.stopShimmer()
            charactersContainerShimmer.isVisible = false
            titleOfCharacter.isVisible = true
            characterImageCardView.isVisible = true
            subTitleLinearlayout.isVisible = true
        }
    }

    private fun bindingDataToUI(data: CharacterItemUI) {
        with(binding) {
            with(data) {
                titleOfCharacter.text = name
                characterImage.loadImage(image)
                statusOfCharacter.text = status
                speciesOfCharacter.text = species
                genderOfCharacter.text = gender
                locationOfCharacter.text = locationName
            }
        }
    }

    private fun logicOfError(err: Throwable) {
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