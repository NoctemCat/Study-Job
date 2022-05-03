package com.example.study_job.ui.login

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.study_job.ContentFragment
import com.example.study_job.HiddenActionBarFragment
import com.example.study_job.R
import com.example.study_job.data.RequestHandler
import com.example.study_job.data.URLs
import com.example.study_job.data.user.SharedPrefManager
import com.example.study_job.data.user.User
import com.example.study_job.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Executors


class RegisterFragment : HiddenActionBarFragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val regViewModel =
            ViewModelProvider(this)[PhisUserViewModel::class.java]
        val role = regViewModel.role

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val button: MaterialButton = binding.registerBtn
        val buttonGotoLog : ImageView = binding.gotoLogButton

        button.setOnClickListener{view ->
            userSignup()
        }

        buttonGotoLog.setOnClickListener{view->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
                LoginFragment.newInstance()
            ).commit()
        }

        val spinner: Spinner = binding.spinRole
        val items = arrayOf("Выберите роль", "Школьник", "Студент", "Преподаватель")
        val adapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, items) }
        spinner.adapter = adapter
        spinner.onItemSelectedListener =  ChooseRoleActivity()

        role.value?.let { refreshVisibility(it) }

        return root
    }

    fun setRole(role: Int){
        val regViewModel =
            ViewModelProvider(this)[PhisUserViewModel::class.java]
        regViewModel.setRole(role)
        refreshVisibility(role)
    }

    private fun refreshVisibility(role: Int){
        val scroll = binding.registerScroll
        val pupilGrade = binding.etRegPupilGrade
        val studentPlace = binding.etRegStudentPlace
        val studentGroup = binding.etRegStudentGroup
        val teacherPlace = binding.etRegTeacherPlace
        val teacherPosition = binding.etRegTeacherPosition
        pupilGrade.visibility = View.GONE
        studentPlace.visibility = View.GONE
        studentGroup.visibility = View.GONE
        teacherPlace.visibility = View.GONE
        teacherPosition.visibility = View.GONE
        when(role){
            1->{
                pupilGrade.visibility = View.VISIBLE
                scroll.smoothScrollTo(0,1400)
            }
            2->{
                studentPlace.visibility = View.VISIBLE
                studentGroup.visibility = View.VISIBLE
                scroll.smoothScrollTo(0,1400)
            }
            3->{
                teacherPlace.visibility = View.VISIBLE
                teacherPosition.visibility = View.VISIBLE
                scroll.smoothScrollTo(0,1400)
            }
        }
    }

    private fun roleToString(value: Int?):String?{
        if(value == null){
            return null
        }
        when(value){
            0->{
                return null
            }
            1->{
                return "school"
            }
            2->{
                return "student"
            }
            3->{
                return "teacher"
            }
        }
        return null
    }

    private fun userSignup()
    {
        val etRegLogin: TextView = binding.etRegLogin
        val etRegPassword: TextView = binding.etRegPass
        val etRegPasswordRep: TextView = binding.etRegPassRepeat
        val etRegName: TextView = binding.etRegName
        val etRegAge: TextView = binding.etRegAge
        val role =
            roleToString(ViewModelProvider(this)[PhisUserViewModel::class.java].role.value)

        val etRegSchoolGrade: TextView = binding.etRegPupilGrade
        val etRegStudentPlace: TextView = binding.etRegStudentPlace
        val etRegStudentGroup: TextView = binding.etRegStudentGroup
        val etRegTeacherPlace: TextView = binding.etRegTeacherPlace
        val etRegTeacherPos: TextView = binding.etRegTeacherPosition

        val login: String = etRegLogin.text.toString()
        val password: String = etRegPassword.text.toString()
        val passwordRep: String = etRegPasswordRep.text.toString()
        val name: String = etRegName.text.toString()
        val age: String = etRegAge.text.toString()

        val schoolGrade: String = etRegSchoolGrade.text.toString()
        val studentPlace: String =  etRegStudentPlace.text.toString()
        val studentGroup: String = etRegStudentGroup.text.toString()
        val teacherPlace: String =  etRegTeacherPlace.text.toString()
        val teacherPos: String =  etRegTeacherPos.text.toString()

        //validating inputs
        if (TextUtils.isEmpty(login)) {
            etRegLogin.error = "Введите логин"
            etRegLogin.requestFocus()
            return
        }
        if (TextUtils.isEmpty(password)) {
            etRegPassword.error = "Введите пароль"
            etRegPassword.requestFocus()
            return
        }
        if (TextUtils.isEmpty(passwordRep)) {
            etRegPasswordRep.error = "Введите пароль"
            etRegPasswordRep.requestFocus()
            return
        }
        if(password != passwordRep){
            etRegPasswordRep.error = "Пароли не совпадают"
            etRegPasswordRep.requestFocus()
            return
        }
        if (TextUtils.isEmpty(name)) {
            etRegName.error = "Введите имя"
            etRegName.requestFocus()
            return
        }
        if (TextUtils.isEmpty(age)) {
            etRegAge.error = "Введите возраст"
            etRegAge.requestFocus()
            return
        }
        if (role == null) {
            etRegAge.error = "Выберите тип пользователя внизу"
            etRegAge.requestFocus()
            return
        }

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            val requestHandler = RequestHandler()

            val params = HashMap<String, String>()
            params["login"] = login
            params["password"] = password
            params["name"] = name
            params["age"] = age
            params["role"] = role

            params["school_grade"] = schoolGrade
            params["student_place"] = studentPlace
            params["student_group"] = studentGroup
            params["teacher_place"] = teacherPlace
            params["teacher_position"] = teacherPos

            val response = requestHandler.sendPostRequest(URLs.URL_REGISTER, params)

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
                            userJson.getString("teacher_position")
                        )

                        //storing the user in shared preferences
                        SharedPrefManager.userLogin(requireContext(), user)

                        //starting the profile activity
                        val fragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
                        fragmentManager.beginTransaction().replace(R.id.root_fragment_activity_main,
                            ContentFragment.newInstance()
                        ).commit()
                    } else {
                        etRegLogin.error = "Случилась неизвестная ошибка"
                    }
                }catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}

class ChooseRoleActivity : Activity(), AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val frag = (view?.context as FragmentActivity).supportFragmentManager
        val fragments: List<Fragment> = frag.fragments
        for (fragment in fragments) {
            if (fragment is RegisterFragment) {
                fragment.setRole(pos)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}