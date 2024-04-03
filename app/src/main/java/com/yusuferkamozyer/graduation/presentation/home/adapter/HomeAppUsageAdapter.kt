package com.yusuferkamozyer.graduation.presentation.home.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.graduation.databinding.LayoutHomeAppUsageRecyclerViewBinding
import com.yusuferkamozyer.graduation.model.AppUsageData

class HomeAppUsageAdapter(val arrayList: ArrayList<AppUsageData>) : RecyclerView.Adapter<HomeAppUsageAdapter.HomeAppUsageViewHolder>() {
    class HomeAppUsageViewHolder(val binding: LayoutHomeAppUsageRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAppUsageViewHolder {
        val binding=LayoutHomeAppUsageRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeAppUsageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: HomeAppUsageViewHolder, position: Int) {
        holder.binding.appIconMain.background=arrayList[position].appIcon
        holder.binding.appNameMain.text=arrayList[position].appName
        holder.binding.appTimeMain.text=arrayList[position].appUsage.toString()
    }
}