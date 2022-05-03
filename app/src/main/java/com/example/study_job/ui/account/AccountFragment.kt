package com.example.study_job.ui.account

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
import com.example.study_job.databinding.FragmentAccountBinding
import com.example.study_job.ui.guide.PersonaFragment
import com.google.android.material.button.MaterialButton

class AccountFragment : BaseFragment(){
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textAccount
        val gotoPersonaBtn: MaterialButton = binding.gotoPersonaBtn

        val notificationsViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        gotoPersonaBtn.setOnClickListener {
            val fragmentManager =
                (binding.root.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment_activity_main,
                PersonaFragment()
            ).commit()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}