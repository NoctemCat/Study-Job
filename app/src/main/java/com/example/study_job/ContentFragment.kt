package com.example.study_job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.study_job.databinding.FragmentContentBinding
import com.example.study_job.ui.account.AccountFragment
import com.example.study_job.ui.chat.ChatFragment
import com.example.study_job.ui.search.SearchFragment
import com.example.study_job.ui.home.HomeFragment
import com.example.study_job.ui.guide.GuideFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContentFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentContentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null

        if(savedInstanceState == null){
            childFragmentManager.beginTransaction().replace(binding.navHostFragmentActivityMain.id,
                HomeFragment()
            ).commit()
        }

        navView.setOnItemSelectedListener{ item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    childFragmentManager.beginTransaction().replace(binding.navHostFragmentActivityMain.id,
                        HomeFragment()
                    ).commit()
                }
                R.id.navigation_search -> {
                    childFragmentManager.beginTransaction().replace(binding.navHostFragmentActivityMain.id,
                        SearchFragment()
                    ).commit()
                }
                R.id.navigation_guide -> {
                    childFragmentManager.beginTransaction().replace(binding.navHostFragmentActivityMain.id,
                        GuideFragment()
                    ).commit()
                }
                R.id.navigation_chat -> {
                    childFragmentManager.beginTransaction().replace(binding.navHostFragmentActivityMain.id,
                        ChatFragment()
                    ).commit()
                }
                R.id.navigation_account -> {
                    childFragmentManager.beginTransaction().replace(binding.navHostFragmentActivityMain.id,
                        AccountFragment()
                    ).commit()
                }
            }
            true
        }


        return root
    }
    companion object {
        @JvmStatic
        fun newInstance() = ContentFragment()
    }
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
