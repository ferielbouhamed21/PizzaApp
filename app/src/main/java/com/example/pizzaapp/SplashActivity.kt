package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    private val runnable = Runnable{
        if(!isFinishing){
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
    }

    override  fun onResume(){
        super.onResume()
        handler.postDelayed(runnable,5000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}