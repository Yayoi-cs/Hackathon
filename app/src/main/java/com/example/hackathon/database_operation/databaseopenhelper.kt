package com.example.hackathon.database_operation
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class databaseopenhelper(context: Context) :
        SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "my_table"
        const val COLUMN_PRIMARY_KEY = "Primary_Key"
        const val COLUMN_YEAR = "Year"
        const val COLUMN_MONTH = "Month"
        const val COLUMN_DAY = "Day"
        const val COLUMN_DATE = "Date"
        const val COLUMN_MAIN_TEXT = "MainText"
        const val COLUMN_IMAGE = "Image"
        const val COLUMN_HAPPINESS = "Happiness"
        const val COLUMN_SESSION_ID = "SessionID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_PRIMARY_KEY INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_YEAR INTEGER, " +
                "$COLUMN_MONTH INTEGER, " +
                "$COLUMN_DAY INTEGER, " +
                "$COLUMN_DATE TEXT, " +
                "$COLUMN_MAIN_TEXT TEXT, " +
                "$COLUMN_IMAGE TEXT, " +
                "$COLUMN_HAPPINESS INTEGER, " +
                "$COLUMN_SESSION_ID TEXT)"

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades, if any
    }
}