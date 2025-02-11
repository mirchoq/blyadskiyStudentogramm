package com.example.blyadskiystudentogramm

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var pass: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        email = findViewById(R.id.Email)
        pass = findViewById(R.id.passwordd)
    }

    fun Enter(view: View) {
        login()
    }
    fun login(){
        if (email.text.isNotEmpty() and pass.text.isNotEmpty()){
            val email = email.text.toString()
            val pass = pass.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "Добро пожаловать",Toast.LENGTH_LONG).show()
                    val per = Intent(this@register, main_menu::class.java)
                    startActivity(per)
                    finish()
                }
                .addOnFailureListener { Toast.makeText(baseContext,"ой ой, не нашли польвователя :(", Toast.LENGTH_LONG).show() }

        }
        else {
            Toast.makeText(baseContext,"jj",Toast.LENGTH_LONG).show()

        }
    }

    fun Reg(view: View) {
        if (email.text.isNotEmpty() and pass.text.isNotEmpty()){
            val email = email.text.toString()
            val pass = pass.text.toString()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "Регистрация успешная",Toast.LENGTH_LONG).show()
                    login()
                }
                .addOnFailureListener { Toast.makeText(baseContext,"404", Toast.LENGTH_LONG).show() }

        }
        else {

        Toast.makeText(baseContext,"jj",Toast.LENGTH_LONG).show()
        }
    }
}
