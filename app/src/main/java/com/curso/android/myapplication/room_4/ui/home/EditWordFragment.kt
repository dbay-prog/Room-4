package com.curso.android.myapplication.room_4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.databinding.FragmentEditWordBinding

class EditWordFragment : Fragment() {

    private var _binding:FragmentEditWordBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit_word, container, false)
        _binding = FragmentEditWordBinding.inflate(inflater,container,false)
        val root:View = binding.root










        return root
    }


}