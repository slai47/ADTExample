package com.example.adtexample.model.responses

import com.example.adtexample.model.RMCharacter

data class CharacterResponseObject(
    val info : Meta,
    val results : List<RMCharacter>)