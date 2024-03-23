package com.example.datetime

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datetime.databinding.ActivityDateDiffBinding
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs

class Date_Diff_Activity : AppCompatActivity() {
    lateinit var mBinding: ActivityDateDiffBinding
    private var firstTimestamp: Long = 0
    private var secondTimestamp: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDateDiffBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.selectDate.setOnClickListener {
            showDateTimePicker()
        }
        mBinding.selectDate1.setOnClickListener{
            showPickerTwo()
        }
        mBinding.diff.setOnClickListener{
              calculateDifference()
        }
        mBinding.reset.setOnClickListener {
            setToCurrentDateTime()
        }
        mBinding.reset1.setOnClickListener {
            setToCurrentDateTime2()
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

                        val dateTimeFormat =
                            SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
                        val formattedDateTime = dateTimeFormat.format(selectedDate.time)
                        mBinding.two.text = formattedDateTime

                        // Set timestamp
                        val timestamp = selectedDate.timeInMillis
                        val timestampText = timestamp
                        mBinding.one.text = timestampText.toString()
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
    private fun showPickerTwo() {
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

                        val dateTimeFormat =
                            SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
                        val formattedDateTime = dateTimeFormat.format(selectedDate.time)
                        mBinding.twoTwo.text = formattedDateTime

                        // Set timestamp
                        val timestamp = selectedDate.timeInMillis
                        val timestampText = timestamp
                        mBinding.one1.text = timestampText.toString()
                        secondTimestamp = selectedDate.timeInMillis

                    },
                    hour, minute, false
                )
                timePickerDialog.show()
            },
            year, month, day
        )
        datePickerDialog.show()
    }
    private fun calculateDifference() {
        val differenceInMillis = abs(firstTimestamp - secondTimestamp)
        val differenceInHours = differenceInMillis / (1000 * 60 * 60) // converting milliseconds to hours
        mBinding.too.text = "$differenceInHours  Hours"
    }

    private fun setToCurrentDateTime() {
        val calendar = Calendar.getInstance()
        val dateTimeFormat = SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
        val formattedDateTime = dateTimeFormat.format(calendar.time)
            mBinding.two.text = formattedDateTime
            mBinding.one.text = calendar.timeInMillis.toString()
            firstTimestamp = calendar.timeInMillis

    }
    private fun setToCurrentDateTime2() {
        val calendar = Calendar.getInstance()
        val dateTimeFormat = SimpleDateFormat("dd MMM,yyyy hh:mm:ss:SSS a", Locale.getDefault())
        val formattedDateTime = dateTimeFormat.format(calendar.time)

            mBinding.twoTwo.text = formattedDateTime
            mBinding.one1.text = calendar.timeInMillis.toString()
            secondTimestamp = calendar.timeInMillis
    }
}
