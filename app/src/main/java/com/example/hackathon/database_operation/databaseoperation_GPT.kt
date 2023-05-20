package com.example.hackathon.database_operation
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class databaseoperation_GPT(context: Context) {

    private val databaseHelper: databaseopenhelper_GPT = databaseopenhelper_GPT(context)

    fun insertData_GPT(
        year_Argument: Int,month_Argument: Int,day_Argument: Int,
        userText: String, gptText: String): Long {
        val values = ContentValues()
        values.put(databaseopenhelper_GPT.COLUMN_Year,year_Argument)
        values.put(databaseopenhelper_GPT.COLUMN_Month,month_Argument)
        values.put(databaseopenhelper_GPT.COLUMN_Day,day_Argument)
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
    fun getDataByDate(year: Int, month: Int, day: Int): DatabaseModel_gpt? {
        val db = databaseHelper.readableDatabase
        val selection = "${databaseopenhelper_GPT.COLUMN_Year} = ? AND " +
                "${databaseopenhelper_GPT.COLUMN_Month} = ? AND " +
                "${databaseopenhelper_GPT.COLUMN_Day} = ?"
        val selectionArgs = arrayOf(year.toString(), month.toString(), day.toString())

        val cursor = db.query(
            databaseopenhelper_GPT.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val data: DatabaseModel_gpt?

        if (cursor.moveToFirst()) {
            val userTextIndex = cursor.getColumnIndex(databaseopenhelper_GPT.COLUMN_USER_TEXT)
            val gptTextIndex = cursor.getColumnIndex(databaseopenhelper_GPT.COLUMN_GPT_TEXT)

            val userText = if (userTextIndex != -1) cursor.getString(userTextIndex) else ""
            val gptText = if (gptTextIndex != -1) cursor.getString(gptTextIndex) else ""

            data = DatabaseModel_gpt(userText, gptText)
        } else {
            data = null
        }

        cursor.close()
        return data
    }
    data class DatabaseModel_gpt(
        val userText: String,
        val gptText: String
    )
}