package com.muchine.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) addFragment()
    }

    private fun addFragment() {
        val fragment = ScrollableTabContainer()
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment)
            .commit()
    }
}
