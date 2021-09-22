package com.josejunior.calculadoradeipva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val m = Intent(this, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({startActivity(m);finish()},3000)
    }
}