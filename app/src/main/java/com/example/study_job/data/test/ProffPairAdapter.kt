package com.example.study_job.data.test

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.R
import com.google.android.material.button.MaterialButton
import kotlin.random.Random


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
        if (position < mProffPairs.size) {
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
            else if(mViewModel.getResult(position ) == "B"){
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
        else if(position == mProffPairs.size){
            val btn = holder.button!!

            btn.text = "Рандом"
            btn.setOnClickListener{
                val arr: Array<String>? = mViewModel.testResult.value

                if (arr != null) {
                    for (index in 0..41) {
                        if(Random.nextInt(0, 2) == 0){
                            mViewModel.setResult(index, "A")
                        }else{
                            mViewModel.setResult(index, "B")
                        }

                    }
                }
            }
        }
        else {
            holder.button!!.setOnClickListener {
                val arr: Array<String>? = mViewModel.testResult.value

                var i: Int = 0
                var ii: Int = 0
                var iii: Int = 0
                var iv: Int = 0
                var v: Int = 0
                var vi: Int = 0
                if (arr != null) {

                    for (index in 0..41) {
                        if (!(arr[index] == "A" || arr[index] == "B")) {
                            Toast.makeText(
                                holder.button!!.context,
                                "Заполните все выборы",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("res", index.toString() + " " + arr[index])
                            return@setOnClickListener
                        }
                    }

                    if (arr[0] == "A") i++ else ii++
                    if (arr[1] == "A") i++ else iii++
                    if (arr[2] == "A") i++ else iv++
                    if (arr[3] == "A") i++ else v++
                    if (arr[4] == "A") i++ else vi++
                    if (arr[5] == "A") ii++ else iii++
                    if (arr[6] == "A") ii++ else iv++
                    if (arr[7] == "A") ii++ else v++
                    if (arr[8] == "A") ii++ else vi++
                    if (arr[9] == "A") iii++ else iv++
                    if (arr[10] == "A") iii++ else v++
                    if (arr[11] == "A") iii++ else vi++
                    if (arr[12] == "A") iv++ else v++
                    if (arr[13] == "A") iv++ else vi++
                    if (arr[14] == "A") v++ else vi++
                    if (arr[15] == "A") i++ else ii++
                    if (arr[16] == "A") i++ else iii++
                    if (arr[17] == "A") iv++ else v++
                    if (arr[18] == "A") i++ else iv++
                    if (arr[19] == "A") ii++ else iii++
                    if (arr[20] == "A") i++ else vi++
                    if (arr[21] == "A") ii++ else iv++
                    if (arr[22] == "A") ii++ else v++
                    if (arr[23] == "A") ii++ else vi++
                    if (arr[24] == "A") iii++ else v++
                    if (arr[25] == "A") iii++ else v++
                    if (arr[26] == "A") iii++ else vi++
                    if (arr[27] == "A") i++ else v++
                    if (arr[28] == "A") iv++ else vi++
                    if (arr[29] == "A") v++ else vi++
                    if (arr[30] == "A") i++ else ii++
                    if (arr[31] == "A") ii++ else iv++
                    if (arr[32] == "A") i++ else v++
                    if (arr[33] == "A") i++ else vi++
                    if (arr[34] == "A") ii++ else iv++
                    if (arr[35] == "A") ii++ else iii++
                    if (arr[36] == "A") ii++ else vi++
                    if (arr[37] == "A") iii++ else iv++
                    if (arr[38] == "A") iii++ else v++
                    if (arr[39] == "A") iv++ else v++
                    if (arr[40] == "A") vi++ else iii++
                    if (arr[41] == "A") iv++ else vi++

                    var arrInt = arrayOf(i, ii, iii, iv, v, vi)
                    var maxIndex = -1
                    var curMax = -1
                    for (index in 0..5) {
                        if (arrInt[index] > curMax) {
                            curMax = arrInt[index]
                            maxIndex = index
                        }
                    }

                    Toast.makeText(
                        holder.button!!.context,
                        (maxIndex+1).toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mProffPairs.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mProffPairs.size || position == mProffPairs.size + 1) R.layout.item_button else R.layout.item_test_pair
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvA: TextView? = itemView.findViewById<View>(R.id.tvChoiceA) as TextView?
        var tvB: TextView? = itemView.findViewById<View>(R.id.tvChoiceB) as TextView?
        var button: MaterialButton? = itemView.findViewById<View>(R.id.test_submit) as MaterialButton?
    }
}