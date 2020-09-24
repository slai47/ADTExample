package com.example.adtexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adtexample.R
import com.example.adtexample.adapters.viewholder.CharacterViewHolder
import com.example.adtexample.databinding.ListCharacterBinding
import com.example.adtexample.model.RMCharacter

class CharacterAdapter(val callback: ((RMCharacter) -> Unit)) : RecyclerView.Adapter<CharacterViewHolder>() {

    private val RMCharacters : MutableList<RMCharacter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ListCharacterBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.list_character, parent, false))
        return CharacterViewHolder(binding, binding.root)
    }

    override fun getItemCount(): Int {
        return RMCharacters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = RMCharacters[position]
        holder.binding.model = character
        holder.character = character
        holder.itemView.tag = holder
        holder.itemView.setOnClickListener { view ->
            val viewHolder = view.tag as? CharacterViewHolder
            viewHolder?.character?.let{
                callback.invoke(it)
            }
        }
    }

    fun setCharacters(newRMCharacters: List<RMCharacter>) {
        RMCharacters.clear()
        RMCharacters.addAll(newRMCharacters)
        notifyDataSetChanged()
    }

    fun addCharacters(newRMCharacters: List<RMCharacter>) {
        val new = RMCharacters.size + 1
        RMCharacters.addAll(newRMCharacters)
        notifyItemRangeInserted(new, new + newRMCharacters.size)
    }

    fun getCharacters() = RMCharacters

}