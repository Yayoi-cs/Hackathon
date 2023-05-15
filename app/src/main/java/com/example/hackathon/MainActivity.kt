package com.example.hackathon

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.soundinput.SpeechRecognizerManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton = findViewById<Button>(R.id.sound_button)
        nextButton.setOnClickListener{
            val intent = Intent(this, SoundActivity::class.java)
            startActivity(intent)
        }
    }

}