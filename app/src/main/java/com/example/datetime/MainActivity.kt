package com.example.datetime

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.datetime.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.date.setOnClickListener{
            startActivity(Intent(this,DatePickerActivity::class.java))
        }
        mBinding.time.setOnClickListener{
            startActivity(Intent(this,TimePickerActivity::class.java))
        }
        mBinding.timeStamp.setOnClickListener{
            startActivity(Intent(this,TimeStampActivity::class.java))
        }
        mBinding.timeTDate.setOnClickListener{
            startActivity(Intent(this,Time_to_dateActivity::class.java))
        }
        mBinding.dateTTime.setOnClickListener{
            startActivity(Intent(this,Time_stamp_time_Activity::class.java))
        }
        mBinding.dateHour.setOnClickListener{
            startActivity(Intent(this,Date_Diff_Activity::class.java))

        }
        mBinding.dateMin.setOnClickListener{
            startActivity(Intent(this,Time_Diff_Sec_Activity::class.java))

        }
        mBinding.dateConvt.setOnClickListener{
            startActivity(Intent(this,Date_Time_Conv_Activity::class.java))

        }
    }
}