package com.example.study_job.ui.vacancies

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
import com.example.study_job.databinding.ListVacanciesBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Executors

class VacancyFragment : BaseFragment() {
    private var _binding: ListVacanciesBinding? = null
    private val binding get() = _binding!!

    private var recommended = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListVacanciesBinding.inflate(inflater, container, false)
        arguments?.let {
            recommended = it.getString("search").toString()
        }
        val view: View = binding.root
        val rvProfessions = view.findViewById<View>(R.id.list_vacancies) as RecyclerView

        val gridLayoutManager = LinearLayoutManager(activity)
        rvProfessions.layoutManager = gridLayoutManager
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            val requestHandler = RequestHandler()

            val response: String = if(recommended == ""){
                requestHandler.sendGetRequest(URLs.URL_GET_VACAN)
            }else{
                requestHandler.sendGetRequest(URLs.URL_SEARCH_VACAN + recommended)
            }

            handler.post {
                try {
                    val json = JSONObject(response)

                    val res = json.getJSONObject("results")
                    val profArr: JSONArray = res.getJSONArray("vacancies")

                    var vacancies: ArrayList<Vacancy> = ArrayList()
                    for (i in 0 until profArr.length()) {
                        val vacVacJson: JSONObject = profArr.getJSONObject(i)
                        val vacJson: JSONObject = vacVacJson.getJSONObject("vacancy")

                        val createDate = vacJson.getString("creation-date")
                        val salary_min = vacJson.getString("salary_min")
                        val job_name = vacJson.getString("job-name")
                        val employment = vacJson.getString("employment")
                        val schedule = vacJson.getString("schedule")

                        var newVacan = Vacancy( createDate, job_name, employment, salary_min, schedule)
                        vacancies.add(newVacan)
                    }

                    if(vacancies.size > 0){
                        val rvVacancies = binding.root.findViewById<View>(R.id.list_vacancies) as RecyclerView
                        rvVacancies.adapter = VacancyAdapter(vacancies)
                    }
                    else{
                        Toast.makeText(
                            binding.root.context,
                            "profs zero",
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
        fun newInstance(search: String?) =
            VacancyFragment().apply {
                arguments = Bundle().apply {
                    if (search != null) {
                        putString("search", search)
                    }
                }
            }
    }
}