package com.example.study_job.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
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
        val regViewModel =
            ViewModelProvider(this)[PhisUserViewModel::class.java]
        val role = regViewModel.role

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

        val spinner: Spinner = binding.spinRole
        val items = arrayOf("Выберите роль", "Школьник", "Студент", "Преподаватель")
        val adapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, items) }
        spinner.adapter = adapter
        spinner.onItemSelectedListener =  ChooseRoleActivity()

        role.value?.let { refreshVisibility(it) }

        return root
    }

    fun setRole(role: Int){
        val regViewModel =
            ViewModelProvider(this)[PhisUserViewModel::class.java]
        regViewModel.setRole(role)
        refreshVisibility(role)
    }

    private fun refreshVisibility(role: Int){
        val pupilGrade = binding.etRegPupilGrade
        val studentPlace = binding.etRegStudentPlace
        val studentGroup = binding.etRegStudentGroup
        val teacherPlace = binding.etRegTeacherPlace
        val teacherPosition = binding.etRegTeacherPosition
        pupilGrade.visibility = View.GONE
        studentPlace.visibility = View.GONE
        studentGroup.visibility = View.GONE
        teacherPlace.visibility = View.GONE
        teacherPosition.visibility = View.GONE
        when(role){
            1->{
                pupilGrade.visibility = View.VISIBLE
            }
            2->{
                studentPlace.visibility = View.VISIBLE
                studentGroup.visibility = View.VISIBLE
            }
            3->{
                teacherPlace.visibility = View.VISIBLE
                teacherPosition.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}

class ChooseRoleActivity : Activity(), AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val frag = (view?.context as FragmentActivity).supportFragmentManager
        val fragments: List<Fragment> = frag.fragments
        for (fragment in fragments) {
            if (fragment is RegisterFragment) {
                fragment.setRole(pos)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}