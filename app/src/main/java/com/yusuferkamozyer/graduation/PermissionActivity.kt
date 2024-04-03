package com.yusuferkamozyer.graduation

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yusuferkamozyer.graduation.databinding.ActivityPermissionBinding
import com.yusuferkamozyer.graduation.presentation.selector.SelectAppActivity


class PermissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermissionBinding
    private val PREFS_NAME_USAGE_STATS = "PrefUsageStats"
    private val PREF_USAGE_STATS_GRANTED = "usageStatsGranted"
    private val PREFS_NAME_APP_BLOCK = "PrefAppBlock"
    private val PREF_APP_BLOCK_GRANTED = "appBlockGranted"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonGrantPermissionUsageTracker.setOnClickListener {
            getPermissionUsageTrack()
        }
        binding.buttonGrantPermissionAppBlock.setOnClickListener {
            getPermissionAppBlock()
        }
        checkIsPermissionsGranted()
        goToMainActivity()




    }
    private fun checkIsPermissionsGranted(){
        if (isUsageStatsPermissionGrantedUsageTrack()) {
            binding.accessButtonUsageTracker.setBackgroundResource(R.drawable.access_granted)
        } else {
            binding.accessButtonUsageTracker.setBackgroundResource(R.drawable.access_needed)
        }
        if (isUsageStatsPermissionGrantedAppBlock()){
            binding.accessButtonAppBlock.setBackgroundResource(R.drawable.access_granted)
        }
        else{
            binding.accessButtonAppBlock.setBackgroundResource(R.drawable.access_needed)
        }
    }
    private fun savePermissionStatusAppBlock(granted: Boolean){
        val prefs = getSharedPreferences(PREFS_NAME_APP_BLOCK, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(PREF_APP_BLOCK_GRANTED, granted).apply()
    }
    private fun isUsageStatsPermissionGrantedAppBlock(): Boolean {
        val prefs = getSharedPreferences(PREFS_NAME_APP_BLOCK, Context.MODE_PRIVATE)
        return prefs.getBoolean(PREF_APP_BLOCK_GRANTED, false)
    }
    private fun savePermissionStatusUsageTrack(granted: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME_USAGE_STATS, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(PREF_USAGE_STATS_GRANTED, granted).apply()
    }
    private fun goToMainActivity(){
        binding.goToMainActivity.setOnClickListener {
            if (isUsageStatsPermissionGrantedUsageTrack()&&isUsageStatsPermissionGrantedAppBlock()){
                val intent=Intent(this, SelectAppActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Please give permission access",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun isUsageStatsPermissionGrantedUsageTrack(): Boolean {
        val prefs = getSharedPreferences(PREFS_NAME_USAGE_STATS, Context.MODE_PRIVATE)
        return prefs.getBoolean(PREF_USAGE_STATS_GRANTED, false)
    }

    private var isUsageStatsPermissionGranted = false

    private fun checkUsageStatsPermission() {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val packageName = packageName
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        isUsageStatsPermissionGranted = mode == AppOpsManager.MODE_ALLOWED
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS) {
            checkUsageStatsPermission()
            if (isUsageStatsPermissionGranted) {
                binding.accessButtonUsageTracker.setBackgroundResource(R.drawable.access_granted)
                savePermissionStatusUsageTrack(true)
            } else {
                binding.accessButtonUsageTracker.setBackgroundResource(R.drawable.access_needed)
                savePermissionStatusUsageTrack(false)
            }
        }else if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                binding.accessButtonAppBlock.setBackgroundResource(R.drawable.access_needed)
                savePermissionStatusAppBlock(false)
            } else {
                binding.accessButtonAppBlock.setBackgroundResource(R.drawable.access_granted)
                savePermissionStatusAppBlock(true)
            }

        }
    }



    private fun getPermissionUsageTrack() {
        try {
            val appOps = this.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val packageName = this.packageName
            val mode = appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                packageName
            )
            if (mode == AppOpsManager.MODE_ALLOWED) {
                binding.accessButtonUsageTracker.setBackgroundResource(R.drawable.access_granted)
            } else {
                val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS)

            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }

    private fun getPermissionAppBlock() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE)
                }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }

    }

    companion object {
        var ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469
        private const val REQUEST_CODE_OVERLAY_PERMISSION = 123
        private val MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS = 1000
    }
}