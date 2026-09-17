package com.example.countryapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class DatePicker :
    DialogFragment(){


    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {


        val calendar =
            Calendar.getInstance()


        return DatePickerDialog(
            requireActivity(),
            activity as DatePickerDialog.OnDateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

    }

}
