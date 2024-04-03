package com.yusuferkamozyer.graduation.presentation.selector.model

import android.graphics.drawable.Drawable

data class SelectAppModel(
    val appName: String,
    val icon: Drawable,
    val packageName: String,
    val category:String?=null,
    var isSwitched:Boolean
)
