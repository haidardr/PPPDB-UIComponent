package com.example.countryapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.countryapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0
    private var selectedHour = 0
    private var selectedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val attendanceStatuses = resources.getStringArray(R.array.attendance_status)

        with(binding) {
            selectedYear = datePicker.year
            selectedMonth = datePicker.month
            selectedDay = datePicker.dayOfMonth

            timePicker.setIs24HourView(false)
            selectedHour = timePicker.hour
            selectedMinute = timePicker.minute

            val statusAdapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                attendanceStatuses,
            )
            statusAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            spinnerStatus.adapter = statusAdapter

            spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        etKeterangan.visibility = View.GONE
                        etKeterangan.text?.clear()
                    } else {
                        etKeterangan.visibility = View.VISIBLE
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

            datePicker.init(
                selectedYear,
                selectedMonth,
                selectedDay
            ) { _, year, month, day ->
                selectedYear = year
                selectedMonth = month
                selectedDay = day
            }

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                selectedHour = hourOfDay
                selectedMinute = minute
            }

            btnSubmit.setOnClickListener {
                val calendar = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDay)
                }
                val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH)
                val formattedDate = dateFormat.format(calendar.time)

                val amPm = if (selectedHour < 12) "AM" else "PM"
                val hour12 = when {
                    selectedHour == 0 -> 12
                    selectedHour > 12 -> selectedHour - 12
                    else -> selectedHour
                }
                val formattedTime = String.format(
                    Locale.US,
                    "%02d:%02d %s",
                    hour12,
                    selectedMinute,
                    amPm
                )

                val message = "Presensi berhasil $formattedDate jam $formattedTime"
                tvResult.text = message
                tvResult.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }

            btnExit.setOnClickListener {
                showExitDialog()
            }
        }

        onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    showExitDialog()
                }
            }
        )
    }

    private fun showExitDialog() {
        DialogExit().show(supportFragmentManager, "dialogExit")
    }
}

