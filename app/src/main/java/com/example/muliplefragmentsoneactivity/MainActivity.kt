package com.example.muliplefragmentsoneactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.muliplefragmentsoneactivity.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }


    }
