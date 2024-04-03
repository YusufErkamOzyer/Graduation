package com.yusuferkamozyer.graduation.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.yusuferkamozyer.graduation.databinding.LayoutHomeAppUsageRecyclerViewBinding
import com.yusuferkamozyer.graduation.model.AppUsageData
import com.yusuferkamozyer.graduation.util.GraphicCharts


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
        val barChart=holder.binding.barChart

        holder.binding.appIconMain.background=arrayList[position].appIcon
        holder.binding.appNameMain.text=arrayList[position].appName
        holder.binding.appTimeMain.text=arrayList[position].appUsage.toString()
        holder.binding.homeLinearLayout.setOnClickListener {
            if (holder.binding.childLinearLayout.isVisible){
                holder.binding.childLinearLayout.visibility=View.GONE
            }
            else{
                holder.binding.childLinearLayout.visibility=View.VISIBLE
            }

        }
        holder.binding.lineChartButton.setOnClickListener {
            holder.binding.barChart.visibility=View.GONE
            holder.binding.lineChart.visibility=View.VISIBLE
            GraphicCharts().setLineChart(holder.binding.lineChart,GraphicCharts().generateSampleData(),"başlık")
        }
        holder.binding.barChartButton.setOnClickListener {
            holder.binding.barChart.visibility=View.VISIBLE
            holder.binding.lineChart.visibility=View.GONE
            GraphicCharts().setBarChart(holder.binding.barChart)

        }
    }

}