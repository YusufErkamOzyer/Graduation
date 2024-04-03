package com.yusuferkamozyer.graduation.preferences

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.yusuferkamozyer.graduation.databinding.FragmentSettingsBinding
import com.yusuferkamozyer.graduation.util.CustomColors.ceruleanBlue
import com.yusuferkamozyer.graduation.util.CustomColors.royalBlue
import com.yusuferkamozyer.graduation.util.CustomColors.turquoiseBlue


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pieChart=binding.chart
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(25f, "A"))
        entries.add(PieEntry(35f, "B"))
        entries.add(PieEntry(40f, "C"))

        val dataSet = PieDataSet(entries, "Pie Chart")
        val pieData = PieData(dataSet)
        pieChart.data = pieData

        // Renklerinizi burada belirleyin
        val colors = ArrayList<Int>()
        colors.add(turquoiseBlue)
        colors.add(royalBlue)
        colors.add(ceruleanBlue)
        dataSet.colors = colors
        // İsteğe bağlı: PieChart'ı güncelleyin
        pieChart.invalidate()



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}