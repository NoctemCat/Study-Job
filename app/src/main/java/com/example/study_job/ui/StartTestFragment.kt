package com.example.study_job.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.study_job.BaseFragment
import com.example.study_job.HiddenActionBarFragment
import com.example.study_job.R
import com.example.study_job.data.test.ProffPairFragment
import com.example.study_job.databinding.FragmentNoPersonaBinding
import com.google.android.material.button.MaterialButton

class StartTestFragment : BaseFragment() {
    private var _binding: FragmentNoPersonaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoPersonaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnRec: MaterialButton = binding.gotoTestBtn
        btnRec.setOnClickListener {
            val fragmentManager = (btnRec.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment_activity_main,
                ProffPairFragment()
            ).commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}