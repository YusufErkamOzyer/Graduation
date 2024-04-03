package com.yusuferkamozyer.graduation.presentation.program.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.databinding.LayoutProgramSelectedListRecyclerViewBinding
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem

class ProgramSelectedListAdapter(var selectedList: ArrayList<AppItem>,var arrayList: ArrayList<AppItem>):
    RecyclerView.Adapter<ProgramSelectedListAdapter.ProgramSelectedListViewHolder>() {
    class ProgramSelectedListViewHolder(val binding: LayoutProgramSelectedListRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProgramSelectedListViewHolder {
        val binding=LayoutProgramSelectedListRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProgramSelectedListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedList.size
    }
    override fun onBindViewHolder(holder: ProgramSelectedListViewHolder, position: Int) {
        holder.binding.appIconProgram.background=selectedList[position].appIcon
        holder.binding.appNameProgram.text=selectedList[position].appName
        holder.binding.appDeleteButton.setOnClickListener {
            selectedList.remove(selectedList[position])
            arrayList.add(selectedList[position])
            updateData(selectedList)
        }

    }
    fun updateData(newItems: ArrayList<AppItem>) {
        selectedList = newItems
        notifyDataSetChanged()
    }

}