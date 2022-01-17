package com.curso.android.myapplication.room_4.ui.home


import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
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

import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.adapter.WordListAdapter
import com.curso.android.myapplication.room_4.database.WordEntity
import com.curso.android.myapplication.room_4.databinding.FragmentHomeBinding
import com.curso.android.myapplication.room_4.utils.InjectorUtils

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var homeViewModel:HomeViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: WordListAdapter


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
        val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(60)
        }

        val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity())
            .setTitle(R.string.main_dialogDelete_title)
            .setMessage(String.format(Locale.ROOT, getString(R.string.main_dialogDelete_message),
                word.fecha))
            .setPositiveButton(R.string.label_dialog_delete) { dialogInterface: DialogInterface?, i: Int ->
                try {

                    Log.d("TAG","Elimar: ${word.fecha}")
                    homeViewModel.deleteWord(word.fecha)
                    //showMessage(R.string.main_message_delete_success)
                    Toast.makeText(activity,R.string.main_message_delete_success,Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(activity,R.string.main_message_delete_fail,Toast.LENGTH_SHORT).show()
                    //showMessage(R.string.main_message_delete_fail)
                }
            }
            .setNegativeButton(R.string.label_dialog_cancel, null)
        builder.show()

    }

//    private fun showMessage(resource: Int) {
//        Snackbar.make(containerMain!!, resource, Snackbar.LENGTH_SHORT).show()
//    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG","OonREsume")
    }
}