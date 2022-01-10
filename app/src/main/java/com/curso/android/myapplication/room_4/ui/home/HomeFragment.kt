package com.curso.android.myapplication.room_4.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.android.myapplication.room_2.adapter.WordListAdapter
import com.curso.android.myapplication.room_2.database.WordEntity
import com.curso.android.myapplication.room_4.databinding.FragmentHomeBinding
import com.curso.android.myapplication.room_4.utils.InjectorUtils
import java.lang.StringBuilder


class HomeFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var homeViewModel:HomeViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter:WordListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory = InjectorUtils.inyectarHomeViewModelFactory(this.requireContext())
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        homeViewModel.allWords.observe(viewLifecycleOwner){words->
            val fechaTarea = words.groupBy ({ it.fecha },{it.word})
            val fechaHora = words.groupBy ({it.fecha},{it.hours})

            val miEntity:MutableList<WordEntity> = ArrayList()
            val tareas = StringBuilder()
            var nueva = ""

            for ((x,y) in fechaTarea){
                for (a in y){
                    tareas.append("- $a\n")
                    nueva = tareas.toString()
                }
                miEntity.add(WordEntity(nueva,x,"5"))
                tareas.clear()
            }
            mAdapter.submitList(miEntity)
            //words.let { mAdapter.submitList(words) }
            //Log.d("TAG","$words")
        }

        Log.d("TAG","Configuracion Recycler")
        mRecyclerView = binding.rvContainer
        mAdapter = WordListAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)


        binding.fab.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNewWordFragment()
            findNavController().navigate(action)
        }

       return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(word: WordEntity) {
        Log.d("TAG", "Pulso corto")
        Toast.makeText(activity,word.fecha,Toast.LENGTH_SHORT).show()

    }

    override fun onLongItemClick(word: WordEntity) {
        Log.d("TAG", "Pulso Largo")
        Toast.makeText(activity,word.fecha,Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG","OonREsume")
    }
}