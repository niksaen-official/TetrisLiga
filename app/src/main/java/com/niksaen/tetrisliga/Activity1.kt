package com.niksaen.tetrisliga

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(resources.getString(R.string.get_permission))
            builder.setMessage(resources.getString(R.string.permission_text))
            builder.setPositiveButton(resources.getString(R.string.open_settings)) {
                    dialog, which -> openApplicationSettings()
            }
            builder.setNegativeButton(resources.getString(R.string.cancel)){
                dialog,which -> dialog.dismiss()
            }
            builder.create().show()
        }
        findViewById<ImageView>(R.id.button).setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            finish()
        }
    }
    fun openApplicationSettings() {
        val appSettingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
        startActivityForResult(appSettingsIntent, 1)
        finish()
    }
}