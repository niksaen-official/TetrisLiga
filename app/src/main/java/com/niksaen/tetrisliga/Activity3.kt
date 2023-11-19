package com.niksaen.tetrisliga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        findViewById<ImageView>(R.id.back).setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            finish()
        }
    }
}