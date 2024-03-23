package com.example.datetime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.datetime.databinding.ActivityTimeStampBinding
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TimeStampActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityTimeStampBinding
    private var firstTimestamp: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTimeStampBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.back.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        mBinding.timesStamp.text = System.currentTimeMillis().toString()
        mBinding.selectDate.setOnClickListener{
          showDateTimePicker()
        }
        mBinding.reset.setOnClickListener {
            setToCurrentDateTime()
        }
        mBinding.plus.setOnClickListener{

            val daysToAdd = mBinding.numm.text.toString().toIntOrNull()
            if (daysToAdd != null) {
                addDays(daysToAdd)
            } else {
                Toast.makeText(this, "Please enter a valid number of days.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.neg.setOnClickListener{
            val daysToSubtract = mBinding.numm.text.toString().toIntOrNull()  // Assuming 'numDays' is your EditText ID
            if (daysToSubtract != null) {
                subtractDays(daysToSubtract)
            } else {
                Toast.makeText(this, "Please enter a valid number of days.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.monthAdd.setOnClickListener{
            val monthsToAdd = mBinding.numm.text.toString().toIntOrNull()
            if (monthsToAdd != null) {
                addMonths(monthsToAdd)
            } else {
                Toast.makeText(this, "Please enter a valid number of Months.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.subMonth.setOnClickListener{
            val subMonth = mBinding.numm.text.toString().toIntOrNull()
            if (subMonth != null) {
                subtractMonths(subMonth)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.hourAdd.setOnClickListener{
            val addHour = mBinding.numm.text.toString().toIntOrNull()
            if (addHour != null) {
                addHours(addHour)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.hourSub.setOnClickListener{
            val subHour = mBinding.numm.text.toString().toIntOrNull()
            if (subHour != null) {
                subHours(subHour)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.minAdd.setOnClickListener{
            val addMinute = mBinding.numm.text.toString().toIntOrNull()
            if (addMinute != null) {
                addMinute(addMinute)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.minSub.setOnClickListener{
            val subMinute = mBinding.numm.text.toString().toIntOrNull()
            if (subMinute != null) {
                subMinute(subMinute)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.secAdd.setOnClickListener{
            val addSec = mBinding.numm.text.toString().toIntOrNull()
            if (addSec != null) {
                addSec(addSec)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.secNeg.setOnClickListener{
            val subSec = mBinding.numm.text.toString().toIntOrNull()
            if (subSec != null) {
                subSec(subSec)
            } else {
                Toast.makeText(this, "Please enter a valid number of month.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.plus2.setOnClickListener{
            val addYear= mBinding.numm.text.toString().toIntOrNull()
            if (addYear != null) {
                addYears(addYear)
            } else {
                Toast.makeText(this, "Please enter a valid number of year.", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.neg2.setOnClickListener{
            val subYear = mBinding.numm.text.toString().toIntOrNull()
            if (subYear != null) {
                subtractYears(subYear)
            } else {
                Toast.makeText(this, "Please enter a valid number of year.", Toast.LENGTH_SHORT).show()
            }
        }

}
    private fun addDays(days: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.DAY_OF_MONTH, days)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val datePickerDialog = android.app.DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, selectedHour, selectedMinute ->
                        selectedDate.set(Calendar.HOUR_OF_DAY, selectedHour)
                        selectedDate.set(Calendar.MINUTE, selectedMinute)

                        var dateTimeFormat = SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
                        var formattedDateTime = dateTimeFormat.format(selectedDate.time)
                        mBinding.one.text = formattedDateTime
                        dateTimeFormat = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss:SSS", Locale.getDefault())
                        formattedDateTime = dateTimeFormat.format(selectedDate.time)
                        mBinding.two.text = formattedDateTime

                        // Set timestamp
                        val timestamp = selectedDate.timeInMillis
                        val timestampText = timestamp
                        mBinding.timesStamp.text = timestampText.toString()
                        firstTimestamp = selectedDate.timeInMillis

                    },
                    hour, minute, false
                )
                timePickerDialog.show()
            },
            year, month, day
        )
        datePickerDialog.show()
    }
    private fun setToCurrentDateTime() {
        val calendar = Calendar.getInstance()
        var dateTimeFormat = SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
        var formattedDateTime = dateTimeFormat.format(calendar.time)
        mBinding.one.text = formattedDateTime
        dateTimeFormat = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss:SSS", Locale.getDefault())
        formattedDateTime = dateTimeFormat.format(calendar.time)
        mBinding.two.text = formattedDateTime
        firstTimestamp = calendar.timeInMillis
    }
    private fun updateDateTimeDisplay(calendar: Calendar) {
        val dateTimeFormat = SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
        mBinding.one.text = dateTimeFormat.format(calendar.time)

        val dateTimeFormat2 = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss:SSS", Locale.getDefault())
        mBinding.two.text = dateTimeFormat2.format(calendar.time)

        mBinding.timesStamp.text = calendar.timeInMillis.toString()
    }
    private fun subtractDays(days: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            // Subtract days
            calendar.add(Calendar.DAY_OF_MONTH, -days)  // Note the '-' sign to subtract
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addMonths(months: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.MONTH, months)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun subtractMonths(days: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            // Subtract days
            calendar.add(Calendar.MONTH,-days)  // Note the '-' sign to subtract
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addHours(hours: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.HOUR_OF_DAY,hours)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun subHours(hours: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.HOUR_OF_DAY,-hours)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addMinute(minute: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.MINUTE,minute)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun subMinute(minute: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.MINUTE,-minute)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addSec(sec: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.SECOND,sec)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun subSec(sec: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.SECOND,-sec)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addYears(year: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            calendar.add(Calendar.YEAR, year)
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun subtractYears(year: Int) {
        val calendar = Calendar.getInstance()
        if (mBinding.timesStamp.text.isNotEmpty()) {
            calendar.timeInMillis = mBinding.timesStamp.text.toString().toLong()
            // Subtract days
            calendar.add(Calendar.YEAR,-year)  // Note the '-' sign to subtract
            updateDateTimeDisplay(calendar)
        } else {
            Toast.makeText(this, "No timestamp selected.", Toast.LENGTH_SHORT).show()
        }
    }
}
