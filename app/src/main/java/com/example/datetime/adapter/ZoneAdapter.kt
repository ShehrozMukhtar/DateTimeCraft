package com.example.datetime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datetime.adapter.models.Zone
import com.example.datetime.adapter.models.getZonesList
import com.example.datetime.databinding.ItemDialogueBinding

class ZoneAdapter(
   val zone :List<Zone> = getZonesList(),
    val onZoneClick:(Zone,Int) -> Unit
):RecyclerView.Adapter<ZonePickerHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZonePickerHolder {
       return ZonePickerHolder(
           ItemDialogueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
     }

    override fun getItemCount(): Int {
         return zone.size
    }

    override fun onBindViewHolder(holder: ZonePickerHolder, position: Int) {
      holder.binding.count.setText(zone[position].value)
      holder.binding.shortText.setText(zone[position].shortFrom)
      holder.binding.root.setOnClickListener{
              onZoneClick(zone[position],position)
      }

    }
}