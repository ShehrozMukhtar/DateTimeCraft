package com.example.datetime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datetime.databinding.ActivityTimeStampTimeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Time_stamp_time_Activity : AppCompatActivity() {
    lateinit var mBinding: ActivityTimeStampTimeBinding
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTimeStampTimeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.selectDate.setOnClickListener{
            showTimePickerDialog()
        }
        mBinding.reset.setOnClickListener {
            // Fetch current time
            val currentTime = Calendar.getInstance()
            val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val currentMinute = currentTime.get(Calendar.MINUTE)

            // Format and set the current time in TextViews
            val formattedTime = formatTime(currentHour, currentMinute)
            mBinding.one.text = formattedTime

            val formattedTime24hr = formatTime2(currentHour, currentMinute)
            mBinding.two.text = formattedTime24hr

            // Set the current timestamp
            val timestamp = currentTime.timeInMillis.toString()
            mBinding.timesStamp.text = timestamp
        }
        mBinding.back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        mBinding.plus.setOnClickListener {
            val userTimestampString = mBinding.numm.text.toString()
            if (userTimestampString.isNotEmpty()) {
                try {
                    val userTimestamp = userTimestampString.toLong()
                    // Set the timestamp only once
                    calendar.timeInMillis = userTimestamp

                    // Fetch current hour and minute from calendar
                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    val minute = calendar.get(Calendar.MINUTE)

                    // Format and set the time in TextViews
                    val formattedTime = formatTime(hour, minute)
                    mBinding.one.text = formattedTime

                    val formattedTime24hr = formatTime2(hour, minute)
                    mBinding.two.text = formattedTime24hr

                    // Set the timestamp TextView to show the entered timestamp
                    mBinding.timesStamp.text = userTimestamp.toString()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid Timestamp", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a timestamp", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.plus1.setOnClickListener {
            val userNumberString = mBinding.numa.text.toString()
            if (userNumberString.isNotEmpty()) {
                try {
                    val userNumber = userNumberString.toLong()
                    // Get the previously set timestamp
                    val currentTimestampString = mBinding.timesStamp.text.toString()
                    if (currentTimestampString.isNotEmpty()) {
                        val currentTimestamp = currentTimestampString.toLong()
                        // Add the user's number to the current timestamp
                        val newTimestamp = currentTimestamp + userNumber
                        // Set the new timestamp
                        calendar.timeInMillis = newTimestamp

                        // Fetch current hour and minute from calendar
                        val hour = calendar.get(Calendar.HOUR_OF_DAY)
                        val minute = calendar.get(Calendar.MINUTE)

                        // Format and set the time in TextViews
                        val formattedTime = formatTime(hour, minute)
                        mBinding.one.text = formattedTime

                        val formattedTime24hr = formatTime2(hour, minute)
                        mBinding.two.text = formattedTime24hr

                        // Set the timestamp TextView to show the new timestamp
                        mBinding.timesStamp.text = newTimestamp.toString()
                    } else {
                        Toast.makeText(this, "Please set a timestamp first", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.neg.setOnClickListener {
            val userNumberString = mBinding.numa.text.toString()
            if (userNumberString.isNotEmpty()) {
                try {
                    val userNumber = userNumberString.toLong()
                    // Get the previously set timestamp
                    val currentTimestampString = mBinding.timesStamp.text.toString()
                    if (currentTimestampString.isNotEmpty()) {
                        val currentTimestamp = currentTimestampString.toLong()
                        // Subtract the user's number from the current timestamp
                        val newTimestamp = currentTimestamp - userNumber
                        // Set the new timestamp
                        calendar.timeInMillis = newTimestamp

                        // Fetch current hour and minute from calendar
                        val hour = calendar.get(Calendar.HOUR_OF_DAY)
                        val minute = calendar.get(Calendar.MINUTE)

                        // Format and set the time in TextViews
                        val formattedTime = formatTime(hour, minute)
                        mBinding.one.text = formattedTime

                        val formattedTime24hr = formatTime2(hour, minute)
                        mBinding.two.text = formattedTime24hr

                        // Set the timestamp TextView to show the new timestamp
                        mBinding.timesStamp.text = newTimestamp.toString()
                    } else {
                        Toast.makeText(this, "Please set a timestamp first", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val selectedTime = formatTime(selectedHour, selectedMinute)
            mBinding.one.text = selectedTime

            val selectedTime24hr = formatTime2(selectedHour, selectedMinute)
            mBinding.two.text = selectedTime24hr

            // Setting the timestamp
            val timestamp = currentTime.apply {
                set(Calendar.HOUR_OF_DAY, selectedHour)
                set(Calendar.MINUTE, selectedMinute)
            }.timeInMillis.toString()
            mBinding.timesStamp.text = timestamp
        }, hour, minute, false)

        timePickerDialog.show()
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    private fun formatTime2(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val sdf = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault())
        return sdf.format(calendar.time)
    }
}