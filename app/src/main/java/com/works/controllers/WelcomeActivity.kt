package com.works.controllers

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.MainActivity
import com.works.R
import java.util.Timer
import java.util.TimerTask

class WelcomeActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val timer = Timer()
        timer.schedule(task, 2000)

    }

    private val task = object : TimerTask() {
        override fun run() {
            val token = sharedPreferences.getString("accessToken", "")
            if ( token != null && token != "" ) {
                // token service check
                val i = Intent(this@WelcomeActivity, NotesActivity::class.java)
                startActivity(i)
                finish()
            }else {
                val i = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

}