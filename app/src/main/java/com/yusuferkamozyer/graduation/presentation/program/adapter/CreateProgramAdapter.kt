package com.yusuferkamozyer.graduation.presentation.program.adapter

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.graduation.databinding.LayoutCreateProgramSelectedListRecyclerViewBinding
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem
import java.util.Calendar

class CreateProgramAdapter(val array: Array<AppItem>) :
    RecyclerView.Adapter<CreateProgramAdapter.CreateProgramViewHolder>() {
    class CreateProgramViewHolder(val binding: LayoutCreateProgramSelectedListRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateProgramViewHolder {
        val binding = LayoutCreateProgramSelectedListRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CreateProgramViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: CreateProgramViewHolder, position: Int) {
        holder.binding.createAppImageView.background = array[position].appIcon
        holder.binding.createAppTextView.text = array[position].appName
        val context = holder.binding.root.context
        holder.binding.createAppEditText.setOnClickListener {
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
        holder.binding.btnDayIncrease.setOnClickListener {
            val number = holder.binding.dayTextView.text.toString().toInt() + 1
            checkIfIsNUm(holder.binding.dayTextView, number)

        }
        holder.binding.btnDayDecrease.setOnClickListener {
            val number = holder.binding.dayTextView.text.toString().toInt() - 1
            checkIfIsNUm(holder.binding.dayTextView, number)
        }
        holder.binding.btnHourIncrease.setOnClickListener {
            val number = holder.binding.hourTextView.text.toString().toInt()+1
            if (checkIfIsNUm(holder.binding.hourTextView,number)){
                holder.binding.hourTextView.text="00"
            }
        }
        holder.binding.btnHourDecrease.setOnClickListener {
            val number = holder.binding.hourTextView.text.toString().toInt()-1
            checkIfIsNUm(holder.binding.hourTextView,number)
        }
        holder.binding.btnMinuteIncrease.setOnClickListener {
            val number = holder.binding.minuteTextView.text.toString().toInt()+1
            if (checkIfIsNUm(holder.binding.minuteTextView,number)){
                holder.binding.minuteTextView.text="00"
            }
        }
        holder.binding.btnMinuteDecrease.setOnClickListener {
            val number = holder.binding.minuteTextView.text.toString().toInt()-1
            checkIfIsNUm(holder.binding.minuteTextView,number)
        }
    }

    fun checkIfIsNUm(textView: TextView, number: Int):Boolean {
        if (number in 0..9) {
            textView.text = "0$number"
            return false
        } else if (number in 10..59) {
            textView.text = number.toString()
            return false
        }else{
            return true
        }
    }

}

