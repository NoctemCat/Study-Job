package com.example.study_job.data.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.BaseFragment
import com.example.study_job.R
import com.example.study_job.databinding.ListTestPairBinding

class ProffPairFragment : BaseFragment() {
    private var _binding: ListTestPairBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListTestPairBinding.inflate(inflater, container, false)
        val viewModel =
            ViewModelProvider(this)[ProffPairViewModel::class.java]

        val view: View = binding.root
        val rvProductTypes = view.findViewById<View>(R.id.list_test_pair) as RecyclerView
        val gridLayoutManager = LinearLayoutManager(activity)
        rvProductTypes.layoutManager = gridLayoutManager

        val productTypes: List<ProffPair> = ProffPair.getProffPairList()
        rvProductTypes.adapter = ProffPairAdapter(productTypes, viewModel)

        return view
    }


}