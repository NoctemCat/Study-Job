package com.example.study_job.ui.guide

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
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.databinding.FragmentGuideBinding
import com.example.study_job.ui.StartTestFragment
import com.example.study_job.ui.profession.ProfessionFragment
import com.google.android.material.button.MaterialButton

class GuideFragment : BaseFragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuideBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnRec: MaterialButton = binding.recomBtn

        btnRec.setOnClickListener {
            val user = SharedPrefManager.getUser(btnRec.context)

            if(user.personality == "-1"){
                val fragmentManager = (btnRec.context as FragmentActivity).supportFragmentManager
                fragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment_activity_main,
                    StartTestFragment()
                ).commit()
            }
            else{
                val fragmentManager = (btnRec.context as FragmentActivity).supportFragmentManager
                fragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment_activity_main,
                    ProfessionFragment.newInstance(true)
                ).commit()
            }


        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}