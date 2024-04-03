package com.yusuferkamozyer.graduation.util

import com.yusuferkamozyer.graduation.model.AppList
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem
import com.yusuferkamozyer.graduation.presentation.selector.model.SelectAppModel

fun AppList.toAppItem():AppItem{
    return AppItem(this.appName,this.icon,this.packageName,false)
}
fun AppList.toSelectedAppModel():SelectAppModel{
    return SelectAppModel(this.appName,this.icon,this.packageName,this.category,isSwitched = false)
}