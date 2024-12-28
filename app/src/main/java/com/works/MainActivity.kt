package com.works

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.controllers.NotesActivity
import com.works.controllers.RegisterActivity

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

        l_btnLogin.setOnClickListener {
            // toast
            //Toast.makeText(this, "Login Btn Call", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        l_btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            val email = l_txtEmail.text.toString()
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }


}