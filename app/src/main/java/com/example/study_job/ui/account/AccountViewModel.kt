package com.example.study_job.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Фрагмент страницы аккаунта"
    }
    val text: LiveData<String> = _text
}