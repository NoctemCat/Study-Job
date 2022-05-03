package com.example.study_job.data

object URLs {
    private const val ROOT_LOGIN_URL = "http://192.168.0.62:81/users.php?apicall="
    const val URL_REGISTER = ROOT_LOGIN_URL + "signup"
    const val URL_LOGIN = ROOT_LOGIN_URL + "login"
    const val URL_UPDATE_USER_PERSONA = ROOT_LOGIN_URL + "update_test"

    private const val ROOT_PERSONA_URL = "http://192.168.0.62:81/persona.php?apicall="
    const val URL_GET_PERSONA = ROOT_PERSONA_URL + "get_persona"
    const val URL_GET_PERSONAS = ROOT_PERSONA_URL + "get"

    private const val ROOT_PROFESSIONS_URL = "http://192.168.0.62:81/profession.php?apicall="
    const val URL_PROFS_BY_PERSONA = ROOT_PROFESSIONS_URL + "get_by_persona"
    const val URL_PROFS_GET = ROOT_PROFESSIONS_URL + "get"
}
