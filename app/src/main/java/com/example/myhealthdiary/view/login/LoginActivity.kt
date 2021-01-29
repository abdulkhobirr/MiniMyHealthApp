package com.example.myhealthdiary.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myhealthdiary.R
import com.example.myhealthdiary.databinding.ActivityLoginBinding
import com.example.myhealthdiary.view.home.HomeActivity
import com.example.myhealthdiary.viewmodel.LoginViewModel
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initActions()

        initObservables()
    }

    private fun initActions(){
        binding.btnLogin.setOnClickListener {
            if (binding.edtUsername.text?.isEmpty() == true && binding.edtPassword.text?.isEmpty() == true){
                Toast.makeText(this, "Harap isi username dan password", Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.getUser(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())

                if (loginViewModel.user?.isNotEmpty() == true){
                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("DATA", loginViewModel.user?.get(0))
                    }
                    startActivity(intent)
                }else {
                    val intent = Intent(this, RegisterActivity::class.java).apply {
                        putExtra("Username", binding.edtUsername.text.toString())
                        putExtra("Password", binding.edtPassword.text.toString())
                    }
                    startActivity(intent)
                }
            }
        }
    }

    private fun initObservables(){
//        loginViewModel.user?.observe(this, {
//            Log.d("LoginData: ", it.toString())
//            if (it.isNullOrEmpty()){
//                val intent = Intent(this, RegisterActivity::class.java).apply {
//                    putExtra("Username", binding.edtUsername.text.toString())
//                    putExtra("Password", binding.edtPassword.text.toString())
//                }
//                startActivity(intent)
//            } else {
//                val intent = Intent(this, HomeActivity::class.java).apply {
//                    putExtra("DATA", it[0])
//                }
//                startActivity(intent)
//            }
//        })
    }
}