package com.yusuferkamozyer.graduation.presentation.home

import android.app.Activity
import android.app.AlertDialog
import android.app.AppOpsManager
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.databinding.FragmentHomeBinding
import com.yusuferkamozyer.graduation.model.AppUsageData
import com.yusuferkamozyer.graduation.presentation.home.adapter.HomeAppUsageAdapter
import com.yusuferkamozyer.graduation.util.AppUtil
import com.yusuferkamozyer.graduation.util.GraphicCharts
import dagger.hilt.android.AndroidEntryPoint
import java.security.KeyStore


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val graphicCharts=GraphicCharts()

    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var mainAdapter: HomeAppUsageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //displayUsedApps()
        //getPermissionUsageTrack()
        GraphicCharts().setupPieCharts(binding.pieChart2)
        demoDisplay()
    }
    private fun demoDisplay(){
        setAdapter(arrayListOf(AppUsageData("Demo App","com.yusuferkamozyer.demoapp","s",
            ContextCompat.getDrawable(requireContext(),R.drawable.ic_android_animal)!!,10L
        ),AppUsageData("Demo App","com.yusuferkamozyer.demoapp","s",
            ContextCompat.getDrawable(requireContext(),R.drawable.ic_android_animal)!!,10L
        )))
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayUsedApps(){
        val appUtil=AppUtil(requireContext())
        val applicationList=appUtil.getApplicationsList()

        val timeInterval = viewModel.getCurrentTime()
        val appUsageArrayList = arrayListOf<AppUsageData>()
        val mUsageStatsManager =
            requireContext().getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val lUsageStatsMap = mUsageStatsManager.queryAndAggregateUsageStats(
            timeInterval.startMillis,
            timeInterval.endMillis
        )

        for (app in applicationList) {
            val totalTimeUsageInMillis = lUsageStatsMap[app.packageName]?.totalTimeInForeground
            if (!(totalTimeUsageInMillis?.toInt() == 0 || totalTimeUsageInMillis == null)) {
                appUsageArrayList.add(
                    AppUsageData(
                        app.appName,
                        app.packageName,
                        app.category,
                        app.icon,
                        totalTimeUsageInMillis
                    )
                )
            }
        }
        setAdapter(appUsageArrayList)

    }
    private fun getPermissionUsageTrack() {
        val appOps = requireContext().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val packageName = requireContext().packageName
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        if (mode == AppOpsManager.MODE_ALLOWED) {
            println("true")
        } else {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // İşleme devam et
                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.permission_need)
                        .setMessage(R.string.permission_info_box)
                        .setPositiveButton(R.string.navigate_to_settings) { dialog, _ ->
                            dialog.dismiss()
                            // Ayarlar sayfasını açmak için izin iste
                            startActivity(intent)
                        }
                        .setNegativeButton(R.string.cancel) { dialog, _ ->
                            dialog.dismiss()
                            // İşlemi iptal et
                        }
                        .show()
                }
            }.launch(intent)

        }
    }
    private fun setAdapter(appUsageArrayList:ArrayList<AppUsageData>){
        mainAdapter= HomeAppUsageAdapter(appUsageArrayList)
        binding.usedAppRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.usedAppRecyclerView.adapter=mainAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}