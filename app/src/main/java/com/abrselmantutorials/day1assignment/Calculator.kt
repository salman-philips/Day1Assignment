package com.abrselmantutorials.day1assignment

class Calculator {


    fun add(a: Int, b: Int): Int = a + b

    fun subtract(a: Int, b: Int): Int = a - b

    fun multiply(a: Int, b: Int): Int = a * b

    fun divide(a:Int,b:Int):Int{
        return if(b!=0){
            a/b
        } else{
            -1
        }
    }
}