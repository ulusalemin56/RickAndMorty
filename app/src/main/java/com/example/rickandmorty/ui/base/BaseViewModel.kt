package com.example.rickandmorty.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val error = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()
}