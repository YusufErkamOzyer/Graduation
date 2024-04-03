package com.yusuferkamozyer.graduation.presentation.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.graduation.databinding.LayoutAppSelectorBinding
import com.yusuferkamozyer.graduation.model.AppList
import com.yusuferkamozyer.graduation.presentation.selector.model.SelectAppModel

class SelectAppAdapter(val arrayList:ArrayList<SelectAppModel>): RecyclerView.Adapter<SelectAppAdapter.SelectAppViewHolder>() {
    class SelectAppViewHolder(val binding:LayoutAppSelectorBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAppViewHolder {
        val binding=LayoutAppSelectorBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SelectAppViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: SelectAppViewHolder, position: Int) {
        holder.binding.selectorAppIcon.background=arrayList[position].icon
        holder.binding.selectorAppName.text=arrayList[position].appName
        holder.binding.selectorSwitchButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            arrayList[position].isSwitched=isChecked
        }
    }
    fun getSelectedApps(): ArrayList<SelectAppModel>{
        val selectedApps= arrayListOf<SelectAppModel>()
        for (i in arrayList){
            if (i.isSwitched){
                selectedApps.add(i)
            }
        }
        return selectedApps
    }
}