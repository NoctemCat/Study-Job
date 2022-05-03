package com.example.study_job.data.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProffPairViewModel : ViewModel() {
    private val _testResult = MutableLiveData<Array<String>>().apply {
        value = Array(42){ " " }
    }
    val testResult: LiveData<Array<String>> = _testResult

    fun setResult(pos: Int, res: String){
        if(pos in 1..41){
            _testResult.value?.set(pos, res)
        }
    }

    fun getResult(pos: Int): String{
        return _testResult.value?.get(pos) ?: " "
    }
}