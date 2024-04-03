package com.yusuferkamozyer.graduation.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.yusuferkamozyer.graduation.model.AppList

class AppUtil(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getApplicationsList(): ArrayList<AppList> {
        val appList = arrayListOf<AppList>()
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (app in apps) {
            val appName = app.loadLabel(packageManager).toString()
            val packageName = app.packageName
            val appIcon = app.loadIcon(packageManager)
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            val appCategory = applicationInfo.category
            val categoryTitle = ApplicationInfo.getCategoryTitle(context, appCategory)?.toString()
            appList.add(AppList(appName, appIcon, packageName, categoryTitle))
        }
        return appList
    }
}