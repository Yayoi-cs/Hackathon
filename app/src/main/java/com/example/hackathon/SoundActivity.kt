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

class SoundActivity : AppCompatActivity() {

    private lateinit var speechRecognizerManager: SpeechRecognizerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        val granted = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (granted != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), PERMISSIONS_RECORD_AUDIO)
        }

        var recognize_text_view = findViewById<TextView>(R.id.text_test)
        var recognize_start_button = findViewById<Button>(R.id.recognize_start_button)
        var main_text_view = findViewById<TextView>(R.id.text_main)
        var display_button = findViewById<Button>(R.id.disp_button)

        speechRecognizerManager = SpeechRecognizerManager(recognize_start_button, recognize_text_view)

        val nextButton = findViewById<Button>(R.id.btnBack)
        nextButton.setOnClickListener{
            finish()
        }

        display_button.setOnClickListener {
            main_text_view.text = speechRecognizerManager.getRecognizedText()
        }

    }

    companion object {
        private const val PERMISSIONS_RECORD_AUDIO = 1000
    }
}