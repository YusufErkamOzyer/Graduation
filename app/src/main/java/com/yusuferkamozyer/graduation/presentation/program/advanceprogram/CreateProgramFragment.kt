package com.yusuferkamozyer.graduation.presentation.program.advanceprogram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.databinding.FragmentCreateProgramBinding
import com.yusuferkamozyer.graduation.databinding.FragmentProgramBinding
import com.yusuferkamozyer.graduation.presentation.program.adapter.CreateProgramAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProgramFragment : Fragment() {
    private var _binding: FragmentCreateProgramBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CreateProgramAdapter
    private val args:CreateProgramFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCreateProgramBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedList=args.selectedList
        adapter= CreateProgramAdapter(selectedList)
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}