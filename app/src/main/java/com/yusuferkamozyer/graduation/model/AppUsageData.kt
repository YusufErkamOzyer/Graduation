package com.yusuferkamozyer.graduation.model

import android.graphics.drawable.Drawable

class AppUsageData(
    val appName: String,
    val appPackage: String,
    val appCategory: String?,
    val appIcon:Drawable,
    val appUsage:Long?
)
