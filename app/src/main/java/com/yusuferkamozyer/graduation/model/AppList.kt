package com.yusuferkamozyer.graduation.model

import android.graphics.drawable.Drawable

data class AppList(
    val appName: String,
    val icon: Drawable,
    val packageName: String,
    val category:String?=null
)
