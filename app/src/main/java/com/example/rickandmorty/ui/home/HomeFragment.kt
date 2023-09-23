package com.example.rickandmorty.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rickandmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        with(viewModel) {
            allCharactersLiveData.observe(viewLifecycleOwner) { userResponse ->
                userResponse.results?.forEach { result ->
                   Log.e("HomeFragment", result?.name!!)
                }
            }

            error.observe(viewLifecycleOwner) {
                it.run {

                }
            }
            loading.observe(viewLifecycleOwner) {

            }
        }
    }

}