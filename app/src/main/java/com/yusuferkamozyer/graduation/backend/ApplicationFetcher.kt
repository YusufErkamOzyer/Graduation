package com.yusuferkamozyer.graduation.backend

import android.annotation.SuppressLint
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import java.util.Collections
import java.util.TreeMap

class ApplicationFetcher {
    companion object {

        private const val HOUR_RANGE : Int = 1000 * 3600 * 24

        /**
         * @param context The current context of the application.
         * @return A list of User Installed Applications in the form of a custom wrapper.
         *
         * This method is used to return a list of User Installed Applications by accessing the Package manager of the
         * current context.It also excludes the pre-installed System-Applications by checking the flag of the
         * particular ApplicationInfo.
         */
        @SuppressLint("QueryPermissionsNeeded")
        public fun getInstalledApplications(context: Context) : List<ApplicationInfoWrapper> {
            val packageManager = context.packageManager
                ?: return Collections.emptyList<ApplicationInfoWrapper>()
            // Querying the PackageManager to get all the InstalledApplications by passing 0 flags
            val applications = packageManager.getInstalledApplications(0)
            val installedApplications = ArrayList<ApplicationInfoWrapper>()
            for(applicationInfo in applications) {
                if(!isSystemApplication(applicationInfo)) {
                    val applicationName = packageManager.getApplicationLabel(applicationInfo).toString()
                    val applicationIcon = packageManager.getApplicationIcon(applicationInfo)
                    installedApplications.add(ApplicationInfoWrapper(applicationName,applicationIcon))
                }
            }
            return installedApplications
        }

        /**
         * @param context The context from which the active app needs to be identified.
         * @return The name of the application currently active,
         *         if no active application is found it returns null.
         *
         * This method is used to return the current active application on the given context by using the Usage Stats
         * Manager.
         */
        public fun getForegroundApplication(context: Context) : String? {
            val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            val currentTime = System.currentTimeMillis()
            val usageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,currentTime - HOUR_RANGE, currentTime)
            if(usageStats != null) {
                val sortedMap = TreeMap<Long, UsageStats>()
                for(usageStat in usageStats) {
                    val time : Long = usageStat.lastTimeUsed
                    sortedMap[time] = usageStat
                }
                if(sortedMap.isNotEmpty()) {
                    val lastKey = sortedMap.lastKey()
                    val foregroundAppUsageStats = sortedMap.get(lastKey)
                    if(foregroundAppUsageStats != null) {
                        return context.packageManager.getApplicationLabel(
                            context.packageManager.getApplicationInfo(
                                foregroundAppUsageStats.packageName,
                                0
                            )
                        ).toString()
                    }
                }
            }
            return null
        }
        private fun isSystemApplication(applicationInfo: ApplicationInfo) :Boolean {
            return (applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
        }
    }
}