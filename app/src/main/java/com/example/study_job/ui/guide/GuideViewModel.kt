package com.example.study_job.ui.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuideViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Фрагмент страницы гайда"
    }
    val text: LiveData<String> = _text
}