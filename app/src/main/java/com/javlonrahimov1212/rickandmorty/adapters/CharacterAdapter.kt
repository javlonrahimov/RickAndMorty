package com.javlonrahimov1212.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javlonrahimov1212.rickandmorty.databinding.ItemCharacterBinding
import com.javlonrahimov1212.rickandmorty.models.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters: List<Character> = ArrayList()
    var onReachedEnd: OnReachedEnd? = null
    var onItemClicked: OnItemClicked? = null

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClicked
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindView(characters[position])
        if (position == characters.size - 1) {
            onReachedEnd?.onReachedEnd()
        }
    }

    override fun getItemCount() = characters.size
}

class CharacterViewHolder(
    private val itemBinding: ItemCharacterBinding,
    private var onItemClicked: OnItemClicked?
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindView(character: Character) {
        itemBinding.character = character
        itemBinding.executePendingBindings()
        itemBinding.root.setOnClickListener {
            onItemClicked?.onClick()
        }
    }
}

interface OnReachedEnd {
    fun onReachedEnd()
}

interface OnItemClicked {
    fun onClick()
}
