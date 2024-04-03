package com.yusuferkamozyer.graduation.presentation.home

import androidx.lifecycle.ViewModel
import com.yusuferkamozyer.graduation.model.CurrentTimeDay
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor() : ViewModel() {

    fun getCurrentTime(): CurrentTimeDay {
        val calendar = Calendar.getInstance()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val startMillis = calendar.timeInMillis
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }
        val endMillis = calendar.timeInMillis
        return CurrentTimeDay(startMillis, endMillis)
    }
}