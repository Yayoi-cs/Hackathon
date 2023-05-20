package com.example.hackathon.OpenAI_ApiOperater

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ApiOperater {

    suspend fun chatWithGPT(userText: String): String {
        val apiKey = "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"
        val url = "https://api.openai.com/v1/chat/completions"
        val mediaType = "application/json".toMediaType()
        val requestBody = JSONObject(
            mapOf(
                "model" to "gpt-3.5-turbo",
                "messages" to listOf(
                    mapOf("role" to "user", "content" to userText)
                )
            )
        ).toString()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody(mediaType))
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string()
                val responseObj = JSONObject(responseBody)
                Log.d("リスポンス",responseObj.toString())
                val choicesArray = responseObj.getJSONArray("choices")
                if (choicesArray.length() > 0) {
                    val firstChoiceObj = choicesArray.getJSONObject(0)
                    val reply = firstChoiceObj.getJSONObject("message").getString("content").trim()
                    reply
                } else {
                    ""
                }
            }
        }
    }
}