package com.example.blyadskiystudentogramm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Обработка системных окон (insets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Инициализация полей
        email = findViewById(R.id.Email)
        pass = findViewById(R.id.passwordd)

        // Принудительный выход из системы и очистка кэша
        auth.signOut()
        clearAuthCache()
    }

    override fun onStart() {
        super.onStart()
        // Проверка текущего пользователя при старте активности
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Если пользователь найден, выходим из системы
            auth.signOut()
            Toast.makeText(this, "Обнаружена предыдущая сессия. Войдите снова.", Toast.LENGTH_SHORT).show()
        }
    }




    // Очистка кэша аутентификации
    private fun clearAuthCache() {
        auth.signOut()
        auth.currentUser?.delete()?.addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Кэш аутентификации очищен", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Обработка нажатия кнопки входа
    fun Enter(view: View) {
        login()
    }

    // Метод входа
    private fun login() {
        val emailText = email.text.toString().trim()
        val passText = pass.text.toString().trim()

        if (emailText.isEmpty() || passText.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(emailText, passText)
            .addOnSuccessListener {
                // Проверяем, что вход выполнен успешно
                if (auth.currentUser != null) {
                    Toast.makeText(this, "Добро пожаловать", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, main_menu::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Ошибка: пользователь не найден", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Ошибка входа: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Обработка нажатия кнопки регистрации
    fun Reg(view: View) {
        val emailText = email.text.toString().trim()
        val passText = pass.text.toString().trim()

        if (emailText.isEmpty() || passText.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        // Регистрация нового пользователя
        auth.createUserWithEmailAndPassword(emailText, passText)
            .addOnSuccessListener {
                Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                // Переход на главную страницу после регистрации
                startActivity(Intent(this, main_menu::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Ошибка регистрации: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}