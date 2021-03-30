package com.example.myhealthdiary.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.example.myhealthdiary.databinding.ActivitySplashBinding
import com.example.myhealthdiary.utils.pref.PrefManager
import com.example.myhealthdiary.utils.pref.UserPreferenceKey
import com.example.myhealthdiary.view.home.HomeActivity
import com.example.myhealthdiary.view.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val preferenceManager: PrefManager by inject()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        lifecycleScope.launch {
            delay(1500)
            isLoggedIn()
        }
    }

    private fun isLoggedIn(){
        if(preferenceManager.getBoolean(UserPreferenceKey.IS_LOGGED_IN)){
            HomeActivity.start(this)
        }else{
            LoginActivity.start(this)
        }
    }
}