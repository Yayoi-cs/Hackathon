package com.example.hackathon.database_operation
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class databaseoperation_GPT(context: Context) {

    private val databaseHelper: databaseopenhelper_GPT = databaseopenhelper_GPT(context)

    fun insertData_GPT(sessionId: String, userText: String, gptText: String): Long {
        val values = ContentValues()
        values.put(databaseopenhelper_GPT.COLUMN_SESSION_ID, sessionId)
        values.put(databaseopenhelper_GPT.COLUMN_USER_TEXT, userText)
        values.put(databaseopenhelper_GPT.COLUMN_GPT_TEXT, gptText)

        val db = databaseHelper.writableDatabase
        return db.insert(databaseopenhelper_GPT.TABLE_NAME, null, values)
    }

    fun getData(): Cursor {
        val db = databaseHelper.readableDatabase
        return db.query(
            databaseopenhelper_GPT.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun getDataByPrimarykey(primaryKey: Long): Cursor {
        val db = databaseHelper.readableDatabase
        val selection = "${databaseopenhelper_GPT.COLUMN_PRIMARY_KEY} = ?"
        val selectionArgs = arrayOf(primaryKey.toString())

        return db.query(
            databaseopenhelper_GPT.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
    }
}