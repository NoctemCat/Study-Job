package com.example.study_job.data

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

class RequestHandler {
    fun sendPostRequest(requestURL: String?, postDataParams: HashMap<String, String>): String {
        val url: URL
        var sb = StringBuilder()
        try {
            url = URL(requestURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "POST"
            conn.doInput = true
            conn.doOutput = true
            val os = conn.outputStream
            val writer = BufferedWriter(
                OutputStreamWriter(os, "UTF-8")
            )
            writer.write(getPostDataString(postDataParams))
            writer.flush()
            writer.close()
            os.close()
            val responseCode = conn.responseCode
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                val br = BufferedReader(InputStreamReader(conn.inputStream))
                sb = StringBuilder()
                var response: String?
                while (br.readLine().also { response = it } != null) {
                    sb.append(response)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sb.toString()
    }

    fun sendGetRequest(requestURL: String?): String {
        val url: URL
        var sb = StringBuilder()
        try {
            url = URL(requestURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.doInput = true

            val responseCode = conn.responseCode
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                val br = BufferedReader(InputStreamReader(conn.inputStream))
                sb = StringBuilder()
                var response: String?
                while (br.readLine().also { response = it } != null) {
                    sb.append(response)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sb.toString()
    }

    //this method is converting keyvalue pairs data into a query string as needed to send to the server
    @Throws(UnsupportedEncodingException::class)
    private fun getPostDataString(params: HashMap<String, String>): String {
        val result = StringBuilder()
        var first = true
        for ((key, value) in params) {
            if (first) first = false else result.append("&")
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value, "UTF-8"))
        }
        return result.toString()
    }
}