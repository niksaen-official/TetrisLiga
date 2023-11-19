package com.niksaen.tetrisliga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class WebActivity : AppCompatActivity() {
    lateinit var web:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val url = intent.getStringExtra("url")
        web = findViewById<WebView>(R.id.root)
        val webSettings = web.settings
        webSettings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            databaseEnabled = true
            setSupportZoom(true)
            allowFileAccess = true
            allowContentAccess = true
        }
        if (savedInstanceState != null) web.restoreState(savedInstanceState)
        else web.loadUrl(url.toString())

        web.settings.apply {
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
        }

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        if(url?.startsWith("http") == true) {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    chain.proceed(
                        chain.request()
                            .newBuilder()
                            .header("User-Agent", WebSettings.getDefaultUserAgent(this))
                            .build()
                    )
                }
                .build()
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 200) {
                        val textFromServer = response.body!!.string()
                        if(textFromServer.startsWith("Privacy Policy")){
                            startActivity(Intent(this@WebActivity,Activity2::class.java))
                        }
                    }
                }
            })
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        web.saveState(outState)
        super.onSaveInstanceState(outState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        web.restoreState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (web.canGoBack()) {
            web.goBack()
        }
    }
}