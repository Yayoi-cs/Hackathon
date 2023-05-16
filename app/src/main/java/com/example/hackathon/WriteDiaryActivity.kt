package com.example.hackathon

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.CircularProgressIndicator

class WriteDiaryActivity : AppCompatActivity() {

    private var texts: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary)

        val satisfactionCount = findViewById<EditText>(R.id.satistaftion_count)

        val editText = findViewById<EditText>(R.id.edit_text_view)
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                texts = p0.toString()
                Log.d("MainActivity", ":a:" + texts)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener{
            finish()
        }

        val completeButton = findViewById<Button>(R.id.complete_button)
        completeButton.setOnClickListener {
//            val intent = Intent(this, WatchActivity::class.java)
//            startActivity(intent)
            Log.d("Write", ":a:" + editText.text.toString())
            Log.d("Write", ":b:" + satisfactionCount.text.toString())
        }

//        val progressIndicator = findViewById<CircularProgressIndicator>(R.id.progressIndicator)
//        val maxProgress = 100 // プログレスバーの最大値
//        val targetProgress = (maxProgress * 0.75).toInt() // 目標のプログレス値（40%）
//        val indicatorSize = 200 // プログレスインジケータの大きさ
//        val strokeWidth = 10 // プログレスインジケータの幅の太さ
//        progressIndicator.max = maxProgress // プログレスバーの最大値を設定
//        progressIndicator.progress = targetProgress // 目標のプログレス値を設定
//        progressIndicator.setIndicatorColor(Color.RED)
////        progressIndicator.isIndeterminate = true
//        progressIndicator.indicatorSize = 360
//        progressIndicator.scrollBarSize = 300


    }

}