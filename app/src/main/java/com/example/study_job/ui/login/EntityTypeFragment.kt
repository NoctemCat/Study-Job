package com.example.study_job.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.example.study_job.HiddenActionBarFragment
import com.example.study_job.R
import com.example.study_job.databinding.FragmentEntityTypeBinding
import com.google.android.material.button.MaterialButton

class EntityTypeFragment : HiddenActionBarFragment() {
    private var _binding: FragmentEntityTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntityTypeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val button: MaterialButton = binding.registerBtn
        val buttonGotoLog : ImageView = binding.gotoLogButton

        button.setOnClickListener{view ->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(
                R.id.root_fragment_activity_main,
                RegisterFragment.newInstance()
            ).commit()
        }

        buttonGotoLog.setOnClickListener{view->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(
                R.id.root_fragment_activity_main,
                LoginFragment.newInstance()
            ).commit()
        }

        return root
    }


    companion object {
        fun newInstance() = EntityTypeFragment()
    }
}