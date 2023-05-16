package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class WatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)

        val text = intent.getStringExtra("TEXT")
        val satisfaction = intent.getStringExtra("SATISFACTION")

        val editText = findViewById<TextView>(R.id.text_view)
        editText.text = text
        val satisfactionCount = findViewById<TextView>(R.id.satisfaction_count)
        satisfactionCount.text = satisfaction


        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener{
            finish()
        }
    }
}