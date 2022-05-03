package com.example.study_job.ui.profession

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.BaseFragment
import com.example.study_job.R
import com.example.study_job.data.RequestHandler
import com.example.study_job.data.URLs
import com.example.study_job.data.URLs.URL_GET_PERSONAS
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.data.user.User
import com.example.study_job.databinding.ListProffesionsBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Executors

class ProfessionFragment : BaseFragment() {
    private var _binding: ListProffesionsBinding? = null
    private val binding get() = _binding!!

    private var recommended = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListProffesionsBinding.inflate(inflater, container, false)
        arguments?.let {
            recommended = it.getBoolean("recom")
        }
        val view: View = binding.root
        val rvProfessions = view.findViewById<View>(R.id.list_professions) as RecyclerView

        val gridLayoutManager = LinearLayoutManager(activity)
        rvProfessions.layoutManager = gridLayoutManager
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            Log.d("exec", "begin async")
            val requestHandler = RequestHandler()
            val user: User = SharedPrefManager.getUser(binding.root.context)

            val params = HashMap<String, String>()
            params["persona_id"] = user.personality.toString()

            val response: String = if(recommended){
                requestHandler.sendPostRequest(URLs.URL_PROFS_BY_PERSONA, params)
            }else{
                requestHandler.sendPostRequest(URLs.URL_PROFS_GET, params)
            }

            val responsePersonas = requestHandler.sendPostRequest(URL_GET_PERSONAS, params)

            Log.d("exec", "before handler")
            handler.post {
                try {
                    Log.d("exec", "Inside handler")
                    val json = JSONObject(response)
                    val personasJson = JSONObject(responsePersonas)
                    if (!json.getBoolean("error") && !personasJson.getBoolean("error")) {
                        Log.d("exec", "No error")
                        val profArr: JSONArray = json.getJSONArray("professions")
                        val personaArr: JSONArray = personasJson.getJSONArray("personas")

                        var profs: ArrayList<Profession> = ArrayList()
                        for (i in 0 until profArr.length()) {
                            val prof: JSONObject = profArr.getJSONObject(i)

                            val profId = prof.getInt("id")
                            val science = prof.getString("science")
                            val code = prof.getString("code")
                            val prof_spec = prof.getString("prof_spec")
                            val qual = prof.getString("qual")
                            val persona_id = prof.getString("persona_id")

                            var newProf = Profession(profId, science, code, prof_spec, qual, null)

                            if(persona_id != "-1"){
                                val perType: JSONObject = personaArr.getJSONObject(persona_id.toInt())
                                val persName = perType.getString("name")
                                newProf.persona = persName
                            }
                            profs.add(newProf)
                        }
                        Log.d("exec", "After for")

                        if(profs.size > 0){
                            Log.d("exec", "Set adapter")
                            val rvProfessions = binding.root.findViewById<View>(R.id.list_professions) as RecyclerView
                            rvProfessions.adapter = ProfessionAdapter(profs)
                        }
                        else{
                            Toast.makeText(
                                binding.root.context,
                                "profs zero",
                                Toast.LENGTH_LONG
                            ).show()
                        }
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

    companion object {
        @JvmStatic
        fun newInstance(recommend: Boolean?) =
            ProfessionFragment().apply {
                arguments = Bundle().apply {
                    if (recommend != null) {
                        putBoolean("recom", recommend)
                    }
                }
            }
    }
}