package com.example.journal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.journal.R
import com.example.journal.databinding.ActivitySplashscreenBinding
import com.example.journal.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashscreenBinding>(this, R.layout.activity_splashscreen)

        Thread(Runnable {
                try {
                    this.startActivity(Intent(this, HomeActivity::class.java))
                    finish()

                } catch (exception:Exception){ }

        }).start()

    }
}