package com.example.countryapp


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.countryapp.databinding.DialogExitBinding


class DialogExit :
    DialogFragment(){


    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {


        val builder =
            AlertDialog.Builder(requireActivity())


        val binding =
            DialogExitBinding.inflate(
                requireActivity().layoutInflater
            )


        binding.btnYes.setOnClickListener {

            requireActivity().finish()

        }


        binding.btnNo.setOnClickListener {

            dismiss()

        }


        builder.setView(binding.root)


        return builder.create()

    }

}
