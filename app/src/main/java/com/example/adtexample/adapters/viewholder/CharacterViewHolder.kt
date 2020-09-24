package com.example.adtexample.adapters.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.adtexample.databinding.ListCharacterBinding
import com.example.adtexample.model.RMCharacter

class CharacterViewHolder(val binding : ListCharacterBinding, itemView: View) : RecyclerView.ViewHolder(itemView) {
    var character: RMCharacter? = null
}