package com.example.study_job.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.study_job.BaseFragment
import com.example.study_job.R
import com.example.study_job.data.test.ProffPairFragment
import com.example.study_job.databinding.FragmentSearchBinding
import com.example.study_job.ui.profession.ProfessionFragment

class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[SearchViewModel::class.java]

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val profBtn = binding.profBtn
        profBtn.setOnClickListener{
            val fragmentManager = (profBtn.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment_activity_main,
                ProfessionFragment.newInstance(false)
            ).commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}