package com.example.hackathon

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class WatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)



        // テスト用にWriteDiaryActivityから取得しているが、実際は日付でDBから取得
        val text = intent.getStringExtra("TEXT")
        val satisfaction = intent.getStringExtra("SATISFACTION")
        val date = intent.getStringExtra("DATE")
        val year = intent.getStringExtra("YEAR")
        val month = intent.getStringExtra("MONTH")
        val day = intent.getStringExtra("DAY")

        supportActionBar?.setTitle("日記 ${date}")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.component_background)))

        val mainTextView = findViewById<TextView>(R.id.main_text_view)
        mainTextView.text = text

        val editText = findViewById<TextView>(R.id.text_view)
        editText.text = text
        val satisfactionCount = findViewById<TextView>(R.id.satisfaction_count)
        satisfactionCount.text = satisfaction


        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener{
            finish()
        }

        val chatButton = findViewById<Button>(R.id.chat_button)
        chatButton.setOnClickListener {
//            val intent = Intent(this, ChatActivity::class.java)
//            startActivity(intent)
        }
    }
}