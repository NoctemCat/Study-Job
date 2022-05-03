package com.example.study_job.ui.vacancies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.R
import com.example.study_job.data.test.ProffPair
import com.example.study_job.data.test.ProffPairViewModel
import com.google.android.material.button.MaterialButton

class VacancyAdapter (vacancies: ArrayList<Vacancy>) :
    RecyclerView.Adapter<VacancyAdapter.ViewHolder>() {
    private val mVacancies: ArrayList<Vacancy> = vacancies

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VacancyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.item_vacancy, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VacancyAdapter.ViewHolder, position: Int) {
        val vacancy: Vacancy = mVacancies[position]

        holder.tvDate.text = vacancy.createDate
        holder.tvJobName.text = vacancy.jobName
        holder.tvSchedule.text = vacancy.schedule
        holder.tvPrice.text = vacancy.salaryMin
        holder.tvEmployment.text = vacancy.employment
    }

    override fun getItemCount(): Int {
        return mVacancies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate: TextView = itemView.findViewById<View>(R.id.tvDate) as TextView
        var tvJobName: TextView = itemView.findViewById<View>(R.id.tvJobName) as TextView
        var tvSchedule: TextView = itemView.findViewById<View>(R.id.tvSchedule) as TextView
        var tvPrice: TextView = itemView.findViewById<View>(R.id.tvPrice) as TextView
        var tvEmployment: TextView = itemView.findViewById<View>(R.id.tvEmployment) as TextView
    }
}