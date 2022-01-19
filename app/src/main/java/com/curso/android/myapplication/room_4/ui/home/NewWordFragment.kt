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
import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.database.WordEntity
import com.curso.android.myapplication.room_4.databinding.FragmentNewWordBinding
import com.curso.android.myapplication.room_4.utils.InjectorUtils
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import org.koin.ext.getScopeId
import java.text.SimpleDateFormat
import java.util.*


class NewWordFragment : Fragment() {

    private var _binding: FragmentNewWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel:HomeViewModel
    private var date:Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewWordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val factory = InjectorUtils.inyectarHomeViewModelFactory(this.requireContext())
        homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)


        val today = MaterialDatePicker.todayInUtcMilliseconds()
        date = today + 86400000
        binding.etFecha.setText(SimpleDateFormat("dd/MM/yyy",Locale.ROOT).format(date))

        binding.etFecha.setOnClickListener {
            val dataPicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("SELECCIONAR LA FECHA")
                .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())

            val picker = dataPicker.build()
            picker.addOnPositiveButtonClickListener { selectedDate->
                //Snackbar.make(binding.containerMain,picker.headerText,Snackbar.LENGTH_LONG).show()
                binding.etFecha.setText(SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(selectedDate+86400000))
                date = selectedDate + 86400000
                Log.d("TAG","Fecha: $date")

            }
            picker.show(childFragmentManager,picker.toString())

        }


        binding.btnAceptar.setOnClickListener {
            if (binding.etWord.text.isNullOrEmpty() ||
                binding.etFecha.text.isNullOrEmpty() ||
                binding.etHoras.text.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), R.string.empty_not_saved,Toast.LENGTH_SHORT).show()

            }else {
                val palabra = binding.etWord.text.toString()
                val hours = binding.etHoras.text.toString()
                homeViewModel.insert(WordEntity(palabra, date, hours))

            }


            val action = NewWordFragmentDirections.actionNewWordFragmentToNavigationHome()
            findNavController().navigate(action)
        }
        binding.btnCancelar.setOnClickListener {
            val action = NewWordFragmentDirections.actionNewWordFragmentToNavigationHome()

            findNavController().navigate(action)
            Toast.makeText(requireActivity(), R.string.empty_not_saved,Toast.LENGTH_SHORT).show()
        }

        return root
    }





}



