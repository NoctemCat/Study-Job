package com.example.study_job.data.user

class User(
    var id: Int,
    var login: String,
    var name: String,
    var age: Int,
    var role: String,
    var schoolGrade: Int? = null,
    var studentPlace: String? = null,
    var studentGroup: String? = null,
    var teacherPlace: String? = null,
    var teacherPosition: String? = null,
    var personality: String? = null
){
    init {
        if(schoolGrade == -1){
            schoolGrade = null
        }
    }
}