package com.example.blyadskiystudentogramm

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.ImageView
import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.LinearInterpolator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer // Таймер
    private val rotationDuration = 30000L // 30 секунд (в миллисекундах)
    var Boolean:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Устанавливаем разметку
        setContentView(R.layout.activity_main)

        // Находим TextView
        val textView3 = findViewById<TextView>(R.id.textView3)
        if (textView3 != null) {
            // Устанавливаем градиент для текста
            setTextGradient(textView3)
        } else {
            println("TextView with ID 'textView3' not found")
        }

        // Включаем edge-to-edge режим
        enableEdgeToEdge()

        // Обрабатываем системные окна
        val mainContainer = findViewById<View>(R.id.main)
        if (mainContainer != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainContainer) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
                )
                insets
            }
        } else {
            println("View with ID 'main' not found")
        }

        // Инициализируем ImageView
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageView4 = findViewById<ImageView>(R.id.imageView4)

        // Запускаем таймер и анимации
        val curUset = FirebaseAuth.getInstance().currentUser
        if (curUset != null){
            Boolean = true
        }

        startTimerAndAnimations(imageView2, imageView3, imageView4)
    }

    /**
     * Устанавливает градиент для текста в TextView
     */
    private fun setTextGradient(textView: TextView) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString()) // Ширина текста
        val textSize = textView.textSize // Высота текста

        val gradient = LinearGradient(
            0f, 100f, width, textSize, // Начальная и конечная точки градиента
            intArrayOf(
                0xFF00A3FF.toInt(), // #00A3FF
                0xFF522BFF.toInt(), // #522BFF
                0xFF7000FF.toInt()  // #7000FF
            ),
            null,
            Shader.TileMode.CLAMP
        )

        textView.paint.shader = gradient
    }

    /**
     * Запускает таймер и анимации для ImageView
     */
    private fun startTimerAndAnimations(imageView2: ImageView?, imageView3: ImageView?, imageView4: ImageView?) {
        // Проверяем, что ImageView существуют
        if (imageView2 == null || imageView3 == null || imageView4 == null) {
            println("One or more ImageView elements not found")
            return
        }

        // Создаём таймер на 30 секунд
        timer = object : CountDownTimer(30000, 1000) { // 30 секунд, tick каждую секунду
            override fun onTick(millisUntilFinished: Long) {
                // Лог для отображения оставшегося времени (необязательно)
                println("Time left: ${millisUntilFinished / 1000} seconds")
            }

            override fun onFinish() {
                // Останавливаем все анимации
                imageView2.clearAnimation()
                imageView3.clearAnimation()
                imageView4.clearAnimation()

                // Переход на activity_register после завершения таймера
                println("Timer finished! Starting RegisterActivity...")
                if (Boolean){
                    startActivity(Intent(this@MainActivity, main_menu::class.java))
                    finish()
                }
                else{
                    startActivity(Intent(this@MainActivity, register::class.java))
                    finish()
                }
            }
        }.start()

        // Анимация вращения для imageView3 (по часовой стрелке)
        startRotationAnimation(imageView3, true, rotationDuration /30)

        // Анимация вращения для imageView4 (против часовой стрелки)
        startRotationAnimation(imageView2, false, rotationDuration / 30) // Увеличиваем скорость вращения в два раза

        // Анимация вращения для imageView5 (по часовой стрелке)
        startRotationAnimation(imageView4, true, rotationDuration / 30) // Уменьшаем скорость вращения в два раза
    }

    /**
     * Запускает анимацию вращения для ImageView
     */
    private fun startRotationAnimation(imageView: ImageView, clockwise: Boolean, duration: Long) {
        val fromDegrees = if (clockwise) 0f else 360f
        val toDegrees = if (clockwise) 360f else 0f

        val rotateAnimation = RotateAnimation(
            fromDegrees, toDegrees, // Начальный и конечный угол
            Animation.RELATIVE_TO_SELF, 0.5f, // Центр вращения по X
            Animation.RELATIVE_TO_SELF, 0.5f // Центр вращения по Y
        ).apply {
            this.duration = duration // Длительность анимации
            repeatCount = Animation.INFINITE // Повторять бесконечно
            interpolator = LinearInterpolator() // Используйте линейный интерполятор
        }

        imageView.startAnimation(rotateAnimation)
    }
}