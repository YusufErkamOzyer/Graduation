package com.yusuferkamozyer.graduation.preferences

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.pie.PieChart
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.databinding.FragmentProgramBinding
import com.yusuferkamozyer.graduation.databinding.FragmentReviewPlanBinding


class ReviewPlanFragment : Fragment() {
    private lateinit var pieChart: PieChart
    private var _binding: FragmentReviewPlanBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentReviewPlanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pieChart=binding.pieChart
        val segments = listOf(
            Segment("Veri 1", 25f),
            Segment("Veri 2", 35f),
            Segment("Veri 3", 40f)
        )

        val sf1 = SegmentFormatter(Color.RED)
        val sf2 = SegmentFormatter(Color.GREEN)
        val sf3 = SegmentFormatter(Color.BLUE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}