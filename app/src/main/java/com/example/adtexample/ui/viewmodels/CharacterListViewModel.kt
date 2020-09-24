package com.example.adtexample.ui.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adtexample.backend.RMServer
import com.example.adtexample.model.RMCharacter
import com.example.adtexample.model.responses.Meta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private val _characters = MutableLiveData<List<RMCharacter>>()
    val characters : LiveData<List<RMCharacter>>
        get() = _characters

    var meta : Meta? = null
    var currentPage = 1

    val progress = ObservableField<Int>(View.GONE)

    fun getCharacters(page: Int = 1) {
        currentPage = page
        progress.set(View.VISIBLE)
        viewModelScope.launch(Dispatchers.Default) {
            val retrofit = RMServer.getInstance()
            val result = retrofit.getCharacters(page)

            meta = result.info
            _characters.postValue(result.results)
        }
    }

    // isTrue in kotlin extensions should be used but not enough time to find the right dependency
    fun isCharactersEmpty() : Boolean {
        return _characters.value?.isEmpty() ?: true
    }

    fun storeCharacters(list: List<RMCharacter>?) {
        list?.let {
            _characters.value = it
        }
    }
}