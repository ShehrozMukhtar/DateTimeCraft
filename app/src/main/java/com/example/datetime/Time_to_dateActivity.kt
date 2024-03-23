package com.example.datetime

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.example.datetime.databinding.ActivityTimeToDateBinding
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.Month
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Time_to_dateActivity : AppCompatActivity() {
   lateinit var mBinding : ActivityTimeToDateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTimeToDateBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.back.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        mBinding.timesStamp.text = System.currentTimeMillis().toString()
        mBinding.plus.setOnClickListener {
            val timestampString = mBinding.numm.text.toString()
            mBinding.timesStamp.text = timestampString
            val timestamp = timestampString.toLong() // Convert string to Long
            val date = Date(timestamp)
            var dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
            var formattedDate = dateFormat.format(date)
            mBinding.one.text = formattedDate
            dateFormat = SimpleDateFormat("yyyy, MMMM dd", Locale.getDefault())
            formattedDate = dateFormat.format(date)
            mBinding.two.text = formattedDate
        }
        mBinding.reset.setOnClickListener {
            // Use Calendar instance to get current date
            mBinding.timesStamp.text = System.currentTimeMillis().toString()
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) // Note: January is 0, December is 11
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Update the UI to show current date
            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day ${monthNames[month].substring(0,3)}, $year"
            mBinding.one.setText(selectedDate)
            val selectedDateWithMonthName = "$year,${monthNames[month]} $day"
            mBinding.two.setText(selectedDateWithMonthName)
        }
        mBinding.selectDate.setOnClickListener{
            showDatePickerDialog()
        }
    }
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(Calendar.YEAR, year)
                selectedCalendar.set(Calendar.MONTH, monthOfYear)
                selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                var dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
                var selectedDate = dateFormat.format(selectedCalendar.time)

                // Display selected date and timestamp in TextViews
                mBinding.one.text = selectedDate
                dateFormat = SimpleDateFormat("yyyy, MMMM dd", Locale.getDefault())
                selectedDate = dateFormat.format(selectedCalendar.time)
                mBinding.two.text = selectedDate
                var timestamp = selectedCalendar.timeInMillis.toString()
                mBinding.timesStamp.text = timestamp
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }
}
