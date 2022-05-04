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
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.databinding.FragmentAccountBinding
import com.example.study_job.ui.StartTestFragment
import com.example.study_job.ui.guide.PersonaFragment
import com.example.study_job.ui.profession.ProfessionFragment
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
        val gotoPersonaBtn: MaterialButton = binding.gotoPersonaBtn

        gotoPersonaBtn.setOnClickListener {
            val user = SharedPrefManager.getUser(gotoPersonaBtn.context)

            if(user.personality == "-1"){
                val fragmentManager = (gotoPersonaBtn.context as FragmentActivity).supportFragmentManager
                fragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment_activity_main,
                    StartTestFragment()
                ).commit()
            }
            else{
                val fragmentManager = (gotoPersonaBtn.context as FragmentActivity).supportFragmentManager
                fragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment_activity_main,
                    PersonaFragment()
                ).commit()
            }
        }

        val user = SharedPrefManager.getUser(root.context)

        val tvUserName: TextView = binding.tvUserName
        val tvUserAge: TextView = binding.tvUserAge
        val tvUserType: TextView = binding.tvUserType

        tvUserName.text = user.name
        tvUserAge.text = user.age.toString()

        when (user.role) {
            "school" -> {
                tvUserType.text = "Школьник"
            }
            "student" -> {
                tvUserType.text = "Студент"
            }
            "teacher" -> {
                tvUserType.text = "Преподаватель"
            }
        }

        val logout: MaterialButton = binding.logoutBtn
        logout.setOnClickListener {
            SharedPrefManager.logout(logout.context)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}