package com.yusuferkamozyer.graduation.util

import com.yusuferkamozyer.graduation.model.AppList
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem

fun AppList.toAppItem():AppItem{
    return AppItem(this.appName,this.icon,this.packageName,false)
}