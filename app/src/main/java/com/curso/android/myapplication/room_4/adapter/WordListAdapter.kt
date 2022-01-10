package com.curso.android.myapplication.room_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.curso.android.myapplication.room_2.database.WordEntity
import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.databinding.ItemWordBinding
import com.curso.android.myapplication.room_4.ui.home.OnItemClickListener

class WordListAdapter internal constructor(private val listener: OnItemClickListener):ListAdapter<WordEntity, WordListAdapter.WordViewHolder>(WordsComparator()) {

    inner class WordViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val binding = ItemWordBinding.bind(view)

        fun bind(word:WordEntity){
            binding.tvWord.text = word.word
            binding.tvFecha.text = word.fecha
            //binding.tvNumHoras.text = word.hours
        }

        fun setListener(word: WordEntity){
            binding.containerMain.setOnClickListener{
                listener.onItemClick(word)
            }
            binding.containerMain.setOnLongClickListener {
                listener.onLongItemClick(word)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_word,parent,false)
        return WordViewHolder(view)

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.setListener(current)
    }
}

class WordsComparator:DiffUtil.ItemCallback<WordEntity>() {
    override fun areItemsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
        return oldItem.word == newItem.word
    }

}
