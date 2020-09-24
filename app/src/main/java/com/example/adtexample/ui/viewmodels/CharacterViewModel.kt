package com.example.adtexample.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CharacterViewModel : ViewModel() {

    private val _location = MutableLiveData<String>()
    val location : LiveData<String>
        get() = _location

    val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name
}