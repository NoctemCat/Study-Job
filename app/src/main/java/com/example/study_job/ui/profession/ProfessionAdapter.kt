package com.example.study_job.ui.profession

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.R
import com.example.study_job.data.test.ProffPair
import com.example.study_job.data.test.ProffPairViewModel
import com.google.android.material.button.MaterialButton

class ProfessionAdapter (professions: ArrayList<Profession>) :
    RecyclerView.Adapter<ProfessionAdapter.ViewHolder>() {
    private val mProfessions: ArrayList<Profession> = professions

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfessionAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.item_profession, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfessionAdapter.ViewHolder, position: Int) {
        val profession: Profession = mProfessions[position]

        holder.tvScience.text = profession.science
        holder.tvCode.text = profession.code
        holder.tvProfSpec.text = profession.profSpec
        holder.tvQual.text = profession.qual
        holder.tvPersona.text = profession.persona
    }

    override fun getItemCount(): Int {
        return mProfessions.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvScience: TextView = itemView.findViewById<View>(R.id.tvScience) as TextView
        var tvCode: TextView = itemView.findViewById<View>(R.id.tvCode) as TextView
        var tvProfSpec: TextView = itemView.findViewById<View>(R.id.tvProfSpec) as TextView
        var tvQual: TextView = itemView.findViewById<View>(R.id.tvQual) as TextView
        var tvPersona: TextView = itemView.findViewById<View>(R.id.tvPersona) as TextView
    }
}