package com.example.study_job.ui.guide

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.study_job.BaseFragment
import com.example.study_job.R
import com.example.study_job.data.RequestHandler
import com.example.study_job.data.URLs
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.data.user.User
import com.example.study_job.databinding.FragmentPersonaBinding
import com.example.study_job.ui.StartTestFragment
import com.google.android.material.button.MaterialButton
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Executors

class PersonaFragment: BaseFragment() {
    private var _binding: FragmentPersonaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val gotoTest: MaterialButton = binding.gotoTestBtn

        gotoTest.setOnClickListener {
            val fragmentManager = (binding.root.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main,
                StartTestFragment()
            ).commit()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val user = SharedPrefManager.getUser(binding.root.context)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            val requestHandler = RequestHandler()
            val user: User = SharedPrefManager.getUser(binding.root.context)

            val params = HashMap<String, String>()
            params["id"] = user.personality.toString()

            val response = requestHandler.sendPostRequest(URLs.URL_GET_PERSONA, params)
            handler.post {
                try {
                    val json = JSONObject(response)
                    if (!json.getBoolean("error")) {

                        val persona: JSONObject = json.getJSONObject("persona")

                        binding.personaName.text = persona.getString("name")
                        binding.personaDesc.text = persona.getString("desc")
                    }else{
                        Toast.makeText(
                            binding.root.context,
                            json.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}