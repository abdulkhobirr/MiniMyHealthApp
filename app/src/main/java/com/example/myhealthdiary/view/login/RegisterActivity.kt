package com.example.myhealthdiary.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhealthdiary.databinding.ActivityRegisterBinding
import com.example.myhealthdiary.utils.pref.PrefManager
import com.example.myhealthdiary.utils.pref.UserPreferenceKey
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var username: String ?= null
    private var password: String ?= null

    private val preferenceManager: PrefManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        username = intent.getStringExtra("Username")
        password = intent.getStringExtra("Password")

        initUI()
        initActions()
    }

    private fun initUI(){
        binding.edtUsername.setText(username)
        binding.edtPassword.setText(password)
    }

    private fun initActions(){
        binding.btnDaftar.setOnClickListener {
            if (binding.edtKonfirmasiPassword.text.toString() == binding.edtPassword.text.toString()){
                preferenceManager.saveString(UserPreferenceKey.USERNAME, binding.edtUsername.text.toString())
                val intent = Intent(this, InputDataActivity::class.java).apply {
                    putExtra("Username", username)
                    putExtra("Password", password)
                }
                startActivity(intent)
            }
        }
    }
}