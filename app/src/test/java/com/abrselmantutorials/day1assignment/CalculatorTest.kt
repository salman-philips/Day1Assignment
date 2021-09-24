package com.abrselmantutorials.day1assignment

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun testCalculatorMethods() {
        Assert.assertEquals(5, calculator.add(2, 3))
        Assert.assertEquals(-1, calculator.subtract(2, 3))
        Assert.assertEquals(6, calculator.multiply(2, 3))
        Assert.assertEquals(0, calculator.divide(2, 3))
    }
}