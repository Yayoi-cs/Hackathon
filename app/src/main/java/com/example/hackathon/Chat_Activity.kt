package com.example.hackathon

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.example.hackathon.database_operation.DatabaseOperation
import com.example.hackathon.database_operation.databaseopenhelper
import com.example.hackathon.database_operation.databaseopenhelper_GPT
import com.example.hackathon.database_operation.databaseoperation_GPT
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.Dispatchers
import android.util.Log
import android.widget.TextView

class Chat_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat2)
        val recepter_text:String = intent.getStringExtra("TEXT").toString()
        val recepter_satisfaction = intent.getIntExtra("HAPPINESS",0)
        val recepter_date = intent.getIntExtra("DATE",0)
        val recepter_year = intent.getIntExtra("YEAR",0)
        val recepter_Month = intent.getIntExtra("MONTH",0)
        val recepter_Day = intent.getIntExtra("DAY",0)
        supportActionBar?.title = "Chat"
        supportActionBar?.setDisplayShowTitleEnabled(true)
        //var databaseOperation = DatabaseOperation(this)
        //databaseOperation.insertData(2023,5,17,"Mon","メインサンプルテキスト","example/example",95,"a")
        //var databaseoperationGpt = databaseoperation_GPT(this)
        //databaseoperationGpt.insertData_GPT(2023,3,5,"sampleUserText","sampleGptText")

        var databaseOperation_get_gptdata = databaseoperation_GPT(this)
        val getdata = databaseOperation_get_gptdata.getDataByDate(recepter_year,recepter_Month,recepter_Day)
        Log.d("出力",getdata.toString())
        //val maintext = getdata?.mainText ?:""
        if(getdata == null){
            createnewchat(recepter_text,recepter_satisfaction,recepter_year,recepter_Month,recepter_Day)
        }
        else {
            printoldchat(getdata.userText,getdata.gptText)
        }
        val gohomebutton = findViewById<Button>(R.id.Button_GoMainActivity)
        gohomebutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun createnewchat(recepter_text: String,recepter_satisfaction :Int,Year_r:Int,Month_r:Int,Day_r:Int){
        val userText:String = "こんにちは\n以下は私の今日の日記です\n"+
                recepter_text+"私の今日の幸福度を自己評価で表すと"+
                recepter_satisfaction.toString()+"でした。\n100文字以内でほめてください"
        val textview_user = findViewById<TextView>(R.id.textView_user)
        textview_user.text = recepter_text
        val textview_gpt = findViewById<TextView>(R.id.textView_gpt)
        var replay :String = ""
        GlobalScope.launch {
            replay = chatWithGPT2(userText)
            //Log.d("出力",replay)
            withContext(Dispatchers.Main){
                textview_gpt.text=replay
                var databaseoperationGpt = databaseoperation_GPT(this@Chat_Activity)
                databaseoperationGpt.insertData_GPT(Year_r,Month_r,Day_r,recepter_text,replay)
            }
        }

    }

    fun printoldchat(userText: String,gptText: String){
        val textview_user = findViewById<TextView>(R.id.textView_user)
        textview_user.text=userText
        val textview_gpt = findViewById<TextView>(R.id.textView_gpt)
        textview_gpt.text=gptText
    }
    suspend fun chatWithGPT2(userText: String): String {
        val apiKey = ""
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