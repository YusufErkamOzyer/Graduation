package com.yusuferkamozyer.graduation.presentation.program

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.databinding.FragmentProgramBinding
import com.yusuferkamozyer.graduation.presentation.program.adapter.AppSuggestionArrayAdapter
import com.yusuferkamozyer.graduation.presentation.program.adapter.ProgramSelectedListAdapter
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem
import com.yusuferkamozyer.graduation.util.AppUtil
import com.yusuferkamozyer.graduation.util.toAppItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProgramFragment : Fragment() {
    private var _binding: FragmentProgramBinding? = null
    private val binding get() = _binding!!
    private lateinit var programSelectedListAdapter: ProgramSelectedListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val autoCompleteTextView=binding.autoCompleteTextView
        val appUtil=AppUtil(requireContext())
        val applicationList=appUtil.getApplicationsList()
        val selectedList= arrayListOf<AppItem>()
        val arrayList= arrayListOf<AppItem>()
        for (app in applicationList){
            arrayList.add(app.toAppItem())
        }
        val adapter=AppSuggestionArrayAdapter(requireContext(), R.layout.item_dropdown,arrayList)
        autoCompleteTextView.setAdapter(adapter)
        setRecyclerView(selectedList,arrayList)
        autoCompleteTextView.onItemClickListener=AdapterView.OnItemClickListener{  parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as AppItem
            selectedList.add(selectedItem)
            arrayList.remove(selectedItem)
            programSelectedListAdapter.updateData(selectedList)
        }
        advanceCreateProgram(selectedList)




    }
    private fun setRecyclerView(selectedItemsList:ArrayList<AppItem>,arrayList:ArrayList<AppItem>) {
        binding.selectedListRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        programSelectedListAdapter=ProgramSelectedListAdapter(selectedItemsList,arrayList)
        binding.selectedListRecyclerView.adapter=programSelectedListAdapter
    }
    private fun updateAutoCompleteTextView(selectedItemsList:ArrayList<AppItem>,autoCompleteTextView:AutoCompleteTextView) {
        val autoCompleteText = selectedItemsList.joinToString(", ") { it.appName}
        autoCompleteTextView.setText(autoCompleteText, false)
    }
    private fun advanceCreateProgram(selectedItemsList: ArrayList<AppItem>){
        binding.advanceProcessButton.setOnClickListener {
            val selectedArray=selectedItemsList.toTypedArray()
            val action=ProgramFragmentDirections.actionProgramFragmentToCreateProgramFragment(selectedArray)
            Navigation.findNavController(it).navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}