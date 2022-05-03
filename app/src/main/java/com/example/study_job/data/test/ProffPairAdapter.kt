package com.example.study_job.data.test

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.study_job.R
import com.example.study_job.data.RequestHandler
import com.example.study_job.data.URLs
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.data.user.User
import com.example.study_job.ui.guide.PersonaFragment
import com.google.android.material.button.MaterialButton
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Executors
import kotlin.random.Random


class ProffPairAdapter(products: List<ProffPair>, viewModel: ProffPairViewModel) :
    RecyclerView.Adapter<ProffPairAdapter.ViewHolder>() {

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

                var i = 0
                var ii = 0
                var iii = 0
                var iv = 0
                var v = 0
                var vi = 0
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

                    val arrInt = arrayOf(i, ii, iii, iv, v, vi)
                    var maxIndex = -1
                    var curMax = -1
                    for (index in 0..5) {
                        if (arrInt[index] > curMax) {
                            curMax = arrInt[index]
                            maxIndex = index
                        }
                    }

                    val executor = Executors.newSingleThreadExecutor()
                    val handler = Handler(Looper.getMainLooper())

                    executor.execute {

                        val requestHandler = RequestHandler()
                        val user: User = SharedPrefManager.getUser(holder.button!!.context)

                        val params = HashMap<String, String>()
                        params["id"] = user.id.toString()
                        params["personality"] = maxIndex.toString()

                        val response = requestHandler.sendPostRequest(URLs.URL_UPDATE_USER_PERSONA, params)
                        handler.post {
                            try {
                                val json = JSONObject(response)
                                if (!json.getBoolean("error")) {
                                    //storing the user in shared preferences
                                    SharedPrefManager.updatePersonality(holder.button!!.context, maxIndex.toString())

                                    val fragmentManager =
                                        (holder.button!!.context as FragmentActivity).supportFragmentManager
                                    fragmentManager.beginTransaction().replace(
                                        R.id.nav_host_fragment_activity_main,
                                        PersonaFragment()
                                    ).commit()
                                }else{
                                    Toast.makeText(
                                        holder.button!!.context,
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