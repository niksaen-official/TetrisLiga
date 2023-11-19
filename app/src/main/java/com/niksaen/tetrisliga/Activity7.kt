package com.niksaen.tetrisliga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7)
        findViewById<TextView>(R.id.textView7).text = resources.getString(R.string.points)+intent.getIntExtra("points",0).toString()
        findViewById<Button>(R.id.button6).setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            finish()
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            startActivity(Intent(this,Activity6::class.java))
            finish()
        }
    }
}