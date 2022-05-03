package com.example.study_job.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhisUserViewModel : ViewModel() {
    private val _role = MutableLiveData<Int>().apply {
        value = 0
    }

    val role: LiveData<Int> = _role

    fun setRole(roleLocal: Int){
        _role.value = roleLocal
    }
}