package com.example.myapplication

import android.app.DatePickerDialog
import android.app.admin.SystemUpdatePolicy
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener{ view->
            openDatePicker(view)
        }
    }
    private fun openDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/$selectedMonth/$selectedYear"
            binding.tvSelectedDate.text = selectedDate


           val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = sdf.parse(selectedDate)
            val epochToBirthdayInMinutes = date!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            binding.tvAgeInMinutes.text = (currentDateInMinutes-epochToBirthdayInMinutes).toString()
        }, year, month, day)
            .show()
    }

}