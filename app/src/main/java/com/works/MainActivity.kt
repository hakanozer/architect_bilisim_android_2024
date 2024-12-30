package com.works

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.configs.ApiClient
import com.works.controllers.NotesActivity
import com.works.controllers.RegisterActivity
import com.works.models.JWTModel
import com.works.models.LoginJWTModel
import com.works.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {


    lateinit var l_txtEmail: EditText
    lateinit var l_txtPassword: EditText
    lateinit var l_btnLogin: TextView
    lateinit var l_btnRegister: TextView
    lateinit var dummyService: DummyService

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        l_txtEmail = findViewById(R.id.l_txtEmail)
        l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnLogin = findViewById(R.id.l_btnLogin)
        l_btnRegister = findViewById(R.id.l_btnRegister)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        l_btnLogin.setOnClickListener {
            val username = l_txtEmail.text.toString().trim()
            val password = l_txtPassword.text.toString().trim()
            val loginJWTModel = LoginJWTModel(username, password)

            dummyService.login(loginJWTModel).enqueue( object: Callback<JWTModel> {
                override fun onResponse(p0: Call<JWTModel>, p1: Response<JWTModel>) {
                    if (p1.code() == 200) {
                        val jwtModel = p1.body()
                        if (jwtModel != null) {
                            editor.putString("accessToken", jwtModel.accessToken)
                            editor.putString("name", jwtModel.username)
                            editor.apply()
                            val intent = Intent(this@MainActivity, NotesActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else {
                        Toast.makeText(this@MainActivity, "Username or Password fail!", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(p0: Call<JWTModel>, p1: Throwable) {
                    Log.d("JWT onFailure", p1.message.toString())
                }
            })

            //val intent = Intent(this, NotesActivity::class.java)
            //startActivity(intent)
        }

        l_btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            val email = l_txtEmail.text.toString()
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }


}