package com.example.test_workflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    // Declaring handler, runnable and time in milli seconds
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private var mTime: Long = 10000

    private lateinit var mCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launchIntent =
            packageManager.getLaunchIntentForPackage("com.example.orangeadapp")

        mCounter = findViewById(R.id.counter)

        object : CountDownTimer(mTime, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                mCounter.text =f.format(min) + ":" + f.format(sec)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                mCounter.text = "00:00"
            }

        }.start()

        // Initializing the handler and the runnable
        mHandler = Handler(Looper.getMainLooper())
        mRunnable = Runnable {
            startActivity(launchIntent)
            Toast.makeText(
                applicationContext,
                "User inactive for ${mTime / 1000} secs!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Start the handler
        startHandler()
    }

    // When the screen is touched or motion is detected
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        // Removes the handler callbacks (if any)
        stopHandler()

        // Runs the handler (for the specified time)
        // If any touch or motion is detected before
        // the specified time, this override function is again called
        startHandler()

        return super.onTouchEvent(event)
    }

    // start handler function
    private fun startHandler() {
        mHandler.postDelayed(mRunnable, mTime)
    }

    // stop handler function
    private fun stopHandler() {
        mHandler.removeCallbacks(mRunnable)
    }

}