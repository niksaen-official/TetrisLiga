package com.niksaen.tetrisliga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.niksaen.tetrisliga.databinding.Activity2Binding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Activity2 : AppCompatActivity() {
    lateinit var ui:Activity2Binding
    var prevBtn:RadioButton? = null
    var index = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = Activity2Binding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setView(layoutInflater.inflate(R.layout.dialog,null,false))
        val dialog = dialogBuilder.create()
        dialog.show()
        try {
            val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
            val remoteConfigSettings = FirebaseRemoteConfigSettings
                .Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build()
            firebaseRemoteConfig.setDefaultsAsync(R.xml.default_values)
            firebaseRemoteConfig.setConfigSettingsAsync(remoteConfigSettings);
            firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    dialog.dismiss()
                    val url = firebaseRemoteConfig.getString("daby")
                    val intent = Intent(this,WebActivity::class.java)
                    intent.putExtra("url",url)
                    startActivity(intent)
                    finish()
                }
                else{
                    dialog.dismiss()
                }
            }
        } catch (_: Exception) {
            dialog.dismiss()
        }
        ui.item1.setOnClickListener {
            if(prevBtn != null){
                prevBtn?.isChecked = false
            }
            index = 1
            prevBtn = ui.btnItem1
            prevBtn?.isChecked = true
        }
        ui.item2.setOnClickListener {
            if(prevBtn != null){
                prevBtn?.isChecked = false
            }
            index = 2
            prevBtn = ui.btnItem2
            prevBtn?.isChecked = true
        }
        ui.item3.setOnClickListener {
            if(prevBtn != null){
                prevBtn?.isChecked = false
            }
            index = 3
            prevBtn = ui.btnItem3
            prevBtn?.isChecked = true
        }
        ui.item4.setOnClickListener {
            if(prevBtn != null){
                prevBtn?.isChecked = false
            }
            index = 4
            prevBtn = ui.btnItem4
            prevBtn?.isChecked = true
        }
        ui.button2.setOnClickListener {
            val intent = when(index){
                1-> Intent(this,Activity3::class.java)
                2-> Intent(this,Activity4::class.java)
                3-> Intent(this,Activity5::class.java)
                4-> Intent(this,Activity6::class.java)
                else -> null
            }
            if(intent != null) startActivity(intent)
        }
        setContentView(ui.root)
    }
}