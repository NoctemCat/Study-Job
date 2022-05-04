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
        val navView: BottomNavigationView = binding.navView

        if(savedInstanceState == null){
            replaceFragment(HomeFragment())
        }

        navView.setOnItemSelectedListener{ item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.navigation_search -> {
                    replaceFragment(SearchFragment())
                }
                R.id.navigation_guide -> {
                    replaceFragment(GuideFragment())
                }
                R.id.navigation_chat -> {
                    replaceFragment(ChatFragment())
                }
                R.id.navigation_account -> {
                    replaceFragment(AccountFragment())
                }
            }
            true
        }



        return root
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main,
            fragment
        ).commit()
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
