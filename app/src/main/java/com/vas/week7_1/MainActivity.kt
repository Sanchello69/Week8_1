package com.vas.week7_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vas.feature_main_screen.presentation.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, MainFragment())
            .commit()
    }
}