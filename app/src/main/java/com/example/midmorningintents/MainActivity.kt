package com.example.midmorningintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var btnSms:Button
    lateinit var btnEmail:Button
    lateinit var btnCamera:Button
    lateinit var btnShare:Button
    lateinit var btnMpesa:Button
    lateinit var btnCall:Button
    lateinit var btnWebsite:Button
    lateinit var btnMap:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSms = findViewById(R.id.mBtnSms)
        btnEmail = findViewById(R.id.mBtnEmail)
        btnCamera = findViewById(R.id.mBtnCamera)
        btnShare = findViewById(R.id.mBtnShare)
        btnMpesa = findViewById(R.id.mBtnMpesa)
        btnCall = findViewById(R.id.mBtnCall)
        btnWebsite = findViewById(R.id.mBtnWebsite)
        btnMap = findViewById(R.id.mBtnMap)
        // Set onClick listeners to the buttons
        btnSms.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0722916304")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello there...")
            startActivity(intent)
        }
        btnEmail.setOnClickListener {

            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "innocentshibutu@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "HOLA!")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear dad, how are you...")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        btnCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }
        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app on https://www!")
            startActivity(shareIntent)
        }
        btnMpesa.setOnClickListener {
            val simToolKitLaunchIntent = applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0722916304"))
            if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
        }
        btnWebsite.setOnClickListener {
            val tembea = Intent(this@MainActivity, WebsiteActivity::class.java)
            startActivity(tembea)
        }
        btnMap.setOnClickListener {
            val ramani = Intent(this, MapsActivity::class.java)
            startActivity(ramani)
        }
    }
}