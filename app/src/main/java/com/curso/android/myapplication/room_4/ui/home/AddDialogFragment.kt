package com.curso.android.myapplication.room_4.ui.home

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.curso.android.myapplication.room_4.databinding.FragmentDialogAddBinding

class AddDialogFragment:DialogFragment(), DialogInterface.OnShowListener {

    companion object{
        const val TAG = "AddDialogFragment"
    }

    private var binding:FragmentDialogAddBinding?=null

    private var positiveButton:Button?=null
    private var negativeButton:Button?=null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let { activity->
            binding = FragmentDialogAddBinding.inflate(LayoutInflater.from(context))

            binding?.let {
                val builder = AlertDialog.Builder(activity)
                    .setTitle("Agregar Actividad")
                    .setPositiveButton("Agregar", null)
                    .setNegativeButton("Cancelar", null)
                    .setView(it.root)
                val dialog = builder.create()
                dialog.setOnShowListener(this)

                return dialog
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }


    override fun onShow(dialogInterface: DialogInterface?) {
        val dialog = dialog as? AlertDialog
        //Preguntamos dialog no es null, entonces
        dialog?.let {
            positiveButton = it.getButton(Dialog.BUTTON_POSITIVE)
            negativeButton = it.getButton(Dialog.BUTTON_NEGATIVE)

            positiveButton?.setOnClickListener {

            }
            negativeButton?.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}