package com.curso.android.myapplication.room_4.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.database.WordEntity
import com.curso.android.myapplication.room_4.databinding.ItemWordBinding
import com.curso.android.myapplication.room_4.ui.home.OnItemClickListener
import java.text.SimpleDateFormat
import java.util.*

class WordListAdapter internal constructor(private val listener: OnItemClickListener):ListAdapter<WordEntity, WordListAdapter.WordViewHolder>(WordsComparator()) {

    inner class WordViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val binding = ItemWordBinding.bind(view)

        fun bind(word:WordEntity){
            binding.tvWord.text = word.word
            binding.tvFecha.text = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(word.fecha)
            Log.d("TAG","FechaRecicler: ${word.fecha}")

            val paises = arrayOf("Argentina", "Chile", "Paraguay", "Bolivia", "Peru", "Ecuador", "Brasil", "Colombia", "Venezuela", "Uruguay")
            var habitantes = arrayOf(40_000_000, 17_000_000, 6_500_000, 10_000_000, 30_000_000, 14_000_000, 183_000_000, 44_000_000, 31_000_000, 3_500_000)
            //val adaptador = ArrayAdapter(this.requireActivity(),android.R.layout.simple_list_item_1,paises)
            //binding.lvActividades.adapter = adaptador



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
