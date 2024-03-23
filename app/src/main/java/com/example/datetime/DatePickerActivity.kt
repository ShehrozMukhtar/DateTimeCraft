package com.example.datetime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datetime.databinding.ActivityDatePickerBinding
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.text.DateFormatSymbols
import java.util.Calendar


class DatePickerActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityDatePickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDatePickerBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        var calendar = Calendar.getInstance()
        var year = calendar[Calendar.YEAR]
        var month = calendar[Calendar.MONTH]
        var day = calendar[Calendar.DAY_OF_MONTH]
        mBinding.selectDate.setOnClickListener {
            val datePickerDialog = android.app.DatePickerDialog(
                this,
                { datePicker, selectedYear, selectedMonth, selectedDay ->
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay
                    val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
                    mBinding.one.setText(selectedDate)
                    val monthNames = DateFormatSymbols().months
                    val selectedDates = "$day-${monthNames[month]}-$year"
                    mBinding.two.setText(selectedDates)
                    val selectedDatess = "${monthNames[month]} $day,$year"
                    mBinding.three.setText(selectedDatess)
                }, year, month, day
            )
            datePickerDialog.show()
        }
        mBinding.reset.setOnClickListener {
            // Use Calendar instance to get current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) // Note: January is 0, December is 11
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Update the UI to show current date
            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }

        mBinding.plus.setOnClickListener {
            var num = mBinding.numm.text.toString().toInt()

            while (num > 0) {
                val daysInCurrentMonth = when (month + 1) {
                    1, 3, 5, 7, 8, 10, 12 -> 31
                    4, 6, 9, 11 -> 30
                    2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                    else -> 30
                }

                if (num <= daysInCurrentMonth - day) {
                    day += num
                    num = 0
                } else {
                    num -= (daysInCurrentMonth - day + 1) // Also move to the next day of the new month
                    day = 1 // Reset day for the new month
                    month += 1
                    if (month > 11) { // Adjust for December to January transition
                        month = 0
                        year++
                    }
                }
            }

            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }
        mBinding.neg.setOnClickListener {
            var num = mBinding.numm.text.toString().toInt()

            while (num > 0) {
                if (num < day) {
                    day -= num
                    num = 0
                } else {
                    num -= day
                    month -= 1
                    if (month < 0) { // Adjust for January to December transition
                        month = 11
                        year -= 1
                    }

                    // Set the day to the last day of the new month
                    day = when (month + 1) {
                        1, 3, 5, 7, 8, 10, 12 -> 31
                        4, 6, 9, 11 -> 30
                        2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                        else -> 30
                    }

                    if (num >= day) {
                        num -= day // Prepare for the next loop iteration, if any
                    }
                }
            }

            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }
        mBinding.plus1.setOnClickListener {
            var numMonths = mBinding.numm.text.toString().toInt()
            month += numMonths

            // Check how many years we need to advance, and adjust the month accordingly
            while (month > 11) {
                month -= 12
                year += 1
            }

            // Adjust day for the maximum days in the new month
            val maxDayOfMonth = when (month + 1) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                else -> 31 // Default case, though not expected to be reached
            }

            if (day > maxDayOfMonth) {
                day = maxDayOfMonth // Adjust the day to the last day of the month if necessary
            }

            // Update the UI with the new date
            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }
        mBinding.neg1.setOnClickListener {
            var numMonths = mBinding.numm.text.toString().toInt()
            month -= numMonths

            // Adjust for negative months, ensuring year is decreased as needed
            while (month < 0) {
                month += 12
                year -= 1
            }

            // Adjust day for the maximum days in the adjusted month
            val maxDayOfMonth = when (month + 1) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                else -> 31 // Default case, should not be reached
            }

            if (day > maxDayOfMonth) {
                day = maxDayOfMonth // Adjust the day to the last day of the month if necessary
            }

            // Update the UI with the new date
            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }
        mBinding.plus2.setOnClickListener {
            var numYears = mBinding.numm.text.toString().toInt()
            year += numYears

            // Since the year has changed, update the date accordingly
            // Check for leap year if the current month is February
            if (month == 1) { // February
                val isLeapYear = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
                if (!isLeapYear && day > 28) {
                    day = 28 // Adjust day for non-leap year
                } else if (isLeapYear && day > 29) {
                    day = 29 // Adjust day for leap year
                }
            }

            // Update the UI with the new date
            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }
        mBinding.neg2.setOnClickListener {
            var numYears = mBinding.numm.text.toString().toInt()
            year -= numYears

            // Since the year has changed, we might need to adjust the day for February in case of a leap year
            if (month == 1) { // February
                val isLeapYear = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
                if (!isLeapYear && day > 28) {
                    day = 28 // Adjust for non-leap year
                } else if (isLeapYear && day > 29) {
                    day = 29 // Ensure day does not exceed 29 in a leap year
                }
            }

            // Update the UI with the new date
            val monthNames = DateFormatSymbols().months
            val selectedDate = "$day-${month + 1}-$year"
            val selectedDateWithMonthName = "$day-${monthNames[month]}-$year"
            val selectedDateFormatted = "${monthNames[month]} $day,$year"

            mBinding.one.setText(selectedDate)
            mBinding.two.setText(selectedDateWithMonthName)
            mBinding.three.setText(selectedDateFormatted)
        }
        mBinding.back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}