package com.example.hackathon

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.example.hackathon.database_operation.DatabaseOperation
import com.example.hackathon.database_operation.databaseopenhelper

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.title = "Chat"
        supportActionBar?.setDisplayShowTitleEnabled(true)
        var databaseOperation = DatabaseOperation(this)
        databaseOperation.insertData(2023,5,17,"Mon","メインサンプルテキスト","example/example",95,"a")

    }
}