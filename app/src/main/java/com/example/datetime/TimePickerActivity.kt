package com.example.datetime
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datetime.databinding.ActivityTimePickerBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimePickerActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityTimePickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTimePickerBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        mBinding.selectDate.setOnClickListener {
            showTimePickerDialog()
        }
        mBinding.reset.setOnClickListener {
            resetToCurrentTime()
        }
        mBinding.plus.setOnClickListener{
            addHours()
        }
        mBinding.neg.setOnClickListener{
            subHours()
        }
        mBinding.plus1.setOnClickListener{
            addMinutes()
        }
        mBinding.neg1.setOnClickListener{
            subMinutes()
        }
        mBinding.plus2.setOnClickListener{
            addSeconds()
        }
        mBinding.neg2.setOnClickListener{
            subSeconds()
        }
        mBinding.plus3.setOnClickListener{
            addMilliseconds()
        }
        mBinding.neg3.setOnClickListener{
            subMilliseconds()
        }

    }
    private fun resetToCurrentTime() {
        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentTime.get(Calendar.MINUTE)

        var currentFormattedTime = formatTime(currentHour, currentMinute)
        mBinding.one.text = currentFormattedTime
        currentFormattedTime = formatTime2(currentHour,currentMinute)
        mBinding.two.text = currentFormattedTime
    }
    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,{ _, selectedHour, selectedMinute ->
                // Process the selected time
                var selectedTime = formatTime(selectedHour, selectedMinute)
                mBinding.one.text = selectedTime
                selectedTime = formatTime2(selectedHour,selectedMinute)
                mBinding.two.text = selectedTime
            },hour,minute,false
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
    private fun formatTime2(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val sdf = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault())
        return sdf.format(calendar.time)
    }
    private fun addHours() {
        val hoursToAdd = mBinding.numm.text.toString().toIntOrNull() ?: return // Exit if input is not a valid integer

        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:ms a", Locale.getDefault())

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }
        // Add the specified number of hours
        currentTime.add(Calendar.HOUR_OF_DAY, hoursToAdd)
        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:ms", Locale.getDefault()).format(currentTime.time)
    }
    private fun subHours() {
        val hoursToSubtract = mBinding.numm.text.toString().toIntOrNull() ?: return // Exit if input is not a valid integer

        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:ms a", Locale.getDefault())

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Subtract the specified number of hours
        currentTime.add(Calendar.HOUR_OF_DAY, -hoursToSubtract)

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:ms", Locale.getDefault()).format(currentTime.time)
    }
    private fun addMinutes() {
        val minutesToAdd = mBinding.numm.text.toString().toIntOrNull() ?: return
        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault()) // Changed the format to include milliseconds

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Add the specified number of minutes
        currentTime.add(Calendar.MINUTE, minutesToAdd)

        // Correct hours if minutes exceed 60
        while (currentTime.get(Calendar.MINUTE) >= 60) {
            currentTime.add(Calendar.HOUR_OF_DAY, 1)
            currentTime.add(Calendar.MINUTE, -60)
        }

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(currentTime.time)
    }
    private fun subMinutes() {
        val minutesToSubtract = mBinding.numm.text.toString().toIntOrNull() ?: return
        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault()) // Changed the format to include milliseconds

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Subtract the specified number of minutes
        currentTime.add(Calendar.MINUTE, -minutesToSubtract) // Subtracting minutes

        // Correct hours if minutes go below 0
        while (currentTime.get(Calendar.MINUTE) < 0) {
            currentTime.add(Calendar.HOUR_OF_DAY, -1)
            currentTime.add(Calendar.MINUTE, 60)
        }

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(currentTime.time)
    }
    private fun addSeconds() {
        val secondsToAdd = mBinding.numm.text.toString().toIntOrNull() ?: return
        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault()) // Changed the format to include milliseconds

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Add the specified number of seconds
        currentTime.add(Calendar.SECOND, secondsToAdd)

        // Adjust minutes and milliseconds if seconds exceed 60
        while (currentTime.get(Calendar.SECOND) >= 60) {
            currentTime.add(Calendar.MINUTE, 1)
            currentTime.add(Calendar.SECOND, -60)
        }

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(currentTime.time)
    }
    private fun subSeconds() {
        val secondsToSubtract = mBinding.numm.text.toString().toIntOrNull() ?: return
        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault()) // Changed the format to include milliseconds

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Subtract the specified number of seconds
        currentTime.add(Calendar.SECOND, -secondsToSubtract)

        // Adjust minutes and milliseconds if seconds go below 0
        while (currentTime.get(Calendar.SECOND) < 0) {
            currentTime.add(Calendar.MINUTE, -1)
            currentTime.add(Calendar.SECOND, 60)
        }

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(currentTime.time)
    }
    private fun addMilliseconds() {
        val millisecondsToAdd = mBinding.numm.text.toString().toLongOrNull() ?: return
        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault())

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Add the specified number of milliseconds
        currentTime.add(Calendar.MILLISECOND, millisecondsToAdd.toInt())

        // Adjust seconds, minutes, and hours if milliseconds exceed 1000
        while (currentTime.get(Calendar.MILLISECOND) >= 1000) {
            currentTime.add(Calendar.SECOND, 1)
            currentTime.add(Calendar.MILLISECOND, -1000)
        }

        // Adjust minutes if seconds exceed 60
        while (currentTime.get(Calendar.SECOND) >= 60) {
            currentTime.add(Calendar.MINUTE, 1)
            currentTime.add(Calendar.SECOND, -60)
        }

        // Adjust hours if minutes exceed 60
        while (currentTime.get(Calendar.MINUTE) >= 60) {
            currentTime.add(Calendar.HOUR_OF_DAY, 1)
            currentTime.add(Calendar.MINUTE, -60)
        }

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(currentTime.time)
    }
    private fun subMilliseconds() {
        val millisecondsToSubtract = mBinding.numm.text.toString().toIntOrNull() ?: return
        val currentTime = Calendar.getInstance()
        val sdf = SimpleDateFormat("h:mm:ss:SSS a", Locale.getDefault()) // Changed the format to include milliseconds

        // Try to parse the current time displayed in 'one' TextView
        mBinding.one.text.toString().let {
            try {
                currentTime.time = sdf.parse(it) ?: return
            } catch (e: ParseException) {
                return // Exit if parsing fails
            }
        }

        // Subtract the specified number of milliseconds
        currentTime.add(Calendar.MILLISECOND, -millisecondsToSubtract)

        // Adjust seconds, minutes, and hours if milliseconds go below 0
        while (currentTime.get(Calendar.MILLISECOND) < 0) {
            currentTime.add(Calendar.SECOND, -1)
            currentTime.add(Calendar.MILLISECOND, 1000)
        }
        while (currentTime.get(Calendar.SECOND) < 0) {
            currentTime.add(Calendar.MINUTE, -1)
            currentTime.add(Calendar.SECOND, 60)
        }
        while (currentTime.get(Calendar.MINUTE) < 0) {
            currentTime.add(Calendar.HOUR_OF_DAY, -1)
            currentTime.add(Calendar.MINUTE, 60)
        }

        // Format and update the display for both 'one' and 'two' TextViews
        mBinding.one.text = sdf.format(currentTime.time)
        // For 'two', you may convert to 24-hour format if needed
        mBinding.two.text = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(currentTime.time)
    }

}