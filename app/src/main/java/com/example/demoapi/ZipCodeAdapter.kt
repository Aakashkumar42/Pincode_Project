package com.example.demoapi

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapi.DataModel.ResponseData
import com.example.demoapi.DataModel.Zipcode
import com.example.demoapi.databinding.ZipcodeLayoutBinding

class ZipCodeAdapter:RecyclerView.Adapter<ZipCodeAdapter.ZipCodeViewHolder>() {

    private var lists= mutableListOf<Zipcode>()

    fun setAdapter(lists:List<Zipcode>){
        this.lists=lists.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZipCodeViewHolder {
       val inflate=LayoutInflater.from(parent.context)
       val binding=ZipcodeLayoutBinding.inflate(inflate,parent,false)
       return ZipCodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZipCodeViewHolder, position: Int) {
        val items=lists[position]
        holder.bind(items)
        holder.binding.zipcodeID.setOnClickListener {
            if (!holder.binding.zipcodeID.text.isEmpty()){
                val uri= Uri.parse("geo:0,0?q=${items.id}")
                val intent= Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                holder.binding.root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    inner class ZipCodeViewHolder(val binding: ZipcodeLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(items:Zipcode){
            binding.zipcodeID.text=items.id.toString()
            binding.zipcodeNo.text=items.zipcode.toString()
        }
    }
}