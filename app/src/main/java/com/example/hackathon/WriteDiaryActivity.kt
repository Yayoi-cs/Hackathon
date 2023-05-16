package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WriteDiaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary)

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener{
//            val intent = Intent(this, SoundActivity::class.java)
//            startActivity(intent)
            finish()
        }


    }
}