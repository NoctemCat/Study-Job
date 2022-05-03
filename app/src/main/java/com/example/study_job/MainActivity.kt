package com.example.study_job

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.study_job.databinding.ActivityMainBinding
import com.example.study_job.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.root_fragment_activity_main, LoginFragment.newInstance())
            .commit()
    }
}