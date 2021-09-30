package com.abrselmantutorials.day1assignment

import android.provider.BaseColumns

object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "NAME_ENTRY"
        const val COLUMN_F_NAME = "FIRST_NAME"
        const val COLUMN_L_NAME = "LAST_NAME"
    }
}