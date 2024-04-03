package com.yusuferkamozyer.graduation.presentation.selector

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yusuferkamozyer.graduation.MainActivity
import com.yusuferkamozyer.graduation.databinding.ActivitySelectAppBinding



class SelectAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectAppBinding
    private lateinit var adapter: SelectAppAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*val arrayList = AppUtil(this).getApplicationsList()
        val selectorArrayList= arrayListOf<SelectAppModel>()
        arrayList.forEach { appList ->
            selectorArrayList.add(appList.toSelectedAppModel())
        }*/
        /*binding.selectorRecyclerView.layoutManager=LinearLayoutManager(this)
        adapter=SelectAppAdapter(selectorArrayList)
        binding.selectorRecyclerView.adapter=adapter*/

        binding.confirmButton.setOnClickListener {
            /*for (i in adapter.getSelectedApps()){
                println(i.appName)
            }
            binding.finishButton.isEnabled=true*/
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.finishButton.setOnClickListener {
        }

    }


    override fun onResume() {
        super.onResume()
    }
}