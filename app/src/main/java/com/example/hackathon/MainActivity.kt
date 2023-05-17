package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.example.hackathon.database_operation.DatabaseOperation
import com.example.hackathon.database_operation.databaseopenhelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        var databaseOperation = DatabaseOperation(this)
        databaseOperation.insertData(2023,5,17,"Mon","メインサンプルテキスト","example/example",95,"a")
        /*
        val cursor = databaseOperation.getDataByPrimarykey(1)
        if(cursor != null && cursor.moveToNext()){
            val mainText = cursor.getString(cursor.getColumnIndex(databaseopenhelper.COLUMN_MAIN_TEXT))
            val button = findViewById<Button>(R.id.Button_sample)
            if(mainText != null){
                button.text = mainText.toString()
            }
            cursor.close()
        }
        */
        button.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

    }
}