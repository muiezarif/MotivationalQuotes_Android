package com.muiezarif.motivationalquotes.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.muiezarif.motivationalquotes.R
import com.muiezarif.motivationalquotes.databinding.ActivitySplashBinding
import com.muiezarif.motivationalquotes.intentCallForwardRightToLeftAnim

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash)

        Handler().postDelayed(
            { intentCallForwardRightToLeftAnim(context, HomeActivity::class.java) },
            SPLASH_DISPLAY_LENGTH.toLong()
        )
    }

    companion object {
        const val SPLASH_DISPLAY_LENGTH = 3000
    }
}
