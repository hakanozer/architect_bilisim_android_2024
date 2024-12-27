package com.works

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    lateinit var l_txtEmail: EditText
    lateinit var l_txtPassword: EditText
    lateinit var l_btnLogin: TextView
    lateinit var l_btnRegister: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        l_txtEmail = findViewById(R.id.l_txtEmail)
        l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnLogin = findViewById(R.id.l_btnLogin)
        l_btnRegister = findViewById(R.id.l_btnRegister)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }


}