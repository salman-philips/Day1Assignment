package com.abrselmantutorials.day1assignment

import android.support.test.rule.ActivityTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var mainActivityRule = ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun testMainActivityFields(){
        Assert.assertNotNull(mainActivityRule)
    }
}