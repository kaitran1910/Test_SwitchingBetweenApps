package com.example.orangeadapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mText: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mText = findViewById(R.id.text)
        mText.text = "Touch anytime you want to come back to the other app"
    }

    // Come back to the other app when the user touches the screen
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val launchIntent = packageManager.getLaunchIntentForPackage("com.example.test_workflow")

        if (event?.action == MotionEvent.ACTION_DOWN) {
            startActivity(launchIntent)
        }

        return super.onTouchEvent(event)
    }
}