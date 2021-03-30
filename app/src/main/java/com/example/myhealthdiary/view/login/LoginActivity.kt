package com.example.myhealthdiary.view.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myhealthdiary.databinding.ActivityLoginBinding
import com.example.myhealthdiary.utils.pref.PrefManager
import com.example.myhealthdiary.utils.pref.UserPreferenceKey
import com.example.myhealthdiary.view.home.HomeActivity
import com.example.myhealthdiary.view.register.RegisterActivity
import com.example.myhealthdiary.viewmodel.LoginViewModel
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    private val preferenceManager: PrefManager by inject()

    private var isPasswordVisible = false

    companion object {
        fun start(context: Context) {
            context.startActivity(
                context.intentFor<LoginActivity>(
                ).clearTask().newTask()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initActions()
    }

    private fun initActions(){
        binding.btnLogin.setOnClickListener {
            if (binding.edtUsername.text?.isEmpty() == true && binding.edtPassword.text?.isEmpty() == true){
                Toast.makeText(this, "Harap isi username dan password", Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.getUser(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())

                Handler(Looper.getMainLooper()).postDelayed({
                    if (loginViewModel.user?.isNotEmpty() == true){
                        val intent = Intent(this, HomeActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        preferenceManager.saveBoolean(UserPreferenceKey.IS_LOGGED_IN, true)
                        loginViewModel.user?.get(0)?.Username?.let { it1 ->
                            preferenceManager.saveString(UserPreferenceKey.USERNAME,
                                it1
                            )
                        }
                        startActivity(intent)
                    }else {
                        val intent = Intent(this, RegisterActivity::class.java).apply {
                            putExtra("Username", binding.edtUsername.text.toString())
                            putExtra("Password", binding.edtPassword.text.toString())
                        }
                        startActivity(intent)
                    }
                }, 1500)
            }
        }

        binding.btnToggle.setOnClickListener {
            if (isPasswordVisible){
                binding.btnToggle.text = "Tampilkan"
                binding.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                isPasswordVisible = false
            }else{
                binding.btnToggle.text = "Sembunyikan"
                binding.edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                isPasswordVisible = true
            }
        }
    }
}