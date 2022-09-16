package com.example.drzavnipraznici

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

  class CallAPILoginAsyncTask() : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg input: String?): String {

        var result: String
        var connection: HttpURLConnection? = null
        val apiUrl = input[0]

        try {
            val url = URL(apiUrl)

            connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.doOutput = false

            val httpResult: Int = connection.responseCode

            if (httpResult == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))

                val stringBuilder = StringBuilder()
                var line: String?

                try {

                    while (reader.readLine().also { line = it } != null) {
                        stringBuilder.append((line + "\n"))
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    try {
                        inputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                result = stringBuilder.toString()
            } else {
                result = connection.responseMessage + "ERROR"
            }

        } catch (e: SocketTimeoutException) {
            result = "Connection Time Out"
        } catch (e: Exception) {
            result = "Error : " + e.message
        } finally {
            connection?.disconnect()
        }

        //return result from api
        return result

    }


}
