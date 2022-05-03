package com.example.study_job.data.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.R
import com.google.android.material.button.MaterialButton


class ProductTypesAdapter(products: List<ProffPair>, viewModel: ProffPairViewModel) :
    RecyclerView.Adapter<ProductTypesAdapter.ViewHolder>() {

    private val mProffPairs: List<ProffPair> = products
    private val mViewModel: ProffPairViewModel = viewModel

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == R.layout.item_test_pair){
            val inflater = LayoutInflater.from(viewGroup.context)
            val itemView: View = inflater.inflate(R.layout.item_test_pair, viewGroup, false)
            ViewHolder(itemView)
        } else {
            val inflater = LayoutInflater.from(viewGroup.context)
            val itemView: View = inflater.inflate(R.layout.item_button, viewGroup, false)
            ViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == mProffPairs.size) {
            holder.button!!.setOnClickListener {
                Toast.makeText(
                    holder.tvA?.context,
                    "Button Clicked",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            val proffPair: ProffPair = mProffPairs[position]

            val tvA = holder.tvA
            tvA!!.text = proffPair.aProfession
            val tvB = holder.tvB
            tvB!!.text = proffPair.bProfession

            val colorActive = ContextCompat.getColorStateList(holder.tvA!!.context, R.color.light_blue)
            val colorUnactive = ContextCompat.getColorStateList(holder.tvA!!.context, R.color.light_gray)

            if(mViewModel.getResult(position) == "A"){
                tvA.backgroundTintList = colorActive
                tvB.backgroundTintList = colorUnactive
            }
            else if(mViewModel.getResult(position) == "B"){
                tvA.backgroundTintList = colorUnactive
                tvB.backgroundTintList = colorActive
            }
            else{
                tvA.backgroundTintList = colorUnactive
                tvB.backgroundTintList = colorUnactive
            }

            tvA.setOnClickListener {
                mViewModel.setResult(position, "A")
                tvA.backgroundTintList = colorActive
                tvB.backgroundTintList = colorUnactive
            }

            tvB.setOnClickListener {
                mViewModel.setResult(position, "B")
                tvA.backgroundTintList = colorUnactive
                tvB.backgroundTintList = colorActive
            }

        }
    }

    override fun getItemCount(): Int {
        return mProffPairs.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mProffPairs.size) R.layout.item_button else R.layout.item_test_pair
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvA: TextView? = itemView.findViewById<View>(R.id.tvChoiceA) as TextView?
        var tvB: TextView? = itemView.findViewById<View>(R.id.tvChoiceB) as TextView?
        var button: MaterialButton? = itemView.findViewById<View>(R.id.test_submit) as MaterialButton?
    }
}