package com.example.hackathon

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hackathon.database_operation.DatabaseOperation
import com.example.hackathon.database_operation.databaseopenhelper
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.soundinput.SpeechRecognizerManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Chat_Activity::class.java)
            intent.putExtra("TEXT","今日は近所の公園で近所の子供たちと遊びました。")
            intent.putExtra("HAPPINESS",95)
            intent.putExtra("YEAR",2023)
            intent.putExtra("MONTH",5)
            intent.putExtra("DAY",19)
            startActivity(intent)
        }


        val writeDiaryButton = findViewById<Button>(R.id.write_diary_button)
        writeDiaryButton.setOnClickListener{
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }
    }
}