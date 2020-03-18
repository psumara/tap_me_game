package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var score = 0
    private var gameStarted = false
    private var initialCountDown: Long = 60000
    private var countDownInterval: Long = 1000
    private var timeleft = 60

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var gameScoreTextView: TextView
    private lateinit var timeLeftTextView: TextView
    private lateinit var tapMeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextView = findViewById(R.id.game_score_text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapMeButton = findViewById(R.id.tap_me_button)

        tapMeButton.setOnClickListener{incrementScore()}

        resetGame()
    }

    private fun incrementScore(){
        if(!gameStarted)
            startGame()
        score++

        val newScore = getString(R.string .your_score, score)
        gameScoreTextView.text = newScore

    }
    private fun resetGame(){
        score = 0
        val initialScore: String = getString(R.string.your_score, score)
        gameScoreTextView.text = initialScore

        val initialTimeLeft: String = getString(R.string.time_left, timeleft)
        timeLeftTextView.text = initialTimeLeft

        gameStarted = false

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeleft = millisUntilFinished.toInt()/1000
                val timeLeftString: String = getString(R.string.time_left, timeleft)
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish() {
                endGame()

            }
        }


    }
    private fun startGame(){
        countDownTimer.start()
        gameStarted = true

    }
    private fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()
        resetGame()

    }
}
