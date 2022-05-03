package com.example.study_job.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.study_job.ContentFragment
import com.example.study_job.HiddenActionBarFragment
import com.example.study_job.R
import com.example.study_job.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton

class RegisterFragment : HiddenActionBarFragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val button: MaterialButton = binding.registerBtn
        val buttonGotoLog : MaterialButton = binding.gotoLogButton

        button.setOnClickListener{view ->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
                ContentFragment.newInstance()
            ).commit()
        }

        buttonGotoLog.setOnClickListener{view->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
                LoginFragment.newInstance()
            ).commit()

        }

        return root
    }


    companion object {
        fun newInstance() = RegisterFragment()
    }
}