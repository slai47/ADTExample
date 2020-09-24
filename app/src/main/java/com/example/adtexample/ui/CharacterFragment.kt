package com.example.adtexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adtexample.R
import com.example.adtexample.databinding.FragmentCharacterBinding
import com.example.adtexample.model.RMCharacter

class CharacterFragment : Fragment() {

    companion object {
        val EXTRA_CHARACTER = "character"
    }

    lateinit var binding : FragmentCharacterBinding

    var character: RMCharacter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.bind(inflater.inflate(R.layout.fragment_character, container, false))
        arguments?.let {
            character = it.getParcelable<RMCharacter>(EXTRA_CHARACTER)
        }
        character?.let {
            binding.character = character
        }
        binding.root.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        return binding.root
    }



}