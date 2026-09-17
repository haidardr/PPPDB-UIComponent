package com.example.countryapp


import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countryapp.databinding.ActivityMainBinding


class MainActivity :
    AppCompatActivity(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {


    private lateinit var binding: ActivityMainBinding


    private val countries = arrayOf(
        "Indonesia",
        "United States",
        "United Kingdom",
        "Germany",
        "France",
        "Australia",
        "Japan",
        "China",
        "Brazil",
        "Canada"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val provinces =
            resources.getStringArray(R.array.provinces)



        with(binding){


            val countryAdapter =
                ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_item,
                    countries
                )


            countryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )


            spinnerCountry.adapter =
                countryAdapter



            val provinceAdapter =
                ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_item,
                    provinces
                )


            provinceAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )


            spinnerProvinces.adapter =
                provinceAdapter



            spinnerCountry.setOnItemSelectedListener(
                object :
                    android.widget.AdapterView.OnItemSelectedListener {


                    override fun onItemSelected(
                        parent: android.widget.AdapterView<*>,
                        view: android.view.View?,
                        position: Int,
                        id: Long
                    ) {


                        Toast.makeText(
                            this@MainActivity,
                            countries[position],
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                    override fun onNothingSelected(
                        parent: android.widget.AdapterView<*>
                    ) {}

                }
            )



            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ){
                    _,year,month,day ->

                val date =
                    "$day/${month+1}/$year"

                Toast.makeText(
                    this@MainActivity,
                    date,
                    Toast.LENGTH_SHORT
                ).show()

            }



            timePicker.setOnTimeChangedListener{
                    _,hour,minute ->

                val time =
                    String.format(
                        "%02d:%02d",
                        hour,
                        minute
                    )


                Toast.makeText(
                    this@MainActivity,
                    time,
                    Toast.LENGTH_SHORT
                ).show()

            }




            btnShowCalendar.setOnClickListener {

                DatePicker()
                    .show(
                        supportFragmentManager,
                        "datePicker"
                    )

            }




            btnShowTimePicker.setOnClickListener {

                TimePicker()
                    .show(
                        supportFragmentManager,
                        "timePicker"
                    )

            }




            btnShowAlertDialog.setOnClickListener {


                val builder =
                    AlertDialog.Builder(this@MainActivity)


                builder.setTitle("Keluar")

                builder.setMessage(
                    "Apakah Anda yakin ingin keluar dari aplikasi?"
                )


                builder.setPositiveButton("Ya"){
                        _,_ ->

                    finish()

                }


                builder.setNegativeButton("Tidak"){
                        dialog,_ ->

                    dialog.dismiss()

                }


                builder.create().show()

            }



            btnShowCustomDialog.setOnClickListener {

                DialogExit()
                    .show(
                        supportFragmentManager,
                        "dialogExit"
                    )

            }


        }

    }



    override fun onDateSet(
        view: android.widget.DatePicker?,
        year:Int,
        month:Int,
        day:Int
    ){

        val date =
            "$day/${month+1}/$year"


        Toast.makeText(
            this,
            date,
            Toast.LENGTH_SHORT
        ).show()

    }



    override fun onTimeSet(
        view: android.widget.TimePicker?,
        hour:Int,
        minute:Int
    ){

        val time =
            String.format(
                "%02d:%02d",
                hour,
                minute
            )


        Toast.makeText(
            this,
            time,
            Toast.LENGTH_SHORT
        ).show()

    }

}
