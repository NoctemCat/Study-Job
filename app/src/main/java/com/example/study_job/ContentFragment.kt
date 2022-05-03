package com.example.study_job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.study_job.data.test.ProffPair
import com.example.study_job.data.test.ProffPairFragment
import com.example.study_job.databinding.FragmentContentBinding
import com.example.study_job.ui.account.AccountFragment
import com.example.study_job.ui.chat.ChatFragment
import com.example.study_job.ui.search.SearchFragment
import com.example.study_job.ui.home.HomeFragment
import com.example.study_job.ui.guide.GuideFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val navHost: View = binding.navHostFragmentActivityMain
        val navView: BottomNavigationView = binding.navView
        //navView.itemIconTintList = null

        if(savedInstanceState == null){
            childFragmentManager.beginTransaction().replace(navHost.id,
                HomeFragment()
            ).commit()
        }

//        val fragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
//        fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
//            ContentFragment.newInstance()
//        ).commit()


        navView.setOnItemSelectedListener{ item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    childFragmentManager.beginTransaction().replace(navHost.id,
                        HomeFragment()
                    ).commit()
                }
                R.id.navigation_search -> {
                    childFragmentManager.beginTransaction().replace(navHost.id,
                        SearchFragment()
                    ).commit()
                }
                R.id.navigation_guide -> {
                    childFragmentManager.beginTransaction().replace(navHost.id,
                        ProffPairFragment()
                    ).commit()
                }
                R.id.navigation_chat -> {
                    childFragmentManager.beginTransaction().replace(navHost.id,
                        ChatFragment()
                    ).commit()
                }
                R.id.navigation_account -> {
                    childFragmentManager.beginTransaction().replace(navHost.id,
                        AccountFragment()
                    ).commit()
                }
            }
            true
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act: AppCompatActivity = requireActivity() as AppCompatActivity

        act.actionBar?.setDisplayShowHomeEnabled(false)
        act.actionBar?.setDisplayShowTitleEnabled(false)
        act.actionBar?.hide()

        act.setSupportActionBar(binding.toolbar)
        act.supportActionBar?.show()
        act.supportActionBar?.setDisplayShowHomeEnabled(false)
        act.supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    companion object {
        @JvmStatic
        fun newInstance() = ContentFragment()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String?, param2: String?) =
//            ContentFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
