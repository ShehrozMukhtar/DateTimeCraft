package com.example.datetime.dialogues

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datetime.adapter.ZoneAdapter
import com.example.datetime.adapter.models.Zone
import com.example.datetime.databinding.DialogZonePickerBinding

class ZonePicker (
   val context:Context,
   val layoutInflater: LayoutInflater,
   val  onDialogCloseClick:(ZonePicker)->Unit,
   val onZoneClick: (ZonePicker,Zone,Int) -> Unit,
    val resource:Resources

){
    var layoutParm:WindowManager.LayoutParams
    var dialogue:Dialog
    var binding:DialogZonePickerBinding
    private lateinit var zoneAdapter:ZoneAdapter
    init {
        dialogue = Dialog(context)
        binding = DialogZonePickerBinding.inflate(layoutInflater)
        dialogue.setContentView(binding.root)
        dialogue.window?.addFlags(Window.FEATURE_NO_TITLE)
        dialogue.window?.setBackgroundDrawableResource(com.google.android.material.R.color.material_blue_grey_800)
        dialogue.setCancelable(true)
        zoneAdapter = ZoneAdapter(onZoneClick = {zone,position ->
            onZoneClick(this,zone,position)
        })
        layoutParm = WindowManager.LayoutParams()
        dialogue.window?.let { window ->
                layoutParm.copyFrom(window.attributes)
        }
      //  layoutParm.width = WindowManager.LayoutParams.MATCH_PARENT
     //    layoutParm.height = WindowManager.LayoutParams.MATCH_PARENT
        layoutParm.width = resource.displayMetrics.widthPixels - 80
        layoutParm.height = resource.displayMetrics.heightPixels - 160



        binding.zonesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.zonesRecyclerView.adapter = zoneAdapter
        binding.cross.setOnClickListener {
             onDialogCloseClick(this@ZonePicker)
        }
    }

    fun show(){
        dialogue.show()
        dialogue.window?.attributes = layoutParm

    }
    fun hide(){
        dialogue.hide()
    }
}