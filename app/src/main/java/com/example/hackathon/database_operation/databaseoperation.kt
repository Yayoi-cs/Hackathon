package com.example.hackathon.database_operation
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.hackathon.database_operation.databaseopenhelper

class DatabaseOperation(context: Context) {
    //private val Databaseopenhelper: databaseopenhelper = databaseopenhelper(context)
    //private val database: SQLiteDatabase = Databaseopenhelper.writableDatabase

    private val databaseHelper: databaseopenhelper = databaseopenhelper(context)

    fun insertData(year: Int, month: Int, day: Int, date: String, mainText: String, image: String,
                   happiness: Int, sessionId: String): Long {
        val values = ContentValues()
        values.put(databaseopenhelper.COLUMN_YEAR, year)
        values.put(databaseopenhelper.COLUMN_MONTH, month)
        values.put(databaseopenhelper.COLUMN_DAY, day)
        values.put(databaseopenhelper.COLUMN_DATE, date)
        values.put(databaseopenhelper.COLUMN_MAIN_TEXT, mainText)
        values.put(databaseopenhelper.COLUMN_IMAGE, image)
        values.put(databaseopenhelper.COLUMN_HAPPINESS, happiness)

        val db = databaseHelper.writableDatabase
        return db.insert(databaseopenhelper.TABLE_NAME, null, values)
    }

    fun getData(): Cursor {
        val db = databaseHelper.readableDatabase
        return db.query(
            databaseopenhelper.TABLE_NAME,
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
        val selection = "${databaseopenhelper.COLUMN_PRIMARY_KEY} = ?"
        val selectionArgs = arrayOf(primaryKey.toString())

        return db.query(
            databaseopenhelper.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
    }
    fun getDataByDate(year: Int, month: Int, day: Int): DataModel_watch? {
        val db = databaseHelper.readableDatabase
        val selection = "${databaseopenhelper.COLUMN_YEAR} = ? AND " +
                "${databaseopenhelper.COLUMN_MONTH} = ? AND " +
                "${databaseopenhelper.COLUMN_DAY} = ?"
        val selectionArgs = arrayOf(year.toString(), month.toString(), day.toString())

        val cursor = db.query(
            databaseopenhelper.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val data: DataModel_watch?

        if (cursor.moveToFirst()) {
            val dateIndex = cursor.getColumnIndex(databaseopenhelper.COLUMN_DATE)
            val mainTextIndex = cursor.getColumnIndex(databaseopenhelper.COLUMN_MAIN_TEXT)
            val imageIndex = cursor.getColumnIndex(databaseopenhelper.COLUMN_IMAGE)
            val happinessIndex = cursor.getColumnIndex(databaseopenhelper.COLUMN_HAPPINESS)

            val date = if (dateIndex != -1) cursor.getString(dateIndex) else ""
            val mainText = if (mainTextIndex != -1) cursor.getString(mainTextIndex) else ""
            val image = if (imageIndex != -1) cursor.getString(imageIndex) else ""
            val happiness = if (happinessIndex != -1) cursor.getInt(happinessIndex) else 0

            data = DataModel_watch(date, mainText, image, happiness)
        } else {
            data = null
        }

        cursor.close()
        return data
    }
    data class DataModel_watch(
        val date: String,
        val mainText: String,
        val image: String,
        val happiness: Int
    )
}