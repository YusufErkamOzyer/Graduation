package com.yusuferkamozyer.graduation.presentation.program.adapter

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.graduation.databinding.LayoutCreateProgramSelectedListRecyclerViewBinding
import com.yusuferkamozyer.graduation.databinding.LayoutProgramSelectedListRecyclerViewBinding
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem
import java.util.Calendar

class CreateProgramAdapter(val array: Array<AppItem>) : RecyclerView.Adapter<CreateProgramAdapter.CreateProgramViewHolder>() {
    class CreateProgramViewHolder(val binding: LayoutCreateProgramSelectedListRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateProgramViewHolder {
        val binding= LayoutCreateProgramSelectedListRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CreateProgramViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: CreateProgramViewHolder, position: Int) {
        holder.binding.createAppImageView.background=array[position].appIcon
        holder.binding.createAppTextView.text=array[position].appName
        val context = holder.binding.root.context
        holder.binding.createAppEditText.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog'u oluştur ve göster
            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                    // Seçilen tarihi createAppEditText'e yaz
                    holder.binding.createAppEditText.setText("$dayOfMonth/${month + 1}/$year")
                },
                year,
                month,
                day
            )
            datePickerDialog.show()

        }
        /*holder.binding.btnIncrease.setOnClickListener {
            val number=holder.binding.editTextNumber.text.toString().toInt()
            holder.binding.editTextNumber.setText((number+1).toString())
        }
        holder.binding.btnDecrease.setOnClickListener {
            val number=holder.binding.editTextNumber.text.toString().toInt()
            if ((number-1)>=1){
                holder.binding.editTextNumber.setText((number-1).toString())
            }

        }*/
    }

}