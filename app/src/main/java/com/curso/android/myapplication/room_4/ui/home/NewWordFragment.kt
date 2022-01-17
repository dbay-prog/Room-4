package com.curso.android.myapplication.room_4.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.database.WordEntity
import com.curso.android.myapplication.room_4.databinding.FragmentNewWordBinding
import com.curso.android.myapplication.room_4.utils.DataPickerFragment
import com.curso.android.myapplication.room_4.utils.GetDate
import com.curso.android.myapplication.room_4.utils.InjectorUtils



class NewWordFragment : Fragment() {

    private var _binding: FragmentNewWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel:HomeViewModel



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewWordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val factory = InjectorUtils.inyectarHomeViewModelFactory(this.requireContext())
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)



        val getDate = GetDate()
        binding.etDate.setText(getDate.newDate())

        binding.etDate.setOnClickListener {
            showDatePinckerDialog()
        }


        binding.btnSave.setOnClickListener {
            if (binding.etWord.text.isNullOrEmpty() ||
                binding.etDate.text.isNullOrEmpty() ||
                binding.etHora.text.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), R.string.empty_not_saved,Toast.LENGTH_SHORT).show()

            }else {
                val palabra = binding.etWord.text.toString()
                val date = binding.etDate.text.toString()
                val hours = binding.etHora.text.toString()
                homeViewModel.insert(WordEntity(palabra, date, hours))
            }


            val action = NewWordFragmentDirections.actionNewWordFragmentToNavigationHome()
            findNavController().navigate(action)
        }

        return root
    }

    private fun showDatePinckerDialog() {
        val datePicker = DataPickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(requireActivity().supportFragmentManager,"miDataPicker")

    }

    @SuppressLint("SetTextI18n")
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etDate.setText("$day/${month+1}/$year")
    }


}



