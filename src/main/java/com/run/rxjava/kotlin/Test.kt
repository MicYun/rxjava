package com.run.rxjava.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by MicYun on 2018/7/10.
 */
class Test : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        test(1)
    }

    fun <T> test(x: T): List<T> {
        return mutableListOf()
    }

    fun <T> T.toString2(): String {
        return ""
    }
}