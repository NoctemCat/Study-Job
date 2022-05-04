package com.example.study_job.data.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.study_job.R
import com.example.study_job.ui.login.EntityTypeFragment
import com.example.study_job.ui.login.LoginFragment

class SharedPrefManager {
    companion object {
        //the constants
        private const val SHARED_PREF_NAME = "simplifiedcodingsharedpref"
        private const val KEY_ID = "keyid"
        private const val KEY_LOGIN = "keylogin"
        private const val KEY_NAME = "keyname"
        private const val KEY_AGE = "keyage"
        private const val KEY_ROLE = "keyrole"
        private const val KEY_SCHOOL_GRADE = "keygrade"
        private const val KEY_STUDENT_PLACE = "keystudplace"
        private const val KEY_STUDENT_GROUP = "keystudgroup"
        private const val KEY_TEACHER_PLACE = "keysteachplace"
        private const val KEY_TEACHER_POSITION = "keysteachpos"
        private const val KEY_PERSONALITY = "keypersonality"

        fun userLogin(context: Context, user: User) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            editor.putInt(KEY_ID, user.id)
            editor.putString(KEY_LOGIN, user.login)
            editor.putString(KEY_NAME, user.name)
            editor.putInt(KEY_AGE, user.age)
            editor.putString(KEY_ROLE, user.role)
            user.schoolGrade?.let { editor.putInt(KEY_SCHOOL_GRADE, it) }
            editor.putString(KEY_STUDENT_PLACE, user.studentPlace)
            editor.putString(KEY_STUDENT_GROUP, user.studentGroup)
            editor.putString(KEY_TEACHER_PLACE, user.teacherPlace)
            editor.putString(KEY_TEACHER_POSITION, user.teacherPosition)
            editor.putString(KEY_PERSONALITY, user.personality)
            editor.apply()
        }

        fun isLoggedIn(context: Context): Boolean{
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
            )
            return sharedPreferences.getString(KEY_LOGIN, null) != null
        }

        fun getUser(context: Context): User{
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
            )
            return User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_LOGIN, null)!!,
                sharedPreferences.getString(KEY_NAME, null)!!,
                sharedPreferences.getInt(KEY_AGE, -1),
                sharedPreferences.getString(KEY_ROLE, null)!!,
                sharedPreferences.getInt(KEY_SCHOOL_GRADE, -1),
                sharedPreferences.getString(KEY_STUDENT_PLACE, null),
                sharedPreferences.getString(KEY_STUDENT_GROUP, null),
                sharedPreferences.getString(KEY_TEACHER_PLACE, null),
                sharedPreferences.getString(KEY_TEACHER_POSITION, null),
                sharedPreferences.getString(KEY_PERSONALITY, null)
            )
        }

        fun updatePersonality(context: Context, persona: String){
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            editor.putString(KEY_PERSONALITY, persona)
            editor.apply()
        }

        fun logout(context: Context) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(
                R.id.root_fragment_activity_main,
                LoginFragment.newInstance()
            ).commit()
        }
    }
}
