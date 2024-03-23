package com.example.datetime

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.pm.ActivityInfo.WindowLayout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.example.datetime.databinding.ActivityDateTimeConvBinding
import com.example.datetime.dialogues.ZonePicker
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Date_Time_Conv_Activity : AppCompatActivity() {
    lateinit var mBinding: ActivityDateTimeConvBinding
    lateinit var zonePicker: ZonePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDateTimeConvBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        var calendar = Calendar.getInstance()
        var year = calendar[Calendar.YEAR]
        var month = calendar[Calendar.MONTH]
        var day = calendar[Calendar.DAY_OF_MONTH]
        val monthNames = DateFormatSymbols().months
        mBinding.selectDate.setOnClickListener {
            val datePickerDialog = android.app.DatePickerDialog(
                this,
                { datePicker, selectedYear, selectedMonth, selectedDay ->
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay
                    val selectedDate = day.toString()+" "+ monthNames[month].substring(0,3) + "," + year
                    mBinding.one.setText(selectedDate)
                }, year, month, day
            )
            datePickerDialog.show()
        }
        mBinding.reset.setOnClickListener{
            showTimePickerDialog()
        }
        mBinding.dateTitle6.setOnClickListener {
            /*   val d = Dialog(this)
            d.setContentView(R.layout.dialog_zone_picker)
            val cros = d.findViewById<ImageView>(R.id.cross)
            cros.setOnClickListener {
                d.dismiss()
            }
            d.show()
        } */
            if(!this::zonePicker.isInitialized){
                zonePicker = ZonePicker(
                    context =  this@Date_Time_Conv_Activity,
                    layoutInflater =layoutInflater,
                    onDialogCloseClick = {
                        Toast.makeText(this,"Closed",Toast.LENGTH_SHORT).show()
                        zonePicker.hide()
                    },
                    onZoneClick = {dialog,zone,zonePosition ->
                        mBinding.numa.text = zone.value
                        mBinding.dateTextText.text = zone.shortFrom +": "
                        Toast.makeText(this,"Zone: ${zone.value},${zone.shortFrom}",Toast.LENGTH_SHORT).show()
                        dialog.hide()
                    },
                    resource = resources
                )

            }
            zonePicker.show()
        }
    }

    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,{ _, selectedHour, selectedMinute ->
                // Process the selected time
                var selectedTime = formatTime(selectedHour, selectedMinute)
                mBinding.oneOne.text = selectedTime
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
    fun convertTimeToDiffZone(){
        val DATE_FORMAT = "dd MMM,yyyy-hh:mm:ss:SSS a"
        val mCurrentTime = ZonedDateTime.ofInstant(mCalendar.toInstant(), ZoneId.of("Asia/Karachi"))
        val req = mCurrentTime.withZoneSameInstant(ZoneId.of(zoneId))
        // val clztnow = cpkztnow.withZoneSameInstant(ZoneId.of("Europe/London"))
        //val ctztnow = cpkztnow.withZoneSameInstant(ZoneId.of("Asia/Tokyo"))

        mBinding.countryTime.text =  DateTimeFormatter.ofPattern(DATE_FORMAT).format(req)
        mBinding.country.text = zoneSF+":"
        zoneId = ""
        zoneSF = ""

        //  println("New York: " + DateTimeFormatter.ofPattern(DATE_FORMAT).format(cnyztnow))
        // println("London: " + DateTimeFormatter.ofPattern(DATE_FORMAT).format(clztnow))
        //println("Tokyo: " + DateTimeFormatter.ofPattern(DATE_FORMAT).format(ctztnow))
    }


}