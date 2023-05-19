package com.example.hackathon

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.soundinput.SpeechRecognizerManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WriteDiaryActivity : AppCompatActivity() {

    private lateinit var speechRecognizerManager: SpeechRecognizerManager
    private val PERMISSIONS_RECORD_AUDIO = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary)

        val granted = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (granted != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), PERMISSIONS_RECORD_AUDIO)
        }

        supportActionBar?.setTitle("日記を書く")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.component_background)))


        // 日付関係
        val dateView = findViewById<TextView>(R.id.date_text_view)
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy/MM/dd(EEE)", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        val year = calendar.get(Calendar.YEAR).toInt()
        val month = calendar.get(Calendar.MONTH).toInt()
        val day = calendar.get(Calendar.DAY_OF_MONTH).toInt()
        val dayOfWeek = SimpleDateFormat("EEE", Locale.ENGLISH).format(currentDate)

        dateView.text = formattedDate

        // 本文
        var mainText: String = ""
        val editText = findViewById<EditText>(R.id.edit_text_view)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                mainText = p0.toString()
                Log.d("WriteDiaryActivity", ":a:" + mainText)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        val happinessView = findViewById<EditText>(R.id.happiness_view)

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val completeButton = findViewById<Button>(R.id.complete_button)
        completeButton.setOnClickListener {
            var happinessCount = happinessView.text.toString().toInt()

//            var databaseOperation = DatabaseOperation(this)
//            databaseOperation.insertData(year,month,day,dayOfWeek,mainText,"example/example",happinessCount,"a")

            val intent = Intent(this, WatchActivity::class.java)
            intent.putExtra("TEXT", mainText)
            intent.putExtra("HAPPINESS", happinessCount)
            intent.putExtra("DATE", formattedDate)
            intent.putExtra("YEAR", year)
            intent.putExtra("MONTH", month)
            intent.putExtra("DAY", day)
            startActivity(intent)
        }

        val micButton = findViewById<FloatingActionButton>(R.id.mic_button)

        speechRecognizerManager = SpeechRecognizerManager(micButton, editText)


    }


}