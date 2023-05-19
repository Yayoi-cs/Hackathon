package com.example.hackathon.database_operation
import  android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class databaseopenhelper_GPT(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "hackathon_database.db"
        const val TABLE_NAME = "gpt_table"

        // カラム名
        const val COLUMN_PRIMARY_KEY = "id"
        const val COLUMN_Year = "Year"
        const val COLUMN_Month = "Month"
        const val COLUMN_Day = "Day"
        const val COLUMN_USER_TEXT = "user_text"
        const val COLUMN_GPT_TEXT = "gpt_text"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_PRIMARY_KEY INTEGER PRIMARY KEY, " +
                "$COLUMN_Year INTEGER, " +
                "$COLUMN_Month INTEGER, " +
                "$COLUMN_Day INTEGER, " +
                "$COLUMN_USER_TEXT TEXT, " +
                "$COLUMN_GPT_TEXT TEXT)"

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}