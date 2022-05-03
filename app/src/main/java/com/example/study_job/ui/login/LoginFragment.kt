package com.example.study_job.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.study_job.ContentFragment
import com.example.study_job.HiddenActionBarFragment
import com.example.study_job.R
import com.example.study_job.data.RequestHandler
import com.example.study_job.data.URLs
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.data.user.User
import com.example.study_job.databinding.FragmentLoginBinding
import com.google.android.material.button.MaterialButton
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class LoginFragment : HiddenActionBarFragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val button: MaterialButton = binding.loginBtn
        val buttonGotoReg : MaterialButton = binding.gotoRegButton

        button.setOnClickListener{view ->
            userLogin()
        }

        buttonGotoReg.setOnClickListener{view->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
                EntityTypeFragment.newInstance()
            ).commit()
        }

        return root
    }

    private fun userLogin()
    {
        val editTextLogin: TextView = binding.etLogin
        val editTextPassword: TextView = binding.etPassword

        val login: String = editTextLogin.text.toString()
        val password: String = editTextPassword.text.toString()

        //validating inputs
        if (TextUtils.isEmpty(login)) {
            editTextLogin.error = "Введите логин"
            editTextLogin.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.error = "Введите пароль"
            editTextPassword.requestFocus()
            return
        }

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            val requestHandler = RequestHandler()

            val params = HashMap<String, String>()
            params["login"] = login
            params["password"] = password

            val response = requestHandler.sendPostRequest(URLs.URL_LOGIN, params)

            handler.post {
                try {
                    val json = JSONObject(response)
                    if (!json.getBoolean("error")) {
                        val userJson: JSONObject = json.getJSONObject("user")

                        //creating a new user object
                        val user = User(
                            userJson.getInt("id"),
                            userJson.getString("login"),
                            userJson.getString("name"),
                            userJson.getInt("age"),
                            userJson.getString("role"),
                            userJson.getInt("school_grade"),
                            userJson.getString("student_place"),
                            userJson.getString("student_group"),
                            userJson.getString("teacher_place"),
                            userJson.getString("teacher_position"),
                            userJson.getString("personality")
                        )

                        //storing the user in shared preferences
                        SharedPrefManager.userLogin(requireContext(), user)

                        //starting the profile activity
                        val fragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
                        fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
                            ContentFragment.newInstance()
                        ).commit()
                    } else {
                        editTextLogin.error = "Неверный логин или пароль"
                        editTextLogin.requestFocus()
                    }
                }catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}