package com.example.hackathon

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hackathon.database_operation.DatabaseOperation
import com.example.hackathon.database_operation.databaseopenhelper
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.soundinput.SpeechRecognizerManager
import android.app.DatePickerDialog
import android.view.ContextThemeWrapper
import android.widget.DatePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle("")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.component_background)))

//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            val intent = Intent(this, Chat_Activity::class.java)
//            intent.putExtra("TEXT","今日は近所の公園で近所の子供たちと遊びました。")
//            intent.putExtra("HAPPINESS",95)
//            intent.putExtra("YEAR",2023)
//            intent.putExtra("MONTH",5)
//            intent.putExtra("DAY",19)
//            startActivity(intent)
//        }
        val watch = findViewById<Button>(R.id.see_calendar_button)
        watch.setOnClickListener{
            showCalendarPopup()
        }

        val writeDiaryButton = findViewById<Button>(R.id.write_diary_button)
        writeDiaryButton.setOnClickListener{
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }
    }
    private fun showCalendarPopup() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            ContextThemeWrapper(this, R.style.CustomDatePicker),
            DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                navigateToPage2(year, monthOfYear, dayOfMonth)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
    private fun navigateToPage2(year: Int, month: Int, day: Int) {
        val intent = Intent(this, WatchActivity::class.java)
        intent.putExtra("YEAR", year)
        intent.putExtra("MONTH", month)
        intent.putExtra("DAY", day)
        startActivity(intent)
    }
}