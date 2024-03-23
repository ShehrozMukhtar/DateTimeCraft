package com.example.datetime

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datetime.databinding.ActivityTimeDiffSecBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs

class Time_Diff_Sec_Activity : AppCompatActivity() {
    lateinit var mBinding: ActivityTimeDiffSecBinding
    private var selectedTimestamp: Long = 0
    private var selectedTimestamp2: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTimeDiffSecBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.selectDate.setOnClickListener {
            showTimePickerDialog()
        }
        mBinding.selectDate1.setOnClickListener {
            showTimePickerDialog2()
        }
        mBinding.reset.setOnClickListener {
            resetToCurrentTime()
        }
        mBinding.reset1.setOnClickListener {
            resetToCurrentTime2()
        }
        mBinding.timeStamp.setOnClickListener{
                 calculateDifference()
        }
    }
    private fun resetToCurrentTime() {
        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentTime.get(Calendar.MINUTE)

        var currentFormattedTime = formatTime(currentHour, currentMinute)
        mBinding.one.text = currentFormattedTime
          }
    private fun resetToCurrentTime2() {
        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentTime.get(Calendar.MINUTE)

        var currentFormattedTime = formatTime(currentHour, currentMinute)
        mBinding.one1.text = currentFormattedTime
    }
    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Process the selected time
                val selectedTime = formatTime(selectedHour, selectedMinute)
                mBinding.one.text = selectedTime
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                }
                selectedTimestamp = calendar.timeInMillis
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }
    private fun showTimePickerDialog2() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Process the selected time
                val selectedTime = formatTime(selectedHour, selectedMinute)
                mBinding.one1.text = selectedTime
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                }
                selectedTimestamp2 = calendar.timeInMillis
            },
            hour,
            minute,
            false
        )
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

    private fun calculateDifference() {
        val differenceInSeconds = (selectedTimestamp2 - selectedTimestamp) / 1000 // converting milliseconds to seconds

        val absoluteDifference = abs(differenceInSeconds)
        val differenceDisplay = if (differenceInSeconds >= 0) {
            "$absoluteDifference Seconds"
        } else {
            "$absoluteDifference Seconds"
        }
        mBinding.too.text = differenceDisplay
    }

}