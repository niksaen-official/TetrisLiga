package com.niksaen.tetrisliga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView

class Activity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
        findViewById<ImageView>(R.id.back).setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            finish()
        }
        findViewById<WebView>(R.id.web).loadUrl("https://ligastav.ru/ypjswLDB")
    }
}