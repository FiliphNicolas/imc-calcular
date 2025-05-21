package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IMCHistoryDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    
    companion object {
        private const val DATABASE_NAME = "imc_history.db"
        private const val DATABASE_VERSION = 1
        
        const val TABLE_HISTORY = "imc_history"
        const val COLUMN_ID = "_id"
        const val COLUMN_WEIGHT = "weight"
        const val COLUMN_HEIGHT = "height"
        const val COLUMN_IMC = "imc"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_HISTORY (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_WEIGHT REAL NOT NULL, " +
                "$COLUMN_HEIGHT REAL NOT NULL, " +
                "$COLUMN_IMC REAL NOT NULL, " +
                "$COLUMN_CATEGORY TEXT NOT NULL, " +
                "$COLUMN_DATE TEXT NOT NULL)"
        
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    fun addHistory(weight: Float, height: Float, imc: Float, category: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_WEIGHT, weight)
            put(COLUMN_HEIGHT, height)
            put(COLUMN_IMC, imc)
            put(COLUMN_CATEGORY, category)
            put(COLUMN_DATE, SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()
            ))
        }

        db.insert(TABLE_HISTORY, null, values)
    }

    fun getAllHistory(): Cursor {
        return readableDatabase.query(
            TABLE_HISTORY,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_DATE DESC"
        )
    }

    fun clearHistory() {
        writableDatabase.delete(TABLE_HISTORY, null, null)
    }
}
