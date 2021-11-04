package com.example.tictactoekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var gameActive = true

    // 0 - X
    // 1 - O
    var activePlayer = 0
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    // o'yinda belgilanishlar
    //    0 - X o'yinchi
    //    1 - O o'yinchi
    //    2 - Null
    // yutish yo'llari
    var winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playerTap(view: View) {
        val img = view as ImageView

        // index ni o'qib olish uchun.
        val tappedImage = img.tag.toString().toInt()

        // o'yinni yangittan boshlash
        if (!gameActive) {
            gameReset(view)
        }

        // agar bosilgan rasm bo'sh bo'lsa
        if (gameState[tappedImage] == 2) {
            // har bir bosishdan keyin hisoblashni oshiring
            counter++

            // oxirgi rasm bosilganini tekshirish
            if (counter == 9) {
                // o'yinni qayta boshlash
                gameActive = false
            }

            // rasmlar massiviga qaysi rasm bosilsa osha rasmni id sini yozish
            gameState[tappedImage] = activePlayer

            // rasmga effekt berish
            img.translationY = -1000f

            // faol oyinchini ozgartirish
            if (activePlayer == 0) {
                // image ga x rasmini qoyish
                img.setImageResource(R.drawable.x)
                activePlayer = 1
                val status = findViewById<TextView>(R.id.status)

                // O -  o'yinchiga bosishni eslatuvchi text
                status.text = "O o'yinchi - o'ynash uchun bosing"
            } else {
                // image ga 0 rasmini qoyish
                img.setImageResource(R.drawable.o)
                activePlayer = 0
                val status = findViewById<TextView>(R.id.status)

                // O -  o'yinchiga bosishni eslatuvchi text
                status.text = "X o'yinchi - o'ynash uchun bosing"
            }
            img.animate().translationYBy(2000f).duration = 500
        }

        var flag = 0
        // o'yinchi g'alaba qozonganligini tekshirish
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                flag = 1

                // o'yinchi g'alaba qozonga text ga osha oyinchini chiqarish
                var winnerStr: String

                // o'yinni qayta boshlash
                gameActive = false
                winnerStr = if (gameState[winPosition[0]] == 0) {
                    "X yutdi"
                } else {
                    "O yutdi"
                }
                // yutgan oyinchini text ga chiqarish
                val status = findViewById<TextView>(R.id.status)
                status.text = winnerStr
            }
        }
        // o'yin durang bo'lganda
        // o'yin durang bo'lganda
        if (counter == 9 && flag == 0) {
            val status = findViewById<TextView>(R.id.status)
            status.text = "Do'stlik g'alaba qozondi"
        }
    }

    // reset the game
    fun gameReset(view: View?) {
        gameActive = true
        activePlayer = 0

        // o'yin yangidan boshlanganda hammasiga 2 ni joylab chiqadi
        for (i in gameState.indices) {
            gameState[i] = 2
        }

        // yangi oyin boshlanganda hamma rasmlarni boshatadi
        (findViewById<View>(R.id.imageView0) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView1) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView2) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView3) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView4) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView5) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView6) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView7) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView8) as ImageView).setImageResource(0)
        val status = findViewById<TextView>(R.id.status)
        status.text = "X o'yinchi - o'ynash uchun bosing"
    }

}