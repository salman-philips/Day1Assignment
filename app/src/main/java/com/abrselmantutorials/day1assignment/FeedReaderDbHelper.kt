package com.abrselmantutorials.day1assignment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class FeedReaderDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
//        db.execSQL(SQL_DELETE_ENTRIES)
//        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${FeedReaderContract.FeedEntry.COLUMN_F_NAME} TEXT," +
                    "${FeedReaderContract.FeedEntry.COLUMN_L_NAME} TEXT)"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"
    }

    fun getValues(): Data {
        val cursor = this.readableDatabase.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToLast()
        return Data(
            cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_F_NAME)),
            cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_L_NAME))
        )
    }

    fun getCursorValues(): ArrayList<Data> {
        val dataList = ArrayList<Data>()
        val cursor = this.readableDatabase.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            do {
                dataList.add(
                    Data(
                        cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_F_NAME)),
                        cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_L_NAME))
                    )
                )
            } while (cursor.moveToNext())
        }
        return dataList
    }

    fun putValues(data: Data) {
        // Gets the data repository in write mode
        val db = this.writableDatabase

// Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_F_NAME, data.fName)
            put(FeedReaderContract.FeedEntry.COLUMN_L_NAME, data.lName)
        }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
    }
}