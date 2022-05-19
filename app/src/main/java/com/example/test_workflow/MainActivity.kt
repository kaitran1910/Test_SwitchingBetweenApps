package com.example.test_workflow

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var view = R.layout.activity_main
    var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)
        val parent = findViewById<LinearLayout>(R.id.parent)
        button = findViewById(R.id.button)
        button!!.setOnClickListener(View.OnClickListener {
            val launchIntent =
                packageManager.getLaunchIntentForPackage("com.example.orangeadapp")
            if (launchIntent != null) {
                startActivity(launchIntent)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "There is no package available in android",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}